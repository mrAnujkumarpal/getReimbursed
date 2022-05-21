package com.Reimbursement.service.expenseService;


import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.expense.*;

import java.util.List;

public interface ExpenseService {

    public List<ExpenseType> viewAllExpenseType();

    public void addExpenseType(ExpenseType expenseType);

    public ExpenseType getExpenseTypeById(int expType_Id);

    public void deleteExpenseTypeById(ExpenseType et);

    public List<ExpenseStatus> viewAllExpenseStatus();

    public void addExpenseStatus(ExpenseStatus es);

    public Expense saveExpense(Expense expense);

    public List<Expense> allExpenseByExpStatus(ExpenseStatus es);

    public List<Expense> allExpenseRelatedToMe(Employee employee, ExpenseStatus es);

    public List<Expense> expensesCreatedByMe(Employee employee);

    public List<Expense> getAllInspectexpByMEAndExpStatusID(int empId, int expStatusId);

    public Expense getExpenseById(int exp_Id);

    public void submitExpenseToNextLevel(List<Integer> expenseIds);

    public void updateExpAsReject(ExpenseReject er);

    public List<ExpenseReject> getExpenseRejectReasonData(int expenseID);

    public  List<ExpensePicture> getAllBillsPerExpense(Expense expense);

    public int getSumAmountOfEmpByExpStatus(Employee employee, ExpenseStatus es);

    public  int getMyPendingAmount(Employee employee);

    public  boolean isExpenseTypeExist(ExpenseType et);

    public ExpenseStatus expenseStatusDetails(int esId);

    public void saveExpenseBills(ExpensePicture ep);

}
