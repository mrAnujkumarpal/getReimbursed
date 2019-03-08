package com.Reimbursement.dao.repo.expense;



import com.Reimbursement.models.expense.Expense;
import com.Reimbursement.models.expense.ExpenseReject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRejectRepository extends JpaRepository<ExpenseReject, Integer> {

    @Query("from ExpenseReject where expense=?1")
    List<ExpenseReject> findAllRelatedGivenExpense(Expense expense);
}
