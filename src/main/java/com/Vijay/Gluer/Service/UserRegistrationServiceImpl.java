package com.Vijay.Gluer.Service;

import com.Vijay.Gluer.Exceptions.UserRegistrationException;
import com.Vijay.Gluer.Model.UserRegistration;
import com.Vijay.Gluer.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Optional;
import java.util.Properties;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{


    private UserRepository userRepository;
    private EncryptionService encryptionService=new EncryptionService();

    private static final String SECRET_KEY = "My!Name!is_Vijay";
    private static final String SALT = "This_is!for_ever";


    private static final String SECRET_KEY_2= "UseThisSecretKey";
    private static final String SALT_2 = "CheckIfItWorking";

    @Autowired
    public UserRegistrationServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }



    @Override
    public String saveUser(UserRegistration userRegistration) throws UserRegistrationException{
        UserRegistration saved=new UserRegistration();
        String result=new String();
        userRegistration.setValidated("No");
                if (!(userRepository.existsById(userRegistration.getEmail()))) {
                    if((userRegistration.getEmail().substring(userRegistration.getEmail().length() - 4, userRegistration.getEmail().length()).equals(".com")) &&
                            (userRegistration.getConfirmpass().equals(userRegistration.getPassword()))){
                        userRegistration.setEncyptor(encryptionService.encyptedEmail(userRegistration.getEmail()));
                            saved = userRepository.save(userRegistration);
                            sendMail(userRegistration.getEmail());
                    result = "User Saved Successfully";}
                    else {
                        result="Wrong Email or Password Credentials";
                    }
                } else {
                    result = "User Already Exists";
                }


        return result;
    }

    @Override
    public UserRegistration getUser(String Email) {
        UserRegistration allById=userRepository.findById(Email).get();
        return allById;
    }

    @Override
    public String updateUserValidation(String Encrypted) {
        UserRegistration userRegistration=new UserRegistration();
        String email=encryptionService.decrypt(Encrypted);
//        UserRegistration allByEncryptor = userRepository.findAllByEncryptor(Encrypted);
//        System.out.println(allByEncryptor.getEmail());
        UserRegistration validate = userRepository.findById(email).get();
        userRegistration.setValidated("Yes");
        validate.setValidated(userRegistration.getValidated());
        userRepository.save(validate);
        return "User Registered Successfully, Go to Login Page";
    }

    @Override
    public String updateUserOnSlashValidation(String email) {
        UserRegistration userRegistration=new UserRegistration();
//        UserRegistration allByEncryptor = userRepository.findAllByEncryptor(Encrypted);
//        System.out.println(allByEncryptor.getEmail());
        UserRegistration validate = userRepository.findById(email).get();
        userRegistration.setValidated("Yes");
        validate.setValidated(userRegistration.getValidated());
        userRepository.save(validate);
        return "User Registered Successfully, Go to Login Page";

    }







    public void sendMail(String email){

// Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "pruthvikrishna97@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("pruthvikrishna97@gmail.com", "zilslxquysrpsnlf");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Glue User- Welcome and Validate");

            // Now set the actual message
            message.setText("Please Verify using following link:");

            String linkText=new String();

            String encryptedString = encryptionService.encyptedEmail(email);
            String encryptedString2=encryptionService.encryptionEmail2(email);
            String encryptedString3=encryptionService.encryptionEmail3(email);
            if(!encryptedString.contains("/")){
                linkText=("http://localhost:8071/gluser/verify/"+encryptedString);
            }
//            String Original=new String();
//            try {
//                encryptedString=encyptedEmail(email);
//                if(encryptedString.contains("/")){
//                    Original=encryptedString.replaceAll("/","%20");
//                }
//                else {
//                    Original=encyptedEmail(email);
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            String encryptedString = encyptedEmail(email);
            else if(!encryptedString2.contains("/")){
                    linkText=("http://localhost:8071/gluser/verify/" + encryptedString2);
            }
            else if(!encryptedString3.contains("/")){
                linkText=("http://localhost:8071/gluser/verify/" + encryptedString3);
            }
            else {
                linkText=("http://localhost:8071/gluser/glueme/verify/"+email);
            }
            message.setText(linkText);
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }



//    return emailStringEncrypted;
}


