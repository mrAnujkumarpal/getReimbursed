package com.Reimbursement.dao.repo.expense;


import com.Reimbursement.models.expense.Expense;
import com.Reimbursement.models.expense.ExpensePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpensePictureRepository extends JpaRepository<ExpensePicture, Integer> {


    @Query("from ExpensePicture where expense=?1")
    List<ExpensePicture> findAllAttechedBillWithExpense(Expense expense);
}


