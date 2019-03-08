package com.Reimbursement.dao.repo.employee;

import com.Reimbursement.models.empModel.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("EmployeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmail(String email);

    List<Employee> findByEnabled(boolean enabled);

    Employee findByEmailAndPasswordAndEnabled(String email, String password, boolean enabled);


    @Query("from Employee where subimitter_To=?1 and enabled=?2")
    List<Employee> findBySubmitterToAndEnabled(Employee subimitter_To, boolean enabled);


    @Query("from Employee where approver_To=?1 and enabled=?2")
    List<Employee> findByApproverToAndEnabled(Employee approver_To, boolean enabled);



}
