package com.Reimbursement.controllers.site;

/**
 * Created by Anuj Kumar.
 */
import com.Reimbursement.controllers.validation.Validate;
import com.Reimbursement.models.commonModel.Vendor;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.expense.*;
import com.Reimbursement.service.commonServices.CommonService;
import com.Reimbursement.service.commonServices.EmailService;
import com.Reimbursement.service.empService.EmployeeService;
import com.Reimbursement.service.expenseService.ExpenseService;
import com.Reimbursement.serviceImpl.expenseServiceImpl.XlsxReport;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.apache.tomcat.util.codec.binary.Base64;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;

@Controller
public class ExpenseController extends Validate {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private XlsxReport xlsxReport;
    private ExpenseType et;

    @RequestMapping("/expenseType")
    public ModelAndView viewAllExpenseType() {
        ModelAndView mv = new ModelAndView("expense/expenseType");
        List<ExpenseType> et = expenseService.viewAllExpenseType();

        mv.addObject("expenseTypeList", et);
        mv.addObject("employeeRoleId", loggedInEmployee().getEmpRole().getId());
        return mv;
    }

    @RequestMapping(value = "/expenseType/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addNewExpenseType(ModelMap model, HttpServletRequest request) {
        System.out.println("comes here common Controller ");
        Map<String, String> resp = new HashMap<>();
        String etName = request.getParameter("expType_Name").trim();
        System.out.println("comes here common Controller " + etName);

        ExpenseType expenseType = new ExpenseType();

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date currentDate = new Date();
        System.out.println("current Date ::" + df.format(currentDate));

        expenseType.setCreated_Date(currentDate);
        expenseType.setExpType_Name(etName);

        expenseService.addExpenseType(expenseType);

        resp.put("success", "true");
        resp.put("message", etName + " as new role added successfully.");
        return resp;
    }

    @RequestMapping(value = "/editExpenseType/{expType_Id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> editExpenseType(ModelMap model, @PathVariable int expType_Id) {
        System.out.println("comes here Controller " + expType_Id);
        Map<String, Object> resp = new HashMap<>();

        ExpenseType expenseType = expenseService.getExpenseTypeById(expType_Id);
        //System.out.println("----------" + expenseType.getExpType_Name());
        resp.put("success", "true");
        resp.put("id", expenseType.getExpType_Id());
        resp.put("exptype", expenseType.getExpType_Name());
        return resp;
    }

    @RequestMapping(value = "/expenseType/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateExpenseType(ModelMap model, HttpServletRequest request) {

        Map<String, String> resp = new HashMap<>();
        String et_id = request.getParameter("expType_Id").trim();
        String et_name = request.getParameter("expType_Name").trim();

        ExpenseType et = expenseService.getExpenseTypeById(Integer.parseInt(et_id));
        et.setExpType_Name(et_name);
        expenseService.addExpenseType(et);

        resp.put("success", "true");
        resp.put("message", "Location successfully updated.");
        return resp;
    }

    @RequestMapping(value = "/deleteExpenseType/{et_id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> deleteOrgnigationRole(ModelMap model, @PathVariable int et_id) {
        Map<String, String> resp = new HashMap<>();
        ExpenseType et = expenseService.getExpenseTypeById(et_id);

        expenseService.deleteExpenseTypeById(et);
        resp.put("success", "true");
        return resp;
    }

    @RequestMapping(value = "/expenseStatus", method = RequestMethod.GET)
    public ModelAndView viewAllExpenseStatus() {
        ModelAndView mv = new ModelAndView("expense/expenseStatus");
        List<ExpenseStatus> esL = expenseService.viewAllExpenseStatus();

        mv.addObject("expenseStatusList", esL);
        mv.addObject("employeeRoleId", loggedInEmployee().getEmpRole().getId());
        return mv;
    }

