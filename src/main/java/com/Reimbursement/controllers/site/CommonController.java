package com.Reimbursement.controllers.site;
/**
 * Created by Anuj Kumar.
 */

import com.Reimbursement.dao.repo.employee.EmployeeRepository;
import com.Reimbursement.models.commonModel.Location;
import com.Reimbursement.models.empModel.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.Reimbursement.controllers.validation.Validate;
import com.Reimbursement.models.commonModel.Vendor;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.expense.*;
import com.Reimbursement.service.commonServices.CommonService;
import com.Reimbursement.service.empService.EmployeeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class CommonController extends Validate {

    @Autowired
    CommonService commonService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;



    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
       public ModelAndView defaultPage() {
        ModelAndView mv = new ModelAndView("common/login");
        return mv;
    }


    @RequestMapping(value = "/changePwd", method = RequestMethod.GET)
    public ModelAndView changePassword() {
        ModelAndView mv = new ModelAndView("common/changePassword");
        mv.addObject("employeeRoleId",logedInEmployee().getEmpRole().getId());
        return mv;
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ModelAndView changePasswordDoing(

            @RequestParam("oldpassword") String oldPwd,
            @RequestParam("newpassword") String newPwd,
            @RequestParam("confrmpassword") String confrmPwd
    ) {

        ModelAndView mv = new ModelAndView("common/changePassword");
        Map<String, String> resp = new HashMap<>();

        System.out.println("oldPwd ::" + oldPwd);
        System.out.println("newPwd ::" + newPwd);
        System.out.println("confrmPwd ::" + confrmPwd);

        if (!newPwd.equalsIgnoreCase(confrmPwd)) {
            resp.put("success", "false");
            resp.put("message", "New password and confirm password are not match.");
        }
        Employee emp =logedInEmployee();
        if (emp != null) {
            if (!(emp.getPassword().equalsIgnoreCase(oldPwd))) {
                resp.put("success", "false");
                resp.put("message", "Stored password and not same to given old password.");
            }
        }
        mv.addObject("employeeRoleId",emp.getEmpRole().getId());
        emp.setPassword(oldPwd);

        return mv;
    }

    @RequestMapping(value = "/default")
    public ModelAndView deafultAfterLogin(){
        System.out.println(" ------------------------------ ");
        ModelAndView modelAndView = new ModelAndView();


      int empID=logedInEmployee().getId();
        System.out.println("ID " + empID);
        if(empID != 0){
            String url = "viewEmployeeDetails/" + empID;
            System.out.println("url ::" + url);
            modelAndView = new ModelAndView("redirect:" + url);
        }else {
            modelAndView.addObject("employeeRoleId",logedInEmployee().getEmpRole().getId());
            modelAndView.setViewName("errors/404");
             }

        return modelAndView;
    }



    /*
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(ModelMap model, @ModelAttribute Employee employee,HttpSession session) {
        ModelAndView mv = new ModelAndView();
        System.out.println("Inside login method with post");




        String email = employee.getEmail();
        String password = employee.getPassword();


        System.out.println("Email : " + email);
        System.out.println("Password : " + password);
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            mv.addObject("success", "false");
            mv.addObject("message", "Empty email and password  not accepted.");
            mv.setViewName("common/login");

        } else {
            email = email.trim();
            password = generateHash(password.trim());

            Employee emp = commonService.authorize(email, password);
            session.setAttribute("username", emp);

            //    model.put("emp", emp);
            if (emp != null) {


                Object e_ob = session.getAttribute("emp");


                System.out.println("object session At login time " + e_ob);

                System.out.println("Empl id " + emp.getId());
                //   session.setAttribute("Employee", emp);
                String url = "viewEmployeeDetails/" + emp.getId();
                System.out.println("url ::" + url);
                mv = new ModelAndView("redirect:" + url);


                 /*
                String fnme = emp.getfName();
                String lname = emp.getlName();
                fnme = fnme.substring(0, 1).toUpperCase() + fnme.substring(1).toLowerCase();
                lname = lname.substring(0, 1).toUpperCase() + lname.substring(1).toLowerCase();

                String fullName = fnme + " " + lname;
                System.out.println("fullName " + fullName);
                mv.addObject("success", "true");
                mv.addObject("message", "Login Successfully.");
                mv.addObject("loginEmployee", fullName);
                mv.setViewName("common/login");

            } else {
                System.out.println("na mil ro");
                mv.addObject("success", "false");
                mv.addObject("message", "Invalid email and password.");


                mv.setViewName("common/login");
            }
        }
        return mv;
    }

    */
    @GetMapping(value = "/wrongAccess")
    public ModelAndView wrongAccess(){
        System.out.println("Inside wrong access. ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/505");
        modelAndView.addObject("employeeRoleId",logedInEmployee().getEmpRole().getId());
        return modelAndView;
    }

    @GetMapping(value = "/access-denied")
    public ModelAndView accessDenied(){
        System.out.println("Inside accsss denied method. ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/500");
        modelAndView.addObject("employeeRoleId",logedInEmployee().getEmpRole().getId());
        return modelAndView;
    }
    @RequestMapping(value = "/viewAllRoles", method = RequestMethod.GET)
    public ModelAndView fetchAllRoles() {
        ModelAndView mv = new ModelAndView("common/employeeRole");
        List<EmployeeRole> erl = employeeService.getAllEmployeeRoles();
        mv.addObject("roleList", erl);
        mv.addObject("employeeRoleId",logedInEmployee().getEmpRole().getId());
        return mv;
    }

    @RequestMapping(value = "/roles/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addNewRole(ModelMap model, HttpServletRequest request) {
        System.out.println("comes here common Controller ");
        Map<String, String> resp = new HashMap<>();
        String role = request.getParameter("role_Name").trim();
        System.out.println("comes here common Controller " + role);

        EmployeeRole empRl = new EmployeeRole();

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date currentDate = new Date();
        System.out.println("current Date ::" + df.format(currentDate));

        empRl.setCreated_Date(currentDate);
        empRl.setEmpRole(role);

        employeeService.addNewRole(empRl);


        resp.put("success", "true");
        resp.put("message", role + " as new role added successfully.");
        return resp;
    }

    @RequestMapping(value = "/common/editRole/{role_id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> editExistRole(ModelMap model, @PathVariable int role_id) {
        System.out.println("comes here Controller " + role_id);
        Map<String, Object> resp = new HashMap<>();
        EmployeeRole empRoles = employeeService.getEmployeeRoleByRoleId(role_id);
        resp.put("success", "true");
        resp.put("id", empRoles.getId());
        resp.put("role", empRoles.getEmpRole());
        return resp;
    }

    @RequestMapping(value = "/roles/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateExistRole(ModelMap model, HttpServletRequest request) {

        Map<String, String> resp = new HashMap<>();
        String id = request.getParameter("role_Id").trim();
        String role = request.getParameter("role_Name").trim();

        EmployeeRole empRoles = employeeService.getEmployeeRoleByRoleId(Integer.parseInt(id));
        empRoles.setEmpRole(role);

        employeeService.addNewRole(empRoles);

        resp.put("success", "true");
        resp.put("message", "Location successfully updated.");
        return resp;
    }

    @RequestMapping(value = "/common/deleteRole/{role_id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> deleteOrgnigationRole(ModelMap model, @PathVariable int role_id) {
        Map<String, String> resp = new HashMap<>();
        EmployeeRole empRoles = employeeService.getEmployeeRoleByRoleId(role_id);

        System.out.println("Location_id()" + empRoles.getId());
        System.out.println(role_id + " this id comes fromUI now going to delete employeee " + empRoles.getEmpRole());

        employeeService.deleteEmployeeRole(empRoles.getId());
        resp.put("success", "true");
        return resp;
    }

    @RequestMapping(value = "/viewAllLocations", method = RequestMethod.GET)
    public ModelAndView fetchAllLocations() {
        ModelAndView mv = new ModelAndView("common/location");

        mv.addObject("locationList", commonService.getAllLocations());
        mv.addObject("employeeRoleId",logedInEmployee().getEmpRole().getId());
        return mv;
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public ModelAndView error404() {
        ModelAndView mv = new ModelAndView("errors/404");
        mv.addObject("employeeRoleId",logedInEmployee().getEmpRole().getId());
        return mv;
    }

    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public ModelAndView error500() {
        ModelAndView mv = new ModelAndView("errors/500");
        mv.addObject("employeeRoleId",logedInEmployee().getEmpRole().getId());
        return mv;
    }

    @RequestMapping(value = "/locationEvent/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> locationEventAdd(ModelMap model, HttpServletRequest request) {
        System.out.println("comes here common Controller ");
        Map<String, String> resp = new HashMap<>();
        String name = request.getParameter("location_name").trim();
        System.out.println("comes here common Controller " + name);
        Location location = new Location();

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date currentDate = new Date();
        System.out.println("current Date ::" + df.format(currentDate));

        location.setCreated_Date(currentDate);
        location.setCreated_By(employeeFullName(logedInEmployee()));
        location.setLocation_name(name);
        commonService.addLocation(location);


        resp.put("success", "true");
        resp.put("message", name + " as new location successfully added.");
        return resp;
    }

    @RequestMapping(value = "/locationEvent/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> locationEventUpdate(ModelMap model, HttpServletRequest request) {

        Map<String, String> resp = new HashMap<>();
        String name = request.getParameter("location_name").trim();
        String id = request.getParameter("location_Id").trim();

        Location loc = commonService.getLocationById(Integer.parseInt(id));
        loc.setLocation_name(name);
        commonService.addLocation(loc);

        resp.put("success", "true");
        resp.put("message", "Location successfully updated.");
        return resp;
    }


    @RequestMapping(value = "/common/editLocation/{location_id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> editExistLocation(ModelMap model, @PathVariable int location_id) {
        System.out.println("comes here Controller " + location_id);
        Map<String, Object> resp = new HashMap<>();

        Location loc = commonService.getLocationById(location_id);


        resp.put("success", "true");
        resp.put("id", loc.getLocation_id());
        resp.put("name", loc.getLocation_name());
        return resp;
    }


    @RequestMapping(value = "common/deleteLocation/{location_id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> deleteLocation(ModelMap model, @PathVariable int location_id) {
        Map<String, String> resp = new HashMap<>();

        Location loc = commonService.getLocationById(location_id);

        System.out.println("Location_id()" + loc.getLocation_id());
        System.out.println(location_id + " this id comes fromUI now going to delete employeee " + loc.getLocation_name());

        commonService.deleteLocation(loc);
        resp.put("success", "true");
        return resp;
    }

    @RequestMapping(value = "/allVendors", method = RequestMethod.GET)
    public ModelAndView allVendors() {
        ModelAndView mv = new ModelAndView("common/vendors");

        List<Location> locationList = commonService.getAllLocations();
        List<Vendor> vendors = commonService.getAllendors();

        mv.addObject("employeeRoleId",logedInEmployee().getEmpRole().getId());
        mv.addObject("allVendorsList", vendors);
        mv.addObject("locationList", locationList);
        return mv;
    }

    @RequestMapping(value = "/addNewVendor", method = RequestMethod.GET)
    public ModelAndView addNewVendor(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("common/vendorRegistor");
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            mv.addObject("success", true);
            mv.addObject("message", request.getParameter(enumeration.nextElement()));
        }
        List<Location> locationList = commonService.getAllLocations();

        mv.addObject("locationList", locationList);
        mv.addObject("employeeRoleId",logedInEmployee().getEmpRole().getId());
        mv.addObject("mode", "Add");
        return mv;
    }

    @RequestMapping(value = "/addNewVendor", method = RequestMethod.POST)
    public ModelAndView addVendor(ModelMap model, @ModelAttribute Vendor vendor) {
        System.out.println("-------------------------Comes here to save vendor start--------------------------------");

        ModelAndView mv = new ModelAndView();

        Map<String, String> validateVendor = validateVendor(vendor);

        String success = validateVendor.get("success");
        String message = validateVendor.get("message");

        if (success.equalsIgnoreCase("false")) {
            mv.addObject("success", success);
            mv.addObject("message", message);

            List<Location> locationList = commonService.getAllLocations();

            mv.addObject("locationList", locationList);
            mv.addObject("mode", "Add");

            mv.setViewName("common/vendorRegistor");
        } else {
            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date currentDate = new Date();
            System.out.println("current Date ::" + df.format(currentDate));
            vendor.setCreated_Date(currentDate);
            vendor.setCreated_By(employeeFullName(logedInEmployee()));
            commonService.addNewVendor(vendor);

            mv.addObject("success", true);
            mv.addObject("message", "Vendor successfully added.");

            mv.addObject("locationList", commonService.getAllLocations());
            mv.addObject("mode", "Add");
            mv.addObject("employeeRoleId",logedInEmployee().getEmpRole().getId());
            mv.setViewName("common/vendorRegistor");
        }
        return mv;
    }


    @RequestMapping(value = "/editVendorDetails/{vendor_Id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editVendorDetails(@PathVariable("vendor_Id") int vendor_Id) {
        System.out.println("comming id for edit" + vendor_Id);
        ModelAndView mv = new ModelAndView("common/vendorRegistor");
        Vendor vendor = commonService.getVendorDetailsByVendorID(vendor_Id);

        List<Location> locationList = commonService.getAllLocations();
        System.out.println(" vendor address " + vendor.getVendor_address());

        mv.addObject("locationList", locationList);
        mv.addObject("vendor", vendor);
        mv.addObject("mode", "Edit");
        return mv;
    }


    @RequestMapping(value = "/deleteVendor/{vendor_Id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> deleteVendor(ModelMap model, @PathVariable int vendor_Id) {
        Map<String, String> resp = new HashMap<>();

        Vendor vendor = commonService.getVendorDetailsByVendorID(vendor_Id);
        if (vendor != null)
            commonService.deleteVendorByVendorId(vendor);
        resp.put("success", "true");
        return resp;
    }


}
