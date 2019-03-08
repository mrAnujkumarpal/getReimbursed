package com.Reimbursement.dao.repo.expense;

import com.Reimbursement.models.expense.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Integer> {


    List<ExpenseType> findAllByEnabled(boolean enabled);

    @Query("from ExpenseType where expense_type_Name=?1")
    ExpenseType findExpenseTypeByName(String etName);
}
