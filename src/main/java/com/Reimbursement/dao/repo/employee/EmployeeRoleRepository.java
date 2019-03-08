package com.Reimbursement.dao.repo.employee;

import com.Reimbursement.models.empModel.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {

    @Query("from EmployeeRole where Employee_Role=?1")
    EmployeeRole findEmpRoleByName(String LocationName);

}
