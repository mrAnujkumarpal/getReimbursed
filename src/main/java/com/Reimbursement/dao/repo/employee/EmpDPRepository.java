package com.Reimbursement.dao.repo.employee;

import com.Reimbursement.models.empModel.EmpDP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpDPRepository extends JpaRepository<EmpDP, String> {

    @Query("from EmpDP where employee_id=?1")
    EmpDP findByEmployee_id(int employee_id);

}
