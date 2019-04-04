package com.Reimbursement.serviceImpl.empServiceImpl;


import com.Reimbursement.dao.employeeDaoService.EmployeeDaoService;
import com.Reimbursement.models.empModel.EmpDP;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.empModel.EmployeeRole;
import com.Reimbursement.service.empService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeDaoService employeeDaoService;

    @Override
    public List<Employee> myTeamMembersTL(int empId) {
        return employeeDaoService.myTeamAsTl(empId);
    }

    @Override
    public List<Employee> myTeamMembersManager(int empId) {
        return employeeDaoService.myTeamAsManager(empId);
    }


    public void addEmployee(Employee employee) {
         employeeDaoService.addEmployee(employee);
    }

    @Override
    public List<EmployeeRole> getAllEmployeeRoles() {
        return employeeDaoService.getAllEmployeeRoles();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDaoService.getAlEmployees();
    }


    @Override
    public Employee getEmployeeById(int employeeId) {
        return employeeDaoService.getEmployeeById(employeeId);
    }

    @Override
    public EmployeeRole getEmployeeRoleByRoleId(int roleId) {
        return employeeDaoService.getEmployeeRoleByRoleId(roleId);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeDaoService.deleteEmployee(employee);
    }

    @Override
    public void addEmpPhoto(EmpDP empDP) {

        employeeDaoService.addEmpPhoto(empDP);
    }

    @Override
    public Employee isEmlIdExist(String empMailId) {
        return employeeDaoService.isEmployeeMailIDExist(empMailId);
    }

    @Override
    public List<EmpDP> getAllEmpDPDetails() {
        return employeeDaoService.getAllEmpDPs();
    }

    @Override
    public void deleteEmployeeRole(int roleId) {
        employeeDaoService.deleteEmployeeRoleByID(roleId);
    }

    @Override
    public void addNewRole(EmployeeRole employeeRole) {
        employeeDaoService.addNewEmployeeRole(employeeRole);
    }

    @Override
    public EmpDP findDPByEmployeeId(int empId) {
        return employeeDaoService.findEmpDPByEmployeeID(empId);
    }

    @Override
    public List<Employee> getMyTeamMembers(int empl_Id){ return employeeDaoService.findMyTeamMembers(empl_Id);}


    @Override
    public boolean isEmployeeRoleExist(EmployeeRole employeeRole){ return employeeDaoService.findEmployeeRoleByName(employeeRole.getEmpRole());}


}