    @RequestMapping(value = "/expenseStatus/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addNewExpenseStatus(ModelMap model, HttpServletRequest request) {
        System.out.println("comes here common Controller ");
        Map<String, String> resp = new HashMap<>();
        String esName = request.getParameter("expType_Name").trim();
       // System.out.println("comes here common Controller " + esName);

        ExpenseStatus es = new ExpenseStatus();

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date currentDate = new Date();
        System.out.println("current Date ::" + df.format(currentDate));

        es.setCreated_Date(currentDate);
        es.setExpStatus_Name(esName);
        expenseService.addExpenseStatus(es);

        resp.put("success", "true");
        resp.put("message", esName + " as new role added successfully.");
        return resp;
    }

    @RequestMapping("/createEditExpense")
    public ModelAndView createExpense() {

        System.out.println("Go 4 CreateExpense");
        ModelAndView mv = new ModelAndView("expense/createNew");

        // Employee employee= employeeService.getEmployeeById(logedInEmployeeId());
        mv.addObject("payModeList", commonService.getAllPaymentMode());
        mv.addObject("allVendorsList", commonService.getAllVendors());
        mv.addObject("locationList", commonService.getAllLocations());
        mv.addObject("expenseTypeList", expenseService.viewAllExpenseType());
        mv.addObject("employeeRoleId", loggedInEmployee().getEmpRole().getId());
        mv.addObject("mode", "Add");
        return mv;
    }

    @RequestMapping(value = "/editExpenseDetails/{exp_id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editExpenseDetails(@PathVariable("exp_id") int exp_id) {
        //System.out.println("comming id for edit" + exp_id);
        ModelAndView mv = new ModelAndView();

        Expense ex = expenseService.getExpenseById(exp_id);
        if (ex.getExpenseStatus().getExpStatus_Id() != 1) {
           // System.out.println("You are in wrong track");
            mv.setViewName("errors/404");
        } else {
            mv.setViewName("expense/createNew");
            mv.addObject("payModeList", commonService.getAllPaymentMode());
            mv.addObject("allVendorsList", commonService.getAllVendors());
            mv.addObject("locationList", commonService.getAllLocations());
            mv.addObject("expenseTypeList", expenseService.viewAllExpenseType());
            mv.addObject("expense", ex);
            mv.addObject("mode", "Edit");

        }
        mv.addObject("employeeRoleId", loggedInEmployee().getEmpRole().getId());
        return mv;
    }

