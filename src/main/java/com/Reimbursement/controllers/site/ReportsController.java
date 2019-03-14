package com.Reimbursement.controllers.site;


import com.Reimbursement.controllers.validation.Validate;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.expense.Expense;
import com.Reimbursement.models.expense.ExpenseReject;
import com.Reimbursement.models.expense.ExpenseStatus;
import com.Reimbursement.service.commonServices.CommonService;
import com.Reimbursement.service.empService.EmployeeService;
import com.Reimbursement.service.expenseService.ExpenseService;
import com.Reimbursement.service.reportService.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Anuj Kumar on 09/08/2018.
 */

@Controller
public class ReportsController extends Validate {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private EmployeeService employeeService;


    List<Expense> respectiveExpenseHistory(int expStatus_id) {


        List<Expense> el = new ArrayList<>();

        return el;
    }


    @RequestMapping("/expenseHistory/{id}")
    public ModelAndView expenseHistory(@PathVariable("id") int expStatus_id) {
        ModelAndView mv = new ModelAndView();

        ExpenseStatus es = expenseService.getExpenseStatusDetailsById(expStatus_id);
        if (es != null) {
            mv.setViewName("reports/expenseHistory");
            List<Expense> ex = expenseService.getAllExpenseRelatedToMe(logedInEmployee(), es);
            if (ex != null) {

                mv.addObject("payModeList", commonService.getAllPaymentMode());
                mv.addObject("allVendorsList", commonService.getAllVendors());
                mv.addObject("locationList", commonService.getAllLocations());
                mv.addObject("expenseTypeList", expenseService.viewAllExpenseType());
                mv.addObject("reportName", myReportName(expStatus_id));
                mv.addObject("employeeRoleId", logedInEmployee().getEmpRole().getId());
            }
            mv.addObject("expenses", ex);
        } else {
            mv.setViewName("redirect:/wrongAccess");
        }

        return mv;
    }

    @RequestMapping(value = "/expense/findExpenseForReject/{expense_id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findExpenseForReject(ModelMap model, @PathVariable int expense_id) {
        System.out.println("comes here Controller " + expense_id);
        Map<String, Object> resp = new HashMap<>();
        Expense ex = expenseService.getExpenseById(expense_id);
        resp.put("success", "true");
        resp.put("id", ex.getExp_id());
        return resp;
    }


