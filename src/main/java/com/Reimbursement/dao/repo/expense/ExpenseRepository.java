package com.Reimbursement.dao.repo.expense;

import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.expense.Expense;
import com.Reimbursement.models.expense.ExpenseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    @Query(value = "from Expense where employee=?1 and expenseStatus=?2")
    List<Expense> findAllExpenseByEmployeeAndExpenseStatus(Employee employee, ExpenseStatus expenseStatus);


    @Query("from Expense where employee=?1")
    List<Expense> findAllCreatedByMeee(Employee employee);


    @Query("from Expense where expenseStatus=?1")
    List<Expense> findAllExpenseByExpenseStatus(ExpenseStatus expenseStatus);


    @Query("from Expense where exp_rembrsByEmpId=?1")
    List<Expense> findAllReimburseExpenseByMeAnyExpenseStatus(int empId);


    @Query("from Expense where exp_rejectByEmpId=?1")
    List<Expense> findAllRejectExpenseByMeAnyExpenseStatus(int empId);

}