    @RequestMapping(value = "/createEditExpense", method = RequestMethod.POST)
    public ModelAndView createEditExpense(
            ModelMap model,
            @ModelAttribute Expense expense,
            @RequestParam("pictureName") MultipartFile[] uploadingFiles) {

        Date currentDate = new Date();
        Employee emp = loggedInEmployee();

        ModelAndView mv = new ModelAndView("expense/createNew");
        Map<String, String> validateExpense = validateExpense(expense);

        String success = validateExpense.get("success");
        String message = validateExpense.get("message");

        if (success.equalsIgnoreCase("false")) {

            mv.addObject("success", success);
            mv.addObject("message", message);
            //System.out.println(" ========= GO BACK ========== ");
            if (expense.getExpenseDate() != null) {
                System.out.println(" Inside ");
                String dteStr = expense.getExpenseDate();
                Date dte = stringToDate(dteStr);
                expense.setExp_Date(dte);
            }

            mv.addObject("expense", expense);
            mv.addObject("payModeList", commonService.getAllPaymentMode());
            mv.addObject("allVendorsList", commonService.getAllVendors());
            mv.addObject("locationList", commonService.getAllLocations());
            mv.addObject("expenseTypeList", expenseService.viewAllExpenseType());
            mv.addObject("employeeRoleId", emp.getEmpRole().getId());
            mv.addObject("mode", "Add");

        } else {

            expense.setExp_Date(stringToDate(expense.getExpenseDate()));

            int vid = expense.getVendor().getVendor_Id();
            System.out.println("Vendor id ::" + vid);
            Vendor addedVendor = new Vendor();
            if (expense.getVendor().getVendor_Id() == 2685) {
                System.out.println("you choose other so need to put vendor details.");
                String vendorname = expense.getVendor().getVendor_name();

                Vendor vendor = expense.getVendor();
                if (vendor != null) {
                    System.out.println("Vendor not null . " + vendor.getLocation().getLocation_id());

                    vendor.setCreated_Date(currentDate);
                    addedVendor = commonService.addNewVendor(vendor);
                }

            } else {

                Vendor ven = commonService.getVendorDetailsByVendorID(vid);
               // System.out.println("Vendor Location id jo jake DB m save hoge ::" + ven.getLocation().getLocation_id());
               // System.out.println("dono same hone hai jo jake DB m save hoge ::" + commonService.getLocationById(ven.getLocation().getLocation_id()));
                expense.setLocation(commonService.getLocationById(ven.getLocation().getLocation_id()));
            }

            String empName = employeeFullName(emp);

            expense.setEmployee(emp);
            expense.setExp_createdBy(empName);

            expense.setExp_createdDate(currentDate);
            expense.setExpenseStatus(expenseService.getExpenseStatusDetailsById(1));
            Expense addedExpense = expenseService.saveExpense(expense);

            System.out.println(" Added Expense " + addedExpense.getExp_id());

            if (addedExpense != null && expense.getBillable()) {
                System.out.println("Expense billable  : TRUE");

                /*-----------------------------Creating Directory for upload Bills------------------------------------------*/
                String CREATED_DIRECTORY = createDirectory(empName);
                System.out.println(" CREATED_DIRECTORY :::::::::::::::::::::::: " + CREATED_DIRECTORY);
                //vendor.setCreated_Date(currentDate);

                try {
                    for (MultipartFile uploadedFile : uploadingFiles) {

                        String DIFFERENCIATE_BILL = DIFFERENCIATE_BILL();
                        // System.out.println(" DIFFERENCIATE_BILL =======================::" + DIFFERENCIATE_BILL);
                        //File file = new File(CREATED_DIRECTORY + DIFFERENCIATE_BILL + uploadedFile.getOriginalFilename());
                        //System.out.println(" file name  =======================::" + file.getName());

                        //uploadedFile.transferTo(file);
                        String pictureName = uploadedFile.getOriginalFilename();
                        System.out.println("pictureName  " + pictureName);
                        String cntntTyp = uploadedFile.getContentType();
                        System.out.println("cntntTyp  " + cntntTyp);

                        ExpensePicture ep = new ExpensePicture();
                        System.out.println("currentDate  :: saved date " + currentDate);
                        System.out.println("------------------------------- ");
                        try {

                            ep.setExpBillData(uploadedFile.getBytes());

                        } catch (IOException e) {
                            System.out.println("Catch block : " + e.getMessage());
                        }
                        System.out.println("------------------------------- ");
                        ep.setCreatedDate(currentDate);
                        ep.setPictureName(pictureName);
                        ep.setMimeType(cntntTyp);
                        ep.setExpense(addedExpense);
                        ep.setPrefix(DIFFERENCIATE_BILL);
                        ep.setBillPath(CREATED_DIRECTORY);
                        expenseService.saveExpenseBills(ep);

                    }

                } catch (IllegalStateException e) {
                    System.out.println("Expense billable  : FALSE " + e.getMessage());
                }

                System.out.println(" -------------------------------------- ");
            } else {
                System.out.println("Expense billable  : FALSE ");
                System.out.println("So you don't need to save bills ");
            }

      emailService.sendHTML_ExpenseMail(
          empName,
          emp.getEmail(),
          addedExpense.getExp_amount(),
          addedExpense.getExp_id(),
          empName,
          addedExpense.getExpenseStatus().getExpStatus_Name());

            mv.addObject("success", true);
            mv.addObject("payModeList", commonService.getAllPaymentMode());
            mv.addObject("allVendorsList", commonService.getAllVendors());
            mv.addObject("locationList", commonService.getAllLocations());
            mv.addObject("expenseTypeList", expenseService.viewAllExpenseType());
            mv.addObject("employeeRoleId", emp.getEmpRole().getId());
            mv.addObject("mode", "Add");
            mv.addObject("message", "Expense successfully created.");
            mv.addObject("expense", new Expense());
        }
        System.out.println(" Finally comes here..");

        return mv;
    }

