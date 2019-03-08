package com.Reimbursement.serviceImpl.commonServiceImpl;


        import com.Reimbursement.service.commonServices.EmailService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.mail.SimpleMailMessage;
        import org.springframework.mail.javamail.JavaMailSender;
        import org.springframework.mail.javamail.MimeMessageHelper;
        import org.springframework.scheduling.annotation.Async;
        import org.springframework.stereotype.Service;
        import javax.mail.MessagingException;
        import javax.mail.internet.MimeMessage;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendTextEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }



    public void sendHTML_ExpenseMail(String userName, String T0,long amount, int expenseID, String byName, String expenseStatus) {

        try{

            System.out.println("inside sendHTML_RegistrationMail service impl....");
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("ltlogiclab@gmail.com");
            helper.setTo(T0);
            helper.setSubject("Expense : " + expenseID);

            String text =
                            "<html>\n" +
                            "    <head>\n" +
                            "    </head>\n" +
                            "    <body>\n" +
                            "        <div style=\"font-family:Verdana; background-color: #eeeeee; padding:20px;\">\n" +
                            "\n" +
                            "            <div style=\"background-color:#fff;padding:15px;text-align:center;\">\n" +
                            "                <h1>Dear <span style=\"font-size:20px;\">" + userName + "</span></h1>\n" +
                            "                <h3 style=\"color:gray;\"> Thank you for using get reimbursement tool.</h3>\n" +
                            "            </div>\n" +
                            "\n" +
                            "            <div style=\"background-color:#fff;padding:10px;margin:2px;font-size:12px;\"> \n" +
                            "                Your Expense  <I> "+ expenseID + "</i> has been <i>" + expenseStatus +"</i> with Rs " + amount + ".<br>"
                                        + "Expense " + expenseStatus.substring(0, 1).toUpperCase() + expenseStatus.substring(1).toLowerCase()
                                        +"  by <I> " + byName + "</I>.<br>\n" +
                            "\n" +
                            "            </div>\n" +



                            "            <div style=\"background-color:#fff;padding:15px; border-top:1px solid #eeeeee;\">\n" +
                            "                <h6>Wish you all the best</h6>\n" +
                            "                <h5>Thanks & Regards<br/>IT Logic Lab</h5>\n" +
                            "                <img src=\"http://www.dreamzcard.com/new/assets/img/hot/itlogiclab.png\" style=\"max-height:40px;\">\n" +
                            "            </div>\n" +
                            "            <div style=\"padding:10px;text-align:center;\">\n" +
                            "                <small style=\"font-size:10px;\" >© 2018 ItlogicLab(P)Ltd.India, <br> All rights recived.</small>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </body>\n" +
                            "</html>";

            helper.setText(text, true);
            mailSender.send(message);

        }catch (Exception ex){
            System.out.println("Exception Messages " + ex.getMessage());
        }

    }

    // Use it to send HTML emails

    public void sendHTML_RegistrationMail(String userName, String TO) {

        try {
            System.out.println("inside sendHTML_RegistrationMail service impl....");
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("ltlogiclab@gmail.com");
            helper.setTo(TO);
            helper.setSubject("Registration confirmation");

            String text =
                    "<html>\n" +
                            "    <head>\n" +
                            "    </head>\n" +
                            "    <body>\n" +
                            "        <div style=\"font-family:Verdana; background-color: #eeeeee; padding:20px;\">\n" +
                            "\n" +
                            "            <div style=\"background-color:#fff;padding:15px;text-align:center;\">\n" +
                            "                <h1>Hi <span style=\"font-size:20px;\">" + userName + "</span></h1>\n" +
                            "                <h3 style=\"color:gray;\"> Thank you for your registration in get reimbursement tool.</h3>\n" +
                            "            </div>\n" +
                            "\n" +
                            "            <div style=\"background-color:#fff;padding:10px;margin:2px;font-size:12px;\"> \n" +
                            "                Your account has been created with Email: " + TO +"  <br>\n" +
                            "\n" +
                            "            </div>\n" +



                            "            <div style=\"background-color:#fff;padding:15px; border-top:1px solid #eeeeee;\">\n" +
                            "                <h6>Wish you all the best</h6>\n" +
                            "                <h5>Thanks & Regards<br/>IT Logic Lab</h5>\n" +
                            "                <img src=\"http://www.dreamzcard.com/new/assets/img/hot/itlogiclab.png\" style=\"max-height:40px;\">\n" +
                            "            </div>\n" +
                            "            <div style=\"padding:10px;text-align:center;\">\n" +
                            "                <small style=\"font-size:10px;\" >© 2018 ItlogicLab(P)Ltd.India, <br> All rights recived.</small>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </body>\n" +
                            "</html>";

            helper.setText(text, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            System.out.println("There was an error in email sending. Please check your email address: " + TO);
        }

    }

}
