//package project;

   
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.Properties;  
import java.util.Random;
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.swing.JTextField;
import static jdk.nashorn.internal.objects.NativeMath.random;
      
    public class SendEmail {  
     String host="localhost";  
     final  String user="projectasbar@gmail.com";//change accordingly  
     final  String password="asbarproject";//change accordingly  
     //final  Random rand;
     //final String to="";//change accordingly  
      SendEmail(String user1,int OTPCode) {
          String to;  
          //rand=new Random();
          to=this.user;
          System.out.println("username : "+user1);
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
         
       Session session = Session.getDefaultInstance(props,  
        new javax.mail.Authenticator() {  
          protected PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(to,password);  
          }  
        });  
      
       //Compose the message  
        try {  
            MimeMessage message = new MimeMessage(session);  
            message.setFrom(new InternetAddress(this.user));  
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(user1));  
            message.setSubject("Reset Password");  
            //String OTPCode=String.format("%04d", rand.nextInt(10000));
            message.setText("Dear user below is the OTP generated...\nOTP:"+OTPCode);  
            //new ResetPassword(OTPCode);
        //send the message  
         Transport.send(message);  
      
         System.out.println("message sent successfully...");  
       
         } catch (MessagingException e) {e.printStackTrace();}  
     }     
}  