    @RequestMapping(value = "/viewPerticularExpense/{expense_Id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView viewPerticularExpense(@PathVariable("expense_Id") int expense_Id) throws UnsupportedEncodingException {
        System.out.println("comming expense id to view " + expense_Id);
        ModelAndView mv = new ModelAndView();

        Expense expense = expenseService.getExpenseById(expense_Id);
        if (expense != null) {
            List<ExpensePicture> epBls;
            System.out.println("employe id-->" + expense.getEmployee().getId());
            String imgPath;

            Employee employee = loggedInEmployee();
            System.out.println(employee.getEmpRole().getId());
            System.out.println(expense.getExpenseStatus().getExpStatus_Id() == 5);
            int logedInempId = employee.getId();
            if (expense.getEmployee().getId() == logedInempId
                    || expense.getEmployee().getSubimitter_To().getId() == logedInempId
                    || expense.getEmployee().getApprover_To().getId() == logedInempId
                    || (employee.getEmpRole().getId() == 6 && (expense.getExpenseStatus().getExpStatus_Id() == 4
                    || expense.getExpenseStatus().getExpStatus_Id() == 5))) {

                System.out.println(" In side if condition ");
                mv.addObject("aboutExpense", expense);

                if (expense.getExpenseStatus().getExpStatus_Id() == 6) {
                    System.out.println("this expense now is in rejected state");
                    mv.addObject("rejextExpDetails", expenseService.getExpenseRejectReasonData(expense.getExp_id()));

                }
                if (expense.getBillable()) {
                    System.out.println("find attached bill details.");
                    epBls = expenseService.getAllBillsPerExpense(expense);
                    System.out.println(" Attached bills count " + epBls.size());
                    if (epBls != null) {
                        System.out.println("find attached bill details-- " + epBls.size());
                        for (ExpensePicture ep : epBls) {

                            byte[] encodeBase64 = Base64.encodeBase64(ep.getExpBillData());

                            String base64Encoded = "";
                            System.out.println(" Inside 1");
                            try {
                                base64Encoded = new String(encodeBase64, "UTF-8");
                                System.out.println("=============DONE==============");
                            } catch (NumberFormatException eee) {
                                String message = eee.getMessage();
                                System.out.println("Exception message " + message);
                            }
                            //ep.setBillPath(ep.getBillPath().substring(50, ep.getBillPath().length()));
                            ep.setBase64(base64Encoded);
                            imgPath = ep.getBillPath().substring(50, ep.getBillPath().length());
                            mv.addObject("imgPath", imgPath);
                        }
                        System.out.println("==========================Bills found=========================");
                        mv.addObject("expAttachedBills", epBls);
                    } else {
                        System.out.println("------------Bills not found ----------------------------");
                        mv.addObject("expAttachedBills", "");
                    }
                } else {
                    System.out.println("Bills was not attached with this expense.");
                }

                /*
                 * TO Set view Reject and approved
                 * */
                mv.setViewName("reports/viewparticularExpense");
            } else {
                mv = new ModelAndView("redirect:/wrongAccess");
            }

            mv.addObject("employeeRoleId", employee.getEmpRole().getId());
            mv.addObject("payModeList", commonService.getAllPaymentMode());
            System.out.println("coming expense details " + expense.getExp_id());
        } else {
            mv = new ModelAndView("redirect:/404");
        }
        return mv;
    }

