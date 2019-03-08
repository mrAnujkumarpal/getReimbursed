package com.Reimbursement.dao.expenseDaoService;

import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.expense.*;

import java.util.List;

public interface ExpenseDaoService {

    public List<ExpenseType> fetchAllExpenseType();

    public void saveNewExpenseType(ExpenseType expenseType);

    public ExpenseType getExpenseTypeByExpTypeID(int expType_Id);

    public void makeDisableExpenseType(ExpenseType et);

    public List<ExpenseStatus> findAllEnabledExpenseStatus();

    public void saveNewExpenseStatus(ExpenseStatus es);

    public Expense saveMyExpense(Expense ex);

    public ExpenseStatus findExpenseStatusDetailsById(int id);

    public  void saveBills(ExpensePicture expensePicture);

    public List<Expense>  getAllExpRelatedToMe(Employee employee, ExpenseStatus es);

    public  List<Expense>   expanseCreatedByeMe(Employee employee);

    public Expense  getExpenseByExpId(int exp_Id);

    public void saveExpenseRejectReason(ExpenseReject er);

    public List<ExpenseReject> fetchExpenseRejectReasonDetails(int expense_id);

    public  List<ExpensePicture> fetchAllAttechedBillWithExpense(Expense expense);

    public int getSumAmountOfEmpByExpStatus(Employee employee, ExpenseStatus es);

    public  int fetchMyPendingAmountExpense(Employee employee);

    public  boolean findExpenseTypeByName(String etName);
}
