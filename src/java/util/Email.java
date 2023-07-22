package util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Combo;
import model.Theater;
import model.Ticket;

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

    private static String combo(Map<Combo, Integer> combo) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Combo, Integer> entry : combo.entrySet()) {
            Combo key = entry.getKey();
            Integer value = entry.getValue();
            sb.append("<div><strong>");
            sb.append(value);
            sb.append(" x ");
            sb.append(key.getName());
            sb.append("</strong></div>");
        }
        return sb.toString();
    }

    public static String ticket(Ticket t, Theater th) {

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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
                + "                    <div style=\"padding-bottom: 20px;\"><img src=\"https://i.imgur.com/4Pr5VU2.png\" alt=\"Company\" style=\"width: 56px;\"></div>\n"
                + "                  </div>\n"
                + "                  <div style=\"padding: 20px; background-color: rgb(255, 240, 246);\">\n"
                + "                    <div style=\"color: rgb(0, 0, 0); text-align: left;\">\n"
                + "                      <h2>" + t.getShowtime().getMovie().getTitle() + "</h2>\n"
                + "                      <div style=\"display: flex; justify-content: space-between; min-width: 500px;\">\n"
                + "                        <div><strong style=\"font-size: 13px; color: #7c899a\">THỜI GIAN</strong>\n"
                + "                          <div><strong>" + timeFormat.format(t.getShowtime().getStarttime()) + " ~ " + timeFormat.format(t.getShowtime().getEndtime()) + "</strong></div>\n"
                + "                        </div>\n"
                + "                        <div style=\"text-align: end;margin-left:auto;\"><strong style=\"font-size: 13px; color: #7c899a\">NGÀY CHIẾU</strong>\n"
                + "                          <div><strong>" + dateFormat.format(t.getShowtime().getStarttime()) + "</strong></div>\n"
                + "                        </div>\n"
                + "                      </div>\n"
                + "\n"
                + "                      <div\n"
                + "                        style=\"display: flex; justify-content: space-between; margin:  12px 0; border-bottom: 1px dashed #e5e8ec; padding-bottom: 12px\">\n"
                + "                        <div><strong style=\"font-size: 13px; color: #7c899a\">GHẾ</strong>\n"
                + "                          <div><strong>" + t.getSeatNum() + "</strong></div>\n"
                + "                        </div>\n"
                + "                        <div style=\"text-align: end;margin-left:auto;\"><strong style=\"font-size: 13px; color: #7c899a\">PHÒNG CHIẾU</strong>\n"
                + "                          <div><strong>" + t.getShowtime().getRoom().getName() + "</strong></div>\n"
                + "                        </div>\n"
                + "                      </div>\n"
                + "\n"
                + "                      <div\n"
                + "                        style=\"display: flex; justify-content: space-between; margin: 12px 0; border-bottom: 1px dashed #e5e8ec; padding-bottom: 12px\">\n"
                + "                        <div><strong style=\"font-size: 13px; color: #7c899a\">RẠP</strong>\n"
                + "                          <div><strong>" + th.getName() + "</strong></div>\n"
                + "                        </div>\n"
                + "                        <div style=\"text-align: end;margin-left:auto;\"><strong style=\"font-size: 13px; color: #7c899a\">BẮP NƯỚC</strong>\n"
                + "                          "+ combo(t.getCombos()) +"\n"
                + "                        </div>\n"
                + "                      </div>\n"
                + "\n"
                + "\n"
                + "                      <p style=\"padding-bottom: 16px\">Bạn có thể xuất trình vé cho nhân viên soát vé tại quầy.</p>\n"
                + "                      <p style=\"padding-bottom: 16px\">Thanks,<br>The CineMagic team</p>\n"
                + "                    </div>\n"
                + "                  </div>\n"
                + "                  <div style=\"padding-top: 20px; color: rgb(153, 153, 153); text-align: center;\">\n"
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
