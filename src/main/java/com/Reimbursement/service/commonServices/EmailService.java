package com.Reimbursement.service.commonServices;


import org.springframework.mail.SimpleMailMessage;


public interface EmailService {

    public void sendTextEmail(SimpleMailMessage email);

    public void sendHTML_ExpenseMail(String userName, String T0,long amount, int expenseID,String byName, String expenseStatus);

    public void sendHTML_RegistrationMail(String userName, String T0);

}
