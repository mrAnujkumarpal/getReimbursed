package com.Reimbursement.daoImpl.employeeDaoImpl;


import com.Reimbursement.dao.employeeDaoService.EmployeeDaoService;
import com.Reimbursement.dao.repo.employee.EmpDPRepository;
import com.Reimbursement.dao.repo.employee.EmployeeRepository;
import com.Reimbursement.dao.repo.employee.EmployeeRoleRepository;
import com.Reimbursement.models.empModel.EmpDP;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.empModel.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeDaoServiceImpl implements EmployeeDaoService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeRoleRepository employeeRoleRepository;

    @Autowired
    EmpDPRepository empDPRepository;

   @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public List<Employee> myTeamAsTl(int empId) {

        Employee me = employeeRepository.findOne(empId);
        return employeeRepository.findBySubmitterToAndEnabled(me, true);
    }

    @Override
    public List<Employee> myTeamAsManager(int empId) {

        Employee me = employeeRepository.findOne(empId);
        return  employeeRepository.findByApproverToAndEnabled(me, true);
    }

    public void addEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeRole> getAllEmployeeRoles() {
        return employeeRoleRepository.findAll();
    }

    @Override
    public List<Employee> getAlEmployees() {
        return employeeRepository.findByEnabled(true);
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return employeeRepository.findOne(employeeId);
    }

    @Override
    public EmployeeRole getEmployeeRoleByRoleId(int roleId) {
        return employeeRoleRepository.getOne(roleId);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public Employee isEmployeeMailIDExist(String empMailId) {
        return employeeRepository.findByEmail(empMailId);
    }

    @Override
    public void addEmpPhoto(EmpDP empDP) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        empDP.setEmployee_id( employeeRepository.findByEmail(authentication.getName()).getId());
        empDPRepository.save(empDP);
    }

    @Override
    public List<EmpDP> getAllEmpDPs() {
        return empDPRepository.findAll();
    }

    @Override
    public void deleteEmployeeRoleByID(int roleId) {

        employeeRoleRepository.delete(roleId);
    }

    @Override
    public void addNewEmployeeRole(EmployeeRole employeeRole) {
        employeeRoleRepository.save(employeeRole);
    }

    @Override
    public EmpDP findEmpDPByEmployeeID(int empId) {
         System.out.println("$$--> Comming here to fetch dp emloyee service impl " + empId);
        return empDPRepository.findByEmployee_id(empId);
    }

    @Override
    public List<Employee> findMyTeamMembers(int empl_Id) {
        System.out.println("inside meAsTl ");
        Employee me = employeeRepository.findOne(empl_Id);
        List<Employee> meAsTl = employeeRepository.findBySubmitterToAndEnabled(me, true);
        System.out.println("meAsTl.... " + meAsTl.size());
        List<Employee> meAsMngr = employeeRepository.findByApproverToAndEnabled(me, true);
        System.out.println("meAsTl " + meAsTl.size());
        System.out.println("meAsMngr " + meAsMngr.size());
//
        meAsMngr.add(me);
        meAsTl.addAll(meAsMngr);


        return meAsTl;
    }

    @Override
    public boolean findEmployeeRoleByName(String employeeRole){
        //  return locationRepository.findLocationByName(locationName)!=null;
        return  employeeRoleRepository.findEmpRoleByName(employeeRole)!=null;
    }



}