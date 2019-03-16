package com.Reimbursement.service.empService;


import com.Reimbursement.models.empModel.EmpDP;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.empModel.EmployeeRole;

import java.util.List;

public interface EmployeeService {

    public void addEmployee(Employee employee);

    public List<Employee> getAllEmployees();

    public Employee getEmployeeById(int employeeId);

    public Employee isEmlIdExist(String mailId);

    public void deleteEmployee(Employee employee);

    public List<Employee> getMyTeamMembers(int empl_Id);

    public List<Employee> myTeamMembersTL(int empId);

    public List<Employee> myTeamMembersManager(int empId);


    public void addEmpPhoto(EmpDP empDP);

    public List<EmpDP> getAllEmpDPDetails();

    public EmpDP findDPByEmployeeId(int empId);


    public List<EmployeeRole> getAllEmployeeRoles();

    public EmployeeRole getEmployeeRoleByRoleId(int empRoleId);

    public void deleteEmployeeRole(int roleId);

    public void addNewRole(EmployeeRole employeeRole);

    public boolean isEmployeeRoleExist(EmployeeRole employeeRole);


}
