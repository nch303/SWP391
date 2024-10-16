package koicare.koiCareProject.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import koicare.koiCareProject.dto.request.EmailDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(EmailDetail emailDetail) {
        try {
            //context cua thymeleaf
            Context context = new Context();
            context.setVariable("name", emailDetail.getAccount().getMember().getName());
            context.setVariable("button", "Go to Sunside Koi Care" );
            context.setVariable("link", emailDetail.getLink());

            String template = templateEngine.process("welcome-template", context);
            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getAccount().getEmail());
            mimeMessageHelper.setText(template, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            System.out.println("Error send email!!!");
        }
    }

    public void sendEmailShop(EmailDetail emailDetail) {
        try {
            //context cua thymeleaf
            Context context = new Context();
            context.setVariable("name", emailDetail.getAccount().getShop().getName());
            context.setVariable("button", "Go to your shop page" );
            context.setVariable("link", emailDetail.getLink());

            String template = templateEngine.process("welcome-template", context);
            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getAccount().getEmail());
            mimeMessageHelper.setText(template, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            System.out.println("Error send email!!!");
        }
    }

    public void sendEmailRejectPost(EmailDetail emailDetail) {
        try {
            //context cua thymeleaf
            Context context = new Context();
            context.setVariable("name", emailDetail.getAccount().getShop().getName());
            context.setVariable("button", "Go to your shop page" );
            context.setVariable("link", emailDetail.getLink());

            String template = templateEngine.process("rejectPostOfShop.html", context);
            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getAccount().getEmail());
            mimeMessageHelper.setText(template, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            System.out.println("Error send email!!!");
        }
    }

    public void sendEmailUpdateShop(EmailDetail emailDetail) {
        try {
            //context cua thymeleaf
            Context context = new Context();
            context.setVariable("name", emailDetail.getAccount().getShop().getName());
            context.setVariable("button", "Go to your shop page" );
            context.setVariable("link", emailDetail.getLink());

            String template = templateEngine.process("updateInformation.html", context);
            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getAccount().getEmail());
            mimeMessageHelper.setText(template, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            System.out.println("Error send email!!!");
        }
    }

    public void sendEmailUpdateMember(EmailDetail emailDetail) {
        try {
            //context cua thymeleaf
            Context context = new Context();
            context.setVariable("name", emailDetail.getAccount().getMember().getName());
            context.setVariable("button", "Go to Sunside Koi Care" );
            context.setVariable("link", emailDetail.getLink());

            String template = templateEngine.process("updateInformation.html", context);
            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getAccount().getEmail());
            mimeMessageHelper.setText(template, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            System.out.println("Error send email!!!");
        }
    }

    public void sendEmailBannedAccount(EmailDetail emailDetail) {
        try {
            //context cua thymeleaf
            Context context = new Context();
            if(emailDetail.getAccount().getRole().toString().equals("MEMBER")){
                context.setVariable("name", emailDetail.getAccount().getMember().getName());
                context.setVariable("button", "" );
                context.setVariable("link", emailDetail.getLink());
            }else{
                context.setVariable("name", emailDetail.getAccount().getShop().getName());
                context.setVariable("button", "" );
                context.setVariable("link", emailDetail.getLink());
            }


            String template = templateEngine.process("bannedAccount.html", context);
            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getAccount().getEmail());
            mimeMessageHelper.setText(template, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            System.out.println("Error send email!!!");
        }
    }

    public void sendEmailRestoreAccount(EmailDetail emailDetail) {
        try {
            //context cua thymeleaf
            Context context = new Context();
            if(emailDetail.getAccount().getRole().toString().equals("MEMBER")){
                context.setVariable("name", emailDetail.getAccount().getMember().getName());
                context.setVariable("button", "Go to SUNSIDE KOI CARE " );
                context.setVariable("link", emailDetail.getLink());
            }else{
                context.setVariable("name", emailDetail.getAccount().getShop().getName());
                context.setVariable("button", "Go to your shop page" );
                context.setVariable("link", emailDetail.getLink());
            }



            String template = templateEngine.process("restoreAccount.html", context);
            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getAccount().getEmail());
            mimeMessageHelper.setText(template, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException e){
            System.out.println("Error send email!!!");
        }
    }
}