    @RequestMapping(value = "/submitPerticularExpense/{expense_Id}", method = RequestMethod.GET)
    public String submitPerticularExpense(@PathVariable("expense_Id") int expense_Id) {
        System.out.println("coming expense id to view " + expense_Id);

        Expense ex = expenseService.getExpenseById(expense_Id);
        if (ex != null) {
            System.out.println("Expense created by :::::: " + ex.getEmployee().getfName());
            int oldStatus = ex.getExpenseStatus().getExpStatus_Id();
            System.out.println("oldStatus :::::: " + oldStatus);
            Employee employee = loggedInEmployee();
            int logedInEmployeeId = employee.getId();
            Date currentDate = new Date();
            if ((ex.getEmployee().getId() != logedInEmployeeId)
                    || (ex.getEmployee().getId() == logedInEmployeeId
                    && oldStatus == 1)) {

                if (oldStatus <= 5) {
                    int newStatus = oldStatus + 1;

                    switch (newStatus) {
                        case 2:
                            ex.setExp_submittedDate(currentDate);
                            ex.setExp_submitByEmpId(logedInEmployeeId);
                            break;
                        case 3:
                            ex.setExp_approvedDate(currentDate);
                            ex.setExp_approveByEmpId(logedInEmployeeId);
                            break;
                        case 4:
                            ex.setExp_auditedDate(currentDate);
                            ex.setExp_auditByEmpId(logedInEmployeeId);
                            break;
                        case 5:
                            ex.setExp_reimbursedDate(currentDate);
                            ex.setExp_rembrsByEmpId(logedInEmployeeId);
                            break;
                        default:
                            break;
                    }

                    ExpenseStatus es = expenseService.getExpenseStatusDetailsById(newStatus);
                    ex.setExpenseStatus(es);
                    expenseService.saveExpense(ex);
                }
            } else {
                System.out.println("Loged in employee id:::::: " + logedInEmployeeId);
                System.out.println("Both are same and expense not in created state."
                        + " that's you don't have rights to approved your own bolls. ");
            }
            Employee emp = employeeService.getEmployeeById(ex.getEmployee().getId());
            String empName = employeeFullName(emp);

            String byName = employeeFullName(loggedInEmployee());
            System.out.println(empName + " This empliyee is differ from loged in emp " + logedInEmployeeId);
            /*    emailService.sendHTML_ExpenseMail(
                empName, emp.getEmail(), ex.getExp_amount(),
                ex.getExp_id(), byName,
                ex.getExpenseStatus().getExpStatus_Name()
            );
             */
            return "redirect:/viewPerticularExpense/" + expense_Id;
        } else {
            return "redirect:/404";
        }
    }

