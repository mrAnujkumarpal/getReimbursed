package com.Reimbursement.controllers.site;
/**
 * Created by Anuj Kumar.
 */

import com.Reimbursement.models.empModel.EmpDP;
import com.Reimbursement.models.empModel.EmployeeRole;
import com.Reimbursement.service.commonServices.EmailService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.Reimbursement.controllers.validation.Validate;
import com.Reimbursement.models.commonModel.Vendor;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.service.commonServices.CommonService;
import com.Reimbursement.service.empService.EmployeeService;
import com.Reimbursement.service.expenseService.ExpenseService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController extends Validate {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ExpenseService expenseService;

    @RequestMapping("/home")
    public ModelAndView commanHome() {
        ModelAndView mv = new ModelAndView("common/home");
        return mv;
    }

    @RequestMapping("/viewAllEmployees")
    public ModelAndView viewAllEmployees() {

        ModelAndView mv = new ModelAndView("employee/allEmployees");


        mv.addObject("roleList", employeeService.getAllEmployeeRoles());
        mv.addObject("locationList", commonService.getAllLocations());
        mv.addObject("allEmployeesList", employeeService.getAllEmployees());
        mv.addObject("employeeRoleId", logedInEmployee().getEmpRole().getId());

        return mv;
    }

    @RequestMapping(value = "/empRegistration", method = RequestMethod.GET)
    public ModelAndView employeeRegistration() {
        ModelAndView mv = new ModelAndView("employee/registration");
        Employee employee = new Employee();
/*

        List<Employee> empls = employeeService.getAllEmployees();

        System.out.println(" B4 remove size " + empls.size());

        for (Employee emp : empls) {
            if ((emp.getEmpRole().getId() == 1))
                empls.remove(emp);
        }


        System.out.println(" after remove size " + empls.size());
*/

        mv.addObject("roleList", employeeService.getAllEmployeeRoles());
        mv.addObject("locationList", commonService.getAllLocations());
        mv.addObject("allEmployeesList", employeeService.getAllEmployees());
        mv.addObject("employeeRoleId", logedInEmployee().getEmpRole().getId());
        mv.addObject("employee", employee);

        return mv;
    }

    @RequestMapping(value = "/empRegistration", method = RequestMethod.POST)
    public ModelAndView employeeRegistration(ModelMap model, @ModelAttribute Employee employee) {
        ModelAndView mv = new ModelAndView();
        System.out.println("Inside methods.......................... ");

        Map<String, String> validateEmployee = validateEmployee(employee);
        String success = validateEmployee.get("success");
        String message = validateEmployee.get("message");

        if (success.equalsIgnoreCase("false")) {

            mv.addObject("success", success);
            mv.addObject("message", message);
            mv.addObject("employee", employee);

            mv.addObject("roleList", employeeService.getAllEmployeeRoles());
            mv.addObject("locationList", commonService.getAllLocations());
            mv.addObject("allEmployeesList", employeeService.getAllEmployees());
            mv.setViewName("employee/registration");
        } else {
            System.out.println("My id " + employee.getId());
            if (employee.getId() == 0) {
                Employee isMailIdExit = employeeService.isEmlIdExist(employee.getEmail());
                System.out.println("mens new employee got isMailIdExit " + isMailIdExit);

                if (isMailIdExit != null) {
                    System.out.println(" isMailIdExit " + true);

                    message = "alreadyRegistered\", \"" +
                            "Oops!  There is already a user registered with the email provided.";
                    success = "false";
                    mv.addObject("success", success);
                    mv.addObject("message", message);
                    mv.addObject("employee", employee);

                    mv.addObject("roleList", employeeService.getAllEmployeeRoles());
                    mv.addObject("locationList", commonService.getAllLocations());
                    mv.addObject("allEmployeesList", employeeService.getAllEmployees());
                    mv.setViewName("employee/registration");
                }
            }

            System.out.println(" isMailIdExit " + false);
            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date currentDate = new Date();
            System.out.println("current Date ::" + df.format(currentDate));

            employee.setCreatedDate(currentDate);
            employee.setPassword(employee.getPassword());
            employee.setorg_code(ORG_CODE);
            employeeService.addEmployee(employee);
            System.out.println("-------------------------Fetching start--------------------------------");

            String userName = employeeFullName(employee);
            System.out.println("userName " + userName );
            System.out.println("now going to send email... "  +  employee.getEmail());
            emailService.sendHTML_RegistrationMail(userName, employee.getEmail());
            mv.setViewName("redirect:/viewAllEmployees");
        }
        System.out.println(":::::::::::::::::::::::::::::::::::::::::");
        return mv;
    }


    @RequestMapping(value = {"viewEmployeeDetails/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView viewEmployeeDetails(@PathVariable("id") int employeeId) {
        System.out.println("comming id " + employeeId);
       ModelAndView mv = new ModelAndView();

        Employee employee = employeeService.getEmployeeById(employeeId);
        List<Integer> myTeamMembersID=new ArrayList<>();
            List<Employee> myTeamMembers = employeeService.getMyTeamMembers(logedInEmployee().getId());
                for(Employee myTeam: myTeamMembers)
                    myTeamMembersID.add(myTeam.getId());


            System.out.println(" employee find  :: " + employee);
        if(myTeamMembersID.contains(employeeId)) {
            EmpDP empDP = employeeService.findDPByEmployeeId(employeeId);
            if (empDP != null) {
                byte[] encodeBase64 = Base64.encodeBase64(empDP.getEmpDPData());
                String base64Encoded = "";
                try {
                    base64Encoded = new String(encodeBase64, "UTF-8");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                empDP.setBase64(base64Encoded);
            }

            //1. sum(amount) exp.status_Id=2;  for created
            int crTDAmount = expenseService.getSumAmountOfEmpByExpStatus(employee, expenseService.getExpenseStatusDetailsById(1));
            mv.addObject("crTDAmount", crTDAmount);

            //2. sum(amount) exp.status_Id=3; for remaining all
            int penDNGAmnt = expenseService.getMyPendingAmount(employee);
            mv.addObject("penDNGAmnt", penDNGAmnt);

            //3. sum(amount) exp.status_Id=4; for rembuirshment
            int rmbSDAmount = expenseService.getSumAmountOfEmpByExpStatus(employee, expenseService.getExpenseStatusDetailsById(5));
            mv.addObject("rmbSDAmount", rmbSDAmount);

            int employeeRoleId = employee.getEmpRole().getId();
            System.out.println("employeeRoleId " + employeeRoleId);
            EmployeeRole er = employeeService.getEmployeeRoleByRoleId(employeeRoleId);
            if(logedInEmployee().getId()== employee.getId()){
                mv.addObject("showUploadDpForm",true);
            }
            mv.addObject("empImage", empDP);
            mv.addObject("employeeRole", er.getEmpRole());
            mv.addObject("employeeRoleId", employeeRoleId);
            mv.addObject("employeeData", employee);
            mv.setViewName("employee/viewEmployee");
        }else{
            mv.setViewName("redirect:/wrongAccess");
        }
        return mv;
    }

    @RequestMapping(value = "/editEmployeeDetails/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editEmployeeDetails(@PathVariable("id") int employeeId) {
        System.out.println("comming id for edit" + employeeId);
        ModelAndView mv = new ModelAndView("employee/registration");
        Employee employee = employeeService.getEmployeeById(employeeId);

        System.out.println("comming id from DB ::" + employee.getId());

        mv.addObject("roleList", employeeService.getAllEmployeeRoles());
        mv.addObject("allEmployeesList", employeeService.getAllEmployees());
        mv.addObject("locationList", commonService.getAllLocations());
        mv.addObject("employee", employee);

        return mv;
    }

    @RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> deleteEmployee(@PathVariable("id") int employeeId) {
        Map<String, String> response = new HashMap<>();
        System.out.println("Employee id which want to delete  ----> " + employeeId);

        Employee employee = employeeService.getEmployeeById(employeeId);

        System.out.println(employeeId + " this id comes fromUI now going to delete employeee " + employee.getId());
        employeeService.deleteEmployee(employee);

        response.put("success", "true");
        return response;
    }


    @RequestMapping(value = "/viewEmployeeDetails/doUploadEmpDP", method = RequestMethod.POST)
    public ModelAndView doUploadEmpDP(@RequestParam("empPhoto") MultipartFile fileUpload) {

        System.out.println("Comes here..........doUploadEmpDP:");

        ModelAndView mv = new ModelAndView("employee/uploadEmployeeDp");
        Employee employee= logedInEmployee();
        if (fileUpload.getSize()> 0 && (!fileUpload.getOriginalFilename().isEmpty()) ) {
            System.out.println("Saving file name : " + fileUpload.getOriginalFilename());
            System.out.println("Saving file size : " + fileUpload.getSize());
            EmpDP empDP = new EmpDP();
            empDP.setEmpDPName(fileUpload.getOriginalFilename());
            empDP.setEmpDPType(fileUpload.getContentType());
            try {
                empDP.setEmpDPData(fileUpload.getBytes());
            } catch (Exception e) {
                System.out.println("Catch block : " + e.getMessage());
            }

            EmpDP  fetchedEmpDp= employeeService.findDPByEmployeeId(employee.getId());
            if(fetchedEmpDp!=null){
                fetchedEmpDp.setEmpDPName(fileUpload.getOriginalFilename());
                fetchedEmpDp.setEmpDPType(fileUpload.getContentType());
                try {
                    fetchedEmpDp.setEmpDPData(fileUpload.getBytes());
                } catch (Exception e) {
                    System.out.println("FetchedEmpDp Catch block : " + e.getMessage());
                }
                employeeService.addEmpPhoto(fetchedEmpDp);
            }else{
                employeeService.addEmpPhoto(empDP);
            }

        }else{
            System.out.println("Select any image to upload.");
        }

        EmpDP edp= employeeService.findDPByEmployeeId(employee.getId());
        byte[] encodeBase64 = Base64.encodeBase64(edp.getEmpDPData());
        String base64Encoded = "";
        try {
            base64Encoded = new String(encodeBase64, "UTF-8");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        edp.setBase64(base64Encoded);
        System.out.println(edp.getEmpDPName() + " - " + edp.getEmpDPType());



        /*
        List<EmpDP> edp = employeeService.getAllEmpDPDetails();
        System.out.println(" All detaila " + edp.size());
        for (EmpDP e : edp) {
            byte[] encodeBase64 = Base64.encodeBase64(e.getEmpDPData());
            String base64Encoded = "";
            try {
                base64Encoded = new String(encodeBase64, "UTF-8");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.setBase64(base64Encoded);
            System.out.println(e.getEmpDPName() + " - " + e.getEmpDPType());
        }
        mv.addObject("dpData", edp);
        */
        mv.addObject("employeeRoleId",employee.getEmpRole().getId());
        mv.addObject("dpData", edp);
        mv.addObject("uploadStatus", true);
        mv.addObject("uploadMsg", "Employee photo uploaded successfully.");
        return mv;
    }

    /*
            @RequestMapping(value = "/doUploadEmpDP", method = RequestMethod.POST)
            public ModelAndView doUploadEmpDP(HttpServletRequest request,
                                              @RequestParam CommonsMultipartFile[] fileUpload) throws Exception {

                System.out.println("Comes here..........:");

                ModelAndView mv = new ModelAndView("employee/uploadEmployeeDp");

                if (fileUpload != null && fileUpload.length > 0) {
                    for (CommonsMultipartFile aFile : fileUpload) {

                        System.out.println("Saving file name : " + aFile.getOriginalFilename());
                        System.out.println("Saving file size : " + aFile.getSize());


                        EmpDP empDP = new EmpDP();

                        empDP.setEmpDPName(aFile.getOriginalFilename());
                        empDP.setEmpDPType(aFile.getContentType());
                        empDP.setEmpDPData(aFile.getBytes());


                        employeeService.addEmpPhoto(empDP);
                    }
                }

                return mv;
            }
    */
    @RequestMapping("/uploadDp")
    public ModelAndView doHome() {
        ModelAndView mv = new ModelAndView("employee/uploadEmployeeDp");
        List<EmpDP> edp = employeeService.getAllEmpDPDetails();

        System.out.println(" All detaila " + edp.size());
        for (EmpDP e : edp) {
            byte[] encodeBase64 = Base64.encodeBase64(e.getEmpDPData());
            String base64Encoded = "";
            try {
                base64Encoded = new String(encodeBase64, "UTF-8");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.setBase64(base64Encoded);
            System.out.println(e.getEmpDPName() + " - " + e.getEmpDPType());
        }

        mv.addObject("dpData", edp);
        return mv;
    }

    @RequestMapping(value = "/myTeamMembers/{employeeId}")
    public ModelAndView myTeamMembers(@PathVariable("employeeId") int employeeId) {
        ModelAndView mv = new ModelAndView();
        List<Integer> myTeamMembersID=new ArrayList<>();
        List<Employee> myTeamMembers = employeeService.getMyTeamMembers(logedInEmployee().getId());
        for(Employee myTeam: myTeamMembers)
            myTeamMembersID.add(myTeam.getId());

        if(myTeamMembersID.contains(employeeId)) {
            mv.setViewName("employee/allEmployees");
              myTeamMembers = employeeService.getMyTeamMembers(employeeId);

            mv.addObject("roleList", employeeService.getAllEmployeeRoles());
            mv.addObject("locationList", commonService.getAllLocations());
            mv.addObject("allEmployeesList",sortedListBasedOnID( myTeamMembers));
            mv.addObject("employeeRoleId", logedInEmployee().getEmpRole().getId());
        }else{
            mv.setViewName("redirect:/wrongAccess");
        }
        return mv;
    }


}



