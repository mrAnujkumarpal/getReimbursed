package com.Reimbursement.dao.repo.expense;

import com.Reimbursement.models.expense.ExpenseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseStatusRepository extends JpaRepository<ExpenseStatus, Integer> {


    List<ExpenseStatus> findAllByEnabled(boolean enabled);


}
