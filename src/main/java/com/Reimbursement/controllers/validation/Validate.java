package com.Reimbursement.controllers.validation;


import com.Reimbursement.dao.repo.employee.EmployeeRepository;
import com.Reimbursement.models.commonModel.Vendor;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.expense.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import java.util.Collections;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {


    @Autowired
    EmployeeRepository employeeRepository;


    private static String UPLOAD_DIRECTORY = "D:/iNTELI_jA/SpringBootMySQL-CRUD/src/main/webapp/assets/img/Bills";

    Pattern pattern;
    Matcher matcher;
    String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)$";
    String STRING_PATTERN = "(?i)[a-z]+([,.\\s]+[a-z]+)*";
    Map<String, String> resp = new HashMap<>();
    String success = "false";

    public Map<String, String> validateExpense(Expense expense) {

        resp.put("success", "true");
        resp.put("message", "Successfully added.");
        return resp;
    }


    public Map<String, String> validateVendor(Vendor vendor) {


        String venderName = vendor.getVendor_name();
        String venderPhNo = vendor.getVendor_phno();
        String vendorAdrs = vendor.getVendor_address();


//        System.out.println(vendor.getLocation().getLocation_id());

        if (venderName.trim().isEmpty() || venderName.equals("") || venderPhNo.trim().isEmpty() || venderPhNo.equals("") ||
                vendorAdrs.trim().isEmpty() || vendorAdrs.equals("")
                ) {
            resp.put("success", success);
            resp.put("message", "All fields are required.");
            return resp;
        }

        try {
            Long.parseLong(venderPhNo);
        } catch (NumberFormatException ne) {
            resp.put("success", success);
            resp.put("message", "Please enter a valid mobile number.");
            return resp;
        }

        if (vendor.getLocation().getLocation_id() == 0) {
            resp.put("success", success);
            resp.put("message", "Please select vendor location.");
            return resp;
        }

        resp.put("success", "true");
        resp.put("message", "Successfully added.");
        return resp;
    }

    public Map<String, String> validateEmployee(Employee employee) {


        String fname = employee.getfName();
        String lName = employee.getlName();
        String mobileNo = employee.getMobile();
        String password = employee.getPassword().trim();
        String email = employee.getEmail().trim();

        if (fname.trim().isEmpty() || fname.equals("") || lName.trim().isEmpty() || lName.equals("") ||
                mobileNo.trim().isEmpty() || mobileNo.equals("") || password.trim().isEmpty() || password.equals("") ||
                email.trim().isEmpty() || email.equals("")
                ) {
            resp.put("success", success);
            resp.put("message", "All fields are required.");
            return resp;
        }

        if (fname.length() < 3) {
            resp.put("success", success);
            resp.put("message", "First name is too short.");
            return resp;
        }
        if (lName.length() < 3) {
            resp.put("success", success);
            resp.put("message", "Last name is too short.");
            return resp;
        }
        pattern = Pattern.compile(STRING_PATTERN);
        matcher = pattern.matcher(fname);
        if (!matcher.matches()) {
            resp.put("success", success);
            resp.put("message", "Please enter a valid name, digits or special characters are not allowed.");
            return resp;
        }


        if (password.length() < 6) {
            resp.put("success", success);
            resp.put("message", "Please enter 6-digit password length. ");
            return resp;
        }
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            resp.put("success", success);
            resp.put("message", "Please enter a valid email.");
            return resp;
        }
        if (mobileNo.length() < 10 || mobileNo.length() > 16) {
            resp.put("success", success);
            resp.put("message", "Mobile no should be 10 digit.");
            return resp;
        }
        if (mobileNo.equalsIgnoreCase("\t0000000000")) {
            resp.put("success", success);
            resp.put("message", "Please enter a valid mobile no.");
            return resp;
        }

        try {
            Long.parseLong(mobileNo);
        } catch (NumberFormatException ne) {
            resp.put("success", success);
            resp.put("message", "Please enter a valid mobile number.");
            return resp;
        }

/*

        if (employee.getSubimitter_To().getId() == employee.getApprover_To().getId()) {
            resp.put("success", success);
            resp.put("message", "Submitter & approver can not same.");
            return resp;
        }
*/

        resp.put("success", "true");
        resp.put("message", "Successfully added.");
        return resp;
    }


    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f', 'g'};
            for (int idx = 0; idx < hashedBytes.length; idx++) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();
    }

    public static String createDirectory(String userName) {


        String targetPath = UPLOAD_DIRECTORY + "/" + userName;
        File targetFolder = new File(targetPath);
        if (!targetFolder.isDirectory()) {
            targetFolder.mkdirs();
        }

        Date date = new Date();
        DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
        DateFormat dateFormatMonth = new SimpleDateFormat("MM");

        String folderYear = dateFormatYear.format(date);
        targetPath = targetPath + "/" + folderYear;
        targetFolder = new File(targetPath);
        if (!targetFolder.isDirectory()) {
            targetFolder.mkdirs();
        }
        String folderMonth = dateFormatMonth.format(date);
        targetPath = targetPath + "/" + folderMonth;
        targetFolder = new File(targetPath);
        if (!targetFolder.isDirectory()) {
            targetFolder.mkdirs();
        }
        UPLOAD_DIRECTORY = targetPath + "/";


        return UPLOAD_DIRECTORY;

    }


    public static String DIFFERENCIATE_BILL() {

        Date date = new Date();

        DateFormat dateFormatDay = new SimpleDateFormat("dd");
        String DD = dateFormatDay.format(date);
        String strDateFormat = "HH_mm_ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        String TT = sdf.format(date);

        return DD + "-" + TT + "-";
    }

    public String myReportName(int expStatus_id) {

        String reportName = "";
        // switch statement with int data type
        switch (expStatus_id) {
            case 1:
                reportName = "Created";
                break;
            case 2:
                reportName = "Submitted";
                break;
            case 3:
                reportName = "Approved";
                break;
            case 4:
                reportName = "Audited";
                break;
            case 5:
                reportName = "Rembrushed";
                break;
            case 6:
                reportName = "Rejected";
                break;
            case 7:
                reportName = "Pending";
                break;
            default:
                reportName = "Invalid";
                break;
        }

        return reportName;
    }

    public String employeeFullName(Employee emp){
        String fnme = emp.getfName();
        String lname = emp.getlName();
        fnme = fnme.substring(0, 1).toUpperCase() + fnme.substring(1).toLowerCase();
        lname = lname.substring(0, 1).toUpperCase() + lname.substring(1).toLowerCase();
        return  fnme + " " + lname;

    }

    public  Employee logedInEmployee(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(" inside default login " + authentication.getName());

        return    employeeRepository.findByEmail(authentication.getName());
    }

/*    public  int logedInEmployeeId(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(" inside default login " + authentication.getName());

        return   employeeRepository.findByEmail(authentication.getName()).getId();
    }*/

public List sortedListBasedOnID(List al){

     Collections.sort(al, new Comparator<Employee>() {
        @Override
        public int compare(Employee object1, Employee object2) {

            return  object1.getId()- object2.getId();


        }
    });

     return al;
}

}
