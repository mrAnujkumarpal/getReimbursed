package com.Reimbursement.dao.employeeDaoService;


import com.Reimbursement.models.empModel.EmpDP;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.empModel.EmployeeRole;

import java.util.List;


public interface EmployeeDaoService {


    public void addEmployee(Employee employee);

    public Employee getEmployeeById(int employeeId);

    public List<Employee> getAlEmployees();

    public void deleteEmployee(Employee employee);

    public Employee isEmployeeMailIDExist(String empMailId);

    public List<Employee> findMyTeamMembers(int empl_Id);

    public boolean findEmployeeRoleByName(String locationName);

    public  List<Employee> myTeamAsTl(int empId);

    public  List<Employee> myTeamAsManager(int empId);


    public List<EmployeeRole> getAllEmployeeRoles();

    public EmployeeRole getEmployeeRoleByRoleId(int empRoleId);

    public void deleteEmployeeRoleByID(int role_id);

    public void addNewEmployeeRole(EmployeeRole employeeRole);



    public void addEmpPhoto(EmpDP empDP);

    public List<EmpDP> getAllEmpDPs();

    public EmpDP findEmpDPByEmployeeID(int empId);




}