    @RequestMapping(value = "/RejectExpenseDone", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> rejectExpenseDone(ModelMap model, HttpServletRequest request) {
        System.out.println("inside RejectExpenseDone");
        Map<String, String> resp = new HashMap<>();
        String expense_id = request.getParameter("expense_id").trim();
        String exp_reject_reason = request.getParameter("reject_reason").trim();

        ExpenseStatus es = new ExpenseStatus();
        System.out.println("expense_id " + expense_id);
        System.out.println("reject_reason " + exp_reject_reason);

        Expense ex = expenseService.getExpenseById(Integer.parseInt(expense_id));

        System.out.println(" ex not null ");
        if (ex != null) {
            es = expenseService.getExpenseStatusDetailsById(6);
            ex.setExpenseStatus(es);
            expenseService.saveExpense(ex);
            System.out.println(" expense updated ");
            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date currentDate = new Date();
            System.out.println("current Date ::" + df.format(currentDate));

            /*
             *Find Employee from session
             */
            Employee employee = logedInEmployee();
            String rejectBy = employeeFullName(employee);

            ExpenseReject er = new ExpenseReject();

            er.setExp_reject_By(employeeFullName(employee));
            er.setExp_reject_Date(currentDate);
            er.setExp_reject_reason(exp_reject_reason);
            er.setExpense_Id(Integer.parseInt(expense_id));
            System.out.println(" all things are set");

            expenseService.updateExpAsReject(er);
        }
        System.out.println("here...");
        resp.put("success", "true");
        resp.put("message", "This expense successfully rejected.");
        return resp;
    }

    @RequestMapping("/pendingExpenseforApproval")
    public ModelAndView pendingExpenseforApproval() {
        ModelAndView mv = new ModelAndView("reports/pendingExpenseHistory");

        List<Expense> ex = new ArrayList<>();
        ExpenseStatus es = expenseService.getExpenseStatusDetailsById(2);

        List<Employee> myTeamMembers = employeeService.getMyTeamMembers(logedInEmployee().getId());
        System.out.println("myTeamMembers " + myTeamMembers.size());
        myTeamMembers.remove(logedInEmployee());
        System.out.println("removed itself");
        for (Employee emp : myTeamMembers) {
            ex.addAll(expenseService.getAllExpenseRelatedToMe(emp, es));
        }
        System.out.println("Final fetched list size  " + ex.size());

        mv.addObject("payModeList", commonService.getAllPaymentMode());
        mv.addObject("allVendorsList", commonService.getAllVendors());
        mv.addObject("locationList", commonService.getAllLocations());
        mv.addObject("expenseTypeList", expenseService.viewAllExpenseType());
        mv.addObject("employeeRoleId", logedInEmployee().getEmpRole().getId());
        mv.addObject("reportName", "Pending for approval");
        mv.addObject("expenses", ex);
        return mv;
    }


    @RequestMapping("/pendingExpenseforAudit")
    public ModelAndView pendingExpenseforAudit() {

        ModelAndView mv = new ModelAndView("reports/pendingExpenseHistory");
        List<Expense> ex = new ArrayList<>();
        ExpenseStatus es = expenseService.getExpenseStatusDetailsById(3);
        Employee employee = logedInEmployee();

        List<Employee> myTeamMembers = employeeService.getMyTeamMembers(employee.getId());
        myTeamMembers.remove(employee);

        for (Employee emp : myTeamMembers) {
            ex.addAll(expenseService.getAllExpenseRelatedToMe(emp, es));
        }
        System.out.println("Final fetched list size" + ex.size());


        mv.addObject("payModeList", commonService.getAllPaymentMode());
        mv.addObject("allVendorsList", commonService.getAllVendors());
        mv.addObject("locationList", commonService.getAllLocations());
        mv.addObject("expenseTypeList", expenseService.viewAllExpenseType());
        mv.addObject("reportName", "Pending for audit");
        mv.addObject("employeeRoleId", employee.getEmpRole().getId());
        mv.addObject("expenses", ex);
        return mv;
    }

    @RequestMapping("/pendingExpenseforRemburse")
    public ModelAndView pendingExpenseforRemburse() {

        System.out.println("comes here pendingExpenseforRemburse ");
        ModelAndView mv = new ModelAndView("reports/pendingExpenseHistory");
        List<Expense> ex = new ArrayList<>();

        ExpenseStatus es = expenseService.getExpenseStatusDetailsById(4);

        Employee employee = logedInEmployee();
        int empRoleId = employee.getEmpRole().getId();

        if (empRoleId == 5 || empRoleId == 6) {
            System.out.println("yes I am Admin or Finance. ");
        }

       /* List<Employee> myTeamMembers = employeeService.getMyTeamMembers(employee.getId());
        myTeamMembers.remove(employee);

        for (Employee emp : myTeamMembers) {
            ex.addAll(expenseService.getAllExpenseRelatedToMe(emp, es));
        }*/

        ex = expenseService.getAllExpenseRelatedToMe(employee, es);


        System.out.println("Final fetched list size" + ex.size());

        mv.addObject("payModeList", commonService.getAllPaymentMode());
        mv.addObject("allVendorsList", commonService.getAllVendors());
        mv.addObject("locationList", commonService.getAllLocations());
        mv.addObject("expenseTypeList", expenseService.viewAllExpenseType());
        mv.addObject("employeeRoleId", empRoleId);
        mv.addObject("reportName", "Pending for reimburse");
        mv.addObject("expenses", ex);
        return mv;
    }
}
