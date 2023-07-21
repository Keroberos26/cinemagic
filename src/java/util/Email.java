package util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Email {

    public static String resetPassword(String token) {
        // Encode URL Email
        String encodedToken = token;
        try {
            encodedToken = URLEncoder.encode(token, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "\n"
                + "<head>\n"
                + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "  <title>Reset your password</title>\n"
                + "  <!--[if mso]><style type=\"text/css\">body, table, td, a { font-family: Arial, Helvetica, sans-serif !important; }</style><![endif]-->\n"
                + "</head>\n"
                + "\n"
                + "<body style=\"font-family: Helvetica, Arial, sans-serif; margin: 0px; padding: 0px; background-color: #ffffff;\">\n"
                + "  <table role=\"presentation\"\n"
                + "    style=\"width: 100%; border-collapse: collapse; border: 0px; border-spacing: 0px; font-family: Arial, Helvetica, sans-serif; background-color: rgb(239, 239, 239);\">\n"
                + "    <tbody>\n"
                + "      <tr>\n"
                + "        <td align=\"center\" style=\"padding: 1rem 2rem; vertical-align: top; width: 100%;\">\n"
                + "          <table role=\"presentation\" style=\"max-width: 600px; border-collapse: collapse; border: 0px; border-spacing: 0px; text-align: left;\">\n"
                + "            <tbody>\n"
                + "              <tr>\n"
                + "                <td style=\"padding: 40px 0px 0px;\">\n"
                + "                  <div style=\"text-align: left;\">\n"
                + "                    <div style=\"padding-bottom: 20px;\"><img\n"
                + "                        src=\"https://i.imgur.com/4Pr5VU2.png\"\n"
                + "                        alt=\"CineMagic\" style=\"width: 96px;\"></div>\n"
                + "                  </div>\n"
                + "                  <div style=\"padding: 20px; background-color: rgb(255, 255, 255);\">\n"
                + "                    <div style=\"color: rgb(102, 102, 102); text-align: left;\">\n"
                + "                      <h1 style=\"margin: 1rem 0\">Gặp vấn đề khi đăng nhập?</h1>\n"
                + "                      <p style=\"padding-bottom: 16px\">Cảm ơn bạn vì đã chọn CineMagic.</p>\n"
                + "                      <p style=\"padding-bottom: 16px\">Đã nhận được yêu cầu thay đổi mật khẩu của bạn. Nếu bạn không thực hiện yêu cầu này, vui\n"
                + "                        lòng bỏ qua email này.</p>\n"
                + "                      <p style=\"padding-bottom: 16px\">Để thay đổi mật khẩu, vui lòng nhấn vào nút bên dưới:</p>\n"
                + "                      <p style=\"padding-bottom: 16px\">"
                + "                         <a href=\"http://localhost:8080/forgot?token=" + encodedToken + "&period=" + System.currentTimeMillis() + "\" target=\"_blank\"\n"
                + "                          style=\"padding: 12px 0; border-radius: 4px; color: #FFF; background: #8D48F5;display: block;margin: 0.5rem 0; text-align: center; text-decoration: none;\">Thay\n"
                + "                          đổi mật khẩu</a>"
                + "                         </p>\n"
                + "                      <p style=\"padding-bottom: 16px\">Nếu bạn không yêu cầu điều này, vui lòng bỏ qua email này. Mật khẩu của bạn sẽ không thay\n"
                + "                        đổi trừ khi bạn nhấp vào liên kết ở trên và tạo mật khẩu mới.</p>\n"
                + "                      <p style=\"padding-bottom: 16px\">Trân trọng,<br>CineMagic Team </p>\n"
                + "                    </div>\n"
                + "                  </div>\n"
                + "                  <div style=\"padding-top: 20px; text-align: center; color: rgb(153, 153, 153);\">\n"
                + "                    <p style=\"padding-bottom: 16px\">Made with ♥ in CineMagic</p>\n"
                + "                  </div>\n"
                + "                </td>\n"
                + "              </tr>\n"
                + "            </tbody>\n"
                + "          </table>\n"
                + "        </td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table>\n"
                + "</body>\n"
                + "\n"
                + "</html>";
    }

    public static void send(String to, String subject, String msg) {
        final String from = "jinnguyen054@gmail.com";
        final String password = "izipkgecvocyhrmo";
        final String name = "CineMagic";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo Session với thông tin tài khoản gửi email
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from, name));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            String encodedSubject = MimeUtility.encodeText(subject, "UTF-8", "B");
            message.setSubject(encodedSubject);
            message.setContent(msg, "text/html; charset=utf-8");

            // Gửi email
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
