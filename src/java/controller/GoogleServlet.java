package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.AccountDAO;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Account;
import model.Constants;
import model.UserGoogle;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;

/**
 *
 * @author nguye
 */
public class GoogleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        UserGoogle user = getUserInfo(accessToken);
        System.out.println(user);
        //todo : select account where fbid == user.id
        // if exist => set current account = account
        // if not exist => create new account, account.fbid = user.id => set current account = acount
        AccountDAO dao = new AccountDAO();
        Account currentAcc = null;

        currentAcc = dao.loginGmail(user.getId());

        if (currentAcc != null) {
            session.setAttribute("acc", currentAcc);
            response.sendRedirect("/");
        } else {

            dao.addAccountByGoogleId(user.getId(), user.getName(), user.getPicture());
            currentAcc = dao.loginGmail(user.getId());

            session.setAttribute("acc", currentAcc);
            response.sendRedirect("/");
        }
    }

    public static String getToken(String code) throws IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI)
                        .add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE)
                        .build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogle getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        UserGoogle googlePojo = new Gson().fromJson(response, UserGoogle.class);

        return googlePojo;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
