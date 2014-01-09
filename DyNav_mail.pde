void mail()
{

  // Function to check mail
  // checkMail();
  
  // Function to send mail
  sendMail();
  
  // noLoop();
}

void sendMail() {
  // Create a session
  String host="smtp.gmail.com";
  Properties props=new Properties();

  // SMTP Session
  props.put("mail.transport.protocol", "smtp");
  props.put("mail.smtp.host", host);
  props.put("mail.smtp.port", "25");
  props.put("mail.smtp.auth", "true");
  props.put("mail.smtp.socketFactory.port", "465");
 props.put("mail.smtp.socketFactory.class",
                           "javax.net.ssl.SSLSocketFactory");
 props.put("mail.smtp.auth", "true");
 props.put("mail.smtp.port", "465");
  // We need TTLS, which gmail requires
  props.put("mail.smtp.starttls.enable","true");

  // Create a session
  Session session = Session.getDefaultInstance(props, new Auth());

  try
  {
    // Make a new message
    MimeMessage message = new MimeMessage(session);

    // Who is this message from
    message.setFrom(new InternetAddress("", ""));

    // Who is this message to (we could do fancier things like make a list or add CC's)
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("", false));

    // Subject and body
    message.setSubject(mail1);
    message.setText(mail2);

    // We can do more here, set the date, the headers, etc.
    Transport.send(message);
    // println("Mail sent!");
  }
  catch(Exception e)
  {
    e.printStackTrace();
  }

}

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Auth extends Authenticator {

  public Auth() {
    super();
  }

  public PasswordAuthentication getPasswordAuthentication() {
    String username, password;
    username = "";
    password = "";
    System.out.println("authenticating. . ");
    return new PasswordAuthentication(username, password);
  }
}
