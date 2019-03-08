package com.Reimbursement.dao.repo.employee;


import com.Reimbursement.models.empModel.EmployeeApproverMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeApproverMappingRepository extends JpaRepository<EmployeeApproverMapping, Integer> {


    @Query("from EmployeeApproverMapping where Id=?1")
    List<EmployeeApproverMapping> findBySubmitterToAndEnabled(int Id);


    @Query("from EmployeeApproverMapping where Id=?1")
    List<EmployeeApproverMapping> findByApproverToAndEnabled(int Id);


}
