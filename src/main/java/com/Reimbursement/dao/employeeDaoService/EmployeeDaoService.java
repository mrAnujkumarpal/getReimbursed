package com.Reimbursement.dao.employeeDaoService;


import com.Reimbursement.models.empModel.EmpDP;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.empModel.EmployeeRole;

import java.util.List;


public interface EmployeeDaoService {


    public void addEmployee(Employee employee);

    public List<EmployeeRole> getAllEmployeeRoles();

    public List<Employee> getAlEmployees();

    public Employee getEmployeeById(int employeeId);

    public EmployeeRole getEmployeeRoleByRoleId(int empRoleId);

    public void deleteEmployee(Employee employee);

    public Employee isEmployeeMailIDExist(String empMailId);

    public void addEmpPhoto(EmpDP empDP);

    public List<EmpDP> getAllEmpDPs();

    public void deleteEmployeeRoleByID(int role_id);

    public void addNewEmployeeRole(EmployeeRole employeeRole);

    public EmpDP findEmpDPByEmployeeID(int empId);

    public List<Employee> findMyTeamMembers(int empl_Id);

    public boolean findEmployeeRoleByName(String locationName);
}