    @RequestMapping(value = "/expense/approved/{exp_id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> expenseApproved(ModelMap model, @PathVariable String exp_id) {
        System.out.println("inside rejectExpenseReasonForms");
        Map<String, String> resp = new HashMap<>();

        System.out.println("TO APPROVED EXPense " + exp_id);

        int id = Integer.parseInt(exp_id);
        Expense ex = expenseService.getExpenseById(id);
        if (ex != null) {
            System.out.println("Expense created by :::::: " + ex.getEmployee().getfName());
            int oldStatus = ex.getExpenseStatus().getExpStatus_Id();
            System.out.println("oldStatus :::::: " + oldStatus);
            int logedInEmployeeId = loggedInEmployee().getId();

            if (oldStatus == 5) {
                System.out.println("this is already in reimbursed state ");
                resp.put("success", "false");
                resp.put("message", exp_id + "Already reimbursed !!");
            } else {
                Date currentDate = new Date();
                if ((ex.getEmployee().getId() != logedInEmployeeId)
                        || (ex.getEmployee().getId() == logedInEmployeeId
                        && oldStatus == 1)) {

                    if (oldStatus < 5) {
                        int newStatus = oldStatus + 1;

                        switch (newStatus) {
                            case 2:
                                ex.setExp_submittedDate(currentDate);
                                ex.setExp_submitByEmpId(logedInEmployeeId);
                                break;
                            case 3:
                                ex.setExp_approvedDate(currentDate);
                                ex.setExp_approveByEmpId(logedInEmployeeId);
                                break;
                            case 4:
                                ex.setExp_auditedDate(currentDate);
                                ex.setExp_approveByEmpId(logedInEmployeeId);
                                break;
                            case 5:
                                ex.setExp_reimbursedDate(currentDate);
                                ex.setExp_rembrsByEmpId(logedInEmployeeId);
                                break;
                            default:
                                break;
                        }

                        System.out.println("now increese");
                        ExpenseStatus es = expenseService.getExpenseStatusDetailsById(newStatus);
                        ex.setExpenseStatus(es);
                        expenseService.saveExpense(ex);

                        Employee emp = employeeService.getEmployeeById(ex.getEmployee().getId());
                        String empName = employeeFullName(emp);

                        String byName = employeeFullName(loggedInEmployee());
                        System.out.println(empName + " This empliyee is differ from loged in emp " + logedInEmployeeId);
                        /* emailService.sendHTML_ExpenseMail(
                        empName, emp.getEmail(), ex.getExp_amount(),
                        ex.getExp_id(), byName,
                        ex.getExpenseStatus().getExpStatus_Name()
                    );*/
                        resp.put("success", "true");
                    }
                } else {
                    System.out.println("Loged in employee id:::::: " + logedInEmployeeId);
                    System.out.println("Both are same and expense not in created state."
                            + " that's you don't have rights to approved your own bolls. ");

                    resp.put("success", "false");
                    resp.put("message", "You can not approve own expense !!");
                }

            }

        } else {
            resp.put("success", "false");
        }

        return resp;

    }

