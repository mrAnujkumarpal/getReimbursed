package com.Reimbursement.controllers.validation;

import com.Reimbursement.dao.repo.employee.EmployeeRepository;
import com.Reimbursement.models.commonModel.Vendor;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.expense.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.text.ParseException;
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

    private static String UPLOAD_DIRECTORY = "D:/iNTELI_jA/getReimbursed/src/main/webapp/assets/img/Bills";

    Pattern pattern;
    Matcher matcher;
    String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)$";
    String STRING_PATTERN = "(?i)[a-z]+([,.\\s]+[a-z]+)*";
    Map<String, String> resp = new HashMap<>();
    String success = "false";

    public final String Org_Name = "Xebia";//Newgen//Oracle
    public final String ORG_CODE = "XI";//NG//OR

    public Map<String, String> validateVendor(Vendor vendor) {

        String venderName = vendor.getVendor_name();
        String venderPhNo = vendor.getVendor_phno();
        String vendorAdrs = vendor.getVendor_address();
        int locId = vendor.getLocation().getLocation_id();
        venderName = venderName.trim();
        venderPhNo = venderPhNo.trim();
        vendorAdrs = vendorAdrs.trim();
//        System.out.println(vendor.getLocation().getLocation_id());

        if (venderName.trim().isEmpty() || venderName.equals("") || venderPhNo.trim().isEmpty() || venderPhNo.equals("")
                || vendorAdrs.trim().isEmpty() || vendorAdrs.equals("")) {
            resp.put("success", success);
            resp.put("message", "All fields are required.");
            return resp;
        }

        if (venderName.length() < 3) {
            resp.put("success", success);
            resp.put("message", "Please enter a valid name.");
            return resp;
        }
        try {
            Long.parseLong(venderPhNo);
        } catch (NumberFormatException ne) {
            resp.put("success", success);
            resp.put("message", "Please enter a valid mobile number.");
            return resp;
        }

        if (locId == 0) {
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

        if (fname.trim().isEmpty() || fname.equals("") || lName.trim().isEmpty() || lName.equals("")
                || mobileNo.trim().isEmpty() || mobileNo.equals("") || password.trim().isEmpty() || password.equals("")
                || email.trim().isEmpty() || email.equals("")) {
            resp.put("success", success);
            resp.put("message", "All fields are required.");
            return resp;
        }
        fname = fname.trim();
        lName = lName.trim();
        mobileNo = mobileNo.trim();
        password = password.trim();
        email = email.trim();
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

        if (employee.getSubimitter_To().getId() == employee.getApprover_To().getId()) {
            resp.put("success", success);
            resp.put("message", "Submitter & approver can not same.");
            return resp;
        }

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

        String reportName;
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
                reportName = "Reimbursed";
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

    public String employeeFullName(Employee emp) {
        String fnme = emp.getfName();
        String lname = emp.getlName();
        fnme = fnme.substring(0, 1).toUpperCase() + fnme.substring(1).toLowerCase();
        lname = lname.substring(0, 1).toUpperCase() + lname.substring(1).toLowerCase();
        return fnme + " " + lname;

    }

    public Employee logedInEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return employeeRepository.findByEmail(authentication.getName());
    }

    public List sortedListBasedOnID(List al) {

        Collections.sort(al, (Employee object1, Employee object2) -> object1.getId() - object2.getId());

        return al;
    }

    protected static boolean isFutureDate(final Date providedDate) {

        Date currentDate = new Date(System.currentTimeMillis());
        boolean t = false;
        if (providedDate.after(currentDate)) {
            t = true;
        }

        if (providedDate.before(currentDate)) {
            t = false;
        }

        return t;
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    protected static Date stringToDate(String dateInStr) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateType = null;
        try {
            dateType = df.parse(dateInStr);

        } catch (ParseException e) {
        }
        return dateType;
    }

    public Map<String, String> validateExpense(Expense expense) {

        Map<String, String> response = new HashMap<>();
        String title = expense.getExp_name().trim();

        String message = "";
        String description = expense.getExp_description();
        boolean billable = expense.getBillable();

        System.out.println(expense.getExpenseDate() == null);
        System.out.println(expense.getExpenseType() == null);
        System.out.println(expense.getPaymentMode() == null);
        System.out.println(expense.getVendor() == null);
        System.out.println(expense.getExp_amount() == 0);
        System.out.println(description.trim().isEmpty());
        System.out.println(description == null);
        System.out.println(title.isEmpty());

        if (expense.getExpenseDate() == null
                || expense.getExpenseType() == null
                || expense.getPaymentMode() == null
                || expense.getVendor() == null
                || expense.getExp_amount() == 0
                || description.trim().isEmpty()
                || description == null || title.isEmpty()) {
            message = "All Fields are required !!";
        } else {

            Long amount = expense.getExp_amount();
            int amt = amount.intValue();
            if (title.length() < 3) {
                message = "Title is too short";
            } else if (title.length() > 45) {
                message = "Title is too large write only in 45 characters. ";
            } else if (amount == null || amount == 0) {
                message = "Please enter the amount.";
            } else if (amt < 10 || amt > 100000) {
                message = "Please enter the amount in between  Rs. 10-to-1,00000 .";
            } else {
                success = "true";
            }

        }
        if (expense.getExp_Date() != null) {
            System.out.println("inside here dte not null");

        }
        response.put("success", success);
        response.put("message", message);
        return response;
    }
}