    @RequestMapping(value = "/expense/rejectExpenseReasonForm", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> rejectReason(ModelMap model, HttpServletRequest request) {
        System.out.println("inside rejectExpenseReasonForms");
        Map<String, String> resp = new HashMap<>();
        String expense_id = request.getParameter("expense_id").trim();
        String reject_reason = request.getParameter("reject_reason").trim();
        System.out.println(" id " + expense_id);
        System.out.println(" ireject_reasond " + reject_reason);
        Date currentDate = new Date();

        Employee employee = loggedInEmployee();
        ExpenseReject expenseReject = new ExpenseReject();

        expenseReject.setExp_reject_By(employeeFullName(employee));
        expenseReject.setExp_reject_Date(currentDate);
        expenseReject.setExp_reject_reason(reject_reason);
        expenseReject.setExpense_Id(Integer.valueOf(expense_id));

        expenseService.updateExpAsReject(expenseReject);

        // expenseService.rejectReasonOfExpense(ids,request.getParameter("reason").trim());
        resp.put("success", "true");
        return resp;
    }

    @RequestMapping(value = "/rejectExpense/{expense_id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> editExpenseId(ModelMap model, @PathVariable int expense_id) {
        System.out.println("comes here Controller ------------>" + expense_id);
        Map<String, Object> resp = new HashMap<>();

        Expense ex = expenseService.getExpenseById(expense_id);

        System.out.println("Expense created by :::::: " + ex.getEmployee().getfName());
        resp.put("success", "true");
        resp.put("id", ex.getExp_id());
        return resp;
    }

    /*
     *Below method not in use now it is used when
     *Check box start working
     *
     */
    @RequestMapping(value = "/submitExpense", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView submitExpense(ModelMap model, HttpServletRequest request) {

        Map<String, String> resp = new HashMap<>();
        String expenseIds[] = request.getParameterValues("expenseIds[]");
        List<Integer> ids = null;
        if (expenseIds != null) {
            ids = new ArrayList<>();
            for (String expenseId : expenseIds) {
                ids.add(Integer.parseInt(expenseId.trim()));
            }
        }
        expenseService.submitExpenseToNextLavel(ids);
        ModelAndView mv = new ModelAndView("expense/createNew");

        mv.addObject("payModeList", commonService.getAllPaymentMode());
        mv.addObject("allVendorsList", commonService.getAllVendors());
        mv.addObject("locationList", commonService.getAllLocations());
        mv.addObject("expenseTypeList", expenseService.viewAllExpenseType());
        mv.addObject("mode", "Add");
        return mv;
    }

    @RequestMapping(value = {"/activityMonitoring"}, method = RequestMethod.GET)
    public ModelAndView activityMonitr() {
        System.out.println("comes here..activityMonitr");
        ModelAndView mv = new ModelAndView("reports/monitor");
        mv.addObject("employeeRoleId", loggedInEmployee().getEmpRole().getId());
        mv.addObject("allExpStatus", expenseService.viewAllExpenseStatus());
        return mv;
    }

    @RequestMapping(value = {"/activityMonitoring"}, method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView activityMonitoring(HttpServletRequest request) {
        System.out.println("comes here to do monitor");
        ModelAndView mv = new ModelAndView("reports/monitor");
        String exStatusID = request.getParameter("performedActivity").trim();

        String fromDateStr = request.getParameter("from_date").trim();
        String toDateSrt = request.getParameter("to_date").trim();

        List<Expense> expenseList = new ArrayList<>();
        mv.addObject("success", false);
        if (toDateSrt.isEmpty() || fromDateStr == null || toDateSrt == null || fromDateStr.isEmpty()) {
            System.out.println("Inside if");

            mv.addObject("message", "Both Date required !!");
        } else {
            System.out.println("elside else ");
            Date fromDate = stringToDate(fromDateStr);
            Date toDate = stringToDate(toDateSrt);

            if (fromDate.after(toDate)) {
                mv.addObject("message", "From date would't greater then To be future date. ");
            } else {
                if (isFutureDate(toDate)) {
                    mv.addObject("message", "Future date not allowed");
                } else {
                    expenseList = activityBetweenDte(Integer.parseInt(exStatusID), fromDate, toDate);
                    mv.addObject("success", true);
                }
            }
            int sum = 0;
            for (Expense exp : expenseList) {
                sum = (int) (sum + exp.getExp_amount());
            }

            mv.addObject("expenses", expenseList);
            mv.addObject("grandTotal", sum);
            mv.addObject("expenseTypeList", expenseService.viewAllExpenseType());
        }
        mv.addObject("employeeRoleId", loggedInEmployee().getEmpRole().getId());
        mv.addObject("allExpStatus", expenseService.viewAllExpenseStatus());
        mv.addObject("ckActivity", exStatusID);
        mv.addObject("from_date", fromDateStr);
        mv.addObject("to_date", toDateSrt);

        return mv;
    }

    @RequestMapping(value = "/getPDFFile/{exStatusID}/{fromDate}/{toDate}", method = RequestMethod.GET)
    public void downloadPDFFile(HttpServletResponse response, @PathVariable("exStatusID") String exStatusID,
            @PathVariable("fromDate") String fromDateStr,
            @PathVariable("toDate") String toDateSrt) throws Exception {

        System.out.println(exStatusID);
        System.out.println(fromDateStr);
        System.out.println(toDateSrt);

        Date fromDate = stringToDate(fromDateStr);
        Date toDate = stringToDate(toDateSrt);
        List<Expense> expenseList = activityBetweenDte(Integer.parseInt(exStatusID), fromDate, toDate);

    }

    @RequestMapping(value = "/getXLSXFile/{exStatusID}/{fromDate}/{toDate}", method = RequestMethod.GET)
    public void downloadXLSXFile(HttpServletResponse response, @PathVariable("exStatusID") String exStatusID,
            @PathVariable("fromDate") String fromDateStr,
            @PathVariable("toDate") String toDateSrt) throws Exception {

        System.out.println(exStatusID);
        System.out.println(fromDateStr);
        System.out.println(toDateSrt);

        Date fromDate = stringToDate(fromDateStr);
        Date toDate = stringToDate(toDateSrt);

        XSSFWorkbook wb = null;
        try {
            int clkID = Integer.parseInt(exStatusID);
            List<Expense> expenseList = activityBetweenDte(clkID, fromDate, toDate);

            int sum = 0;
            for (Expense exp : expenseList) {
                sum = (int) (sum + exp.getExp_amount());
            }

            String colNameInReport = myReportName(clkID);
            wb = this.xlsxReport.generateXlsx(expenseList, clkID, colNameInReport, sum);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment; filename=Approve/Audit_Expense.xlsx");
            wb.write(response.getOutputStream());
        } catch (IOException ioe) {
            throw new RuntimeException("Error writing spreadsheet to output stream");
        } finally {
            if (wb != null) {
                wb.close();
            }
        }
    }

    private List<Expense> activityBetweenDte(int ckStatusId, Date fromDate, Date toDate) {
        List<Expense> expenseList = new ArrayList<>();

        Employee employee = loggedInEmployee();


        /*
         *Below code to including 1 day (current day as well)
         *
         */
        System.out.println("$$$---> B4 date " + toDate);
        toDate = addDays(toDate, 1);
        System.out.println("$$$---> Now date " + toDate);

        if (ckStatusId == 1) {
            expenseList = expenseService.fetchAllexpCreatedByMe(employee);
        } else {
            System.out.println("Get expense list according to selected activity b/w date.");
            expenseList = expenseService.getAllInspectexpByMEAndExpStatusID(employee.getId(), ckStatusId);
        }
        System.out.println("Below block is common to execute for all size " + expenseList.size());
        List<Expense> tempExp = new ArrayList<>();

        if (expenseList.size() > 0) {
            for (Expense exp : expenseList) {

                switch (ckStatusId) {
                    case 1:
                        if (exp.getExp_createdDate() != null) {
                            if (!(exp.getExp_createdDate().after(fromDate) && exp.getExp_createdDate().before(toDate))) {
                                System.out.println("Removing created " + exp.getExp_id());
                                tempExp.add(exp);
                            }
                        }
                        break;
                    case 2:
                        if (exp.getExp_submittedDate() != null) {
                            if (!(exp.getExp_submittedDate().after(fromDate) && exp.getExp_submittedDate().before(toDate))) {
                                System.out.println("Removing submitted " + exp.getExp_id());
                                tempExp.add(exp);
                            }
                        }
                        break;
                    case 3:
                        if (exp.getExp_approvedDate() != null) {
                            if (!(exp.getExp_approvedDate().after(fromDate) && exp.getExp_approvedDate().before(toDate))) {
                                System.out.println("Removing approved " + exp.getExp_id());
                                tempExp.add(exp);
                            }
                        }
                        break;
                    case 4:
                        if (exp.getExp_auditedDate() != null) {
                            if (!(exp.getExp_auditedDate().after(fromDate) && exp.getExp_auditedDate().before(toDate))) {
                                System.out.println("Removing audited " + exp.getExp_id());
                                tempExp.add(exp);
                            }
                        }
                        break;
                    case 5:
                        if (exp.getExp_rejectedDate() != null) {
                            if (!(exp.getExp_reimbursedDate().after(fromDate) && exp.getExp_reimbursedDate().before(toDate))) {
                                System.out.println("Removing getExp_reimbursedDate " + exp.getExp_id());
                                tempExp.add(exp);
                            }
                        }
                        break;
                    default:
                        if (exp.getExp_rejectedDate() != null) {
                            if (!(exp.getExp_rejectedDate().after(fromDate) && exp.getExp_rejectedDate().before(toDate))) {
                                System.out.println("Removing getExp_reimbursedDate " + exp.getExp_id());
                                tempExp.add(exp);
                            }
                        }
                        break;
                }
            }
        }

        expenseList.removeAll(tempExp);
        System.out.println("Final list size" + expenseList.size());

        return expenseList;

    }

}
