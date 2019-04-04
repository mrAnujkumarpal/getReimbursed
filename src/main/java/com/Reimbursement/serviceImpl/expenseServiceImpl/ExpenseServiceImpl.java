package com.Reimbursement.serviceImpl.expenseServiceImpl;

import com.Reimbursement.dao.expenseDaoService.ExpenseDaoService;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.expense.*;
import com.Reimbursement.service.expenseService.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {


    @Autowired
    private ExpenseDaoService expenseDaoService;

    @Override
    public List<Expense> getAllExpenseByExpStatus(ExpenseStatus es) {
        return expenseDaoService.getExpListByExpStatus(es);
    }

    @Override
    public List<ExpenseType> viewAllExpenseType() {
        return expenseDaoService.fetchAllExpenseType();
    }

    @Override
    public void addExpenseType(ExpenseType expenseType) {
        expenseDaoService.saveNewExpenseType(expenseType);
    }

    @Override
    public ExpenseType getExpenseTypeById(int expType_Id) {
        return expenseDaoService.getExpenseTypeByExpTypeID(expType_Id);
    }

    @Override
    public void deleteExpenseTypeById(ExpenseType et) {
        expenseDaoService.makeDisableExpenseType(et);
    }

    @Override
    public List<ExpenseStatus> viewAllExpenseStatus() {
        return expenseDaoService.findAllEnabledExpenseStatus();
    }

    @Override
    public void addExpenseStatus(ExpenseStatus es) {
        expenseDaoService.saveNewExpenseStatus(es);
    }

    @Override
    public Expense saveExpense(Expense ex) {
        return expenseDaoService.saveMyExpense(ex);
    }

    @Override
    public ExpenseStatus getExpenseStatusDetailsById(int esId) {
        return expenseDaoService.findExpenseStatusDetailsById(esId);
    }

    @Override
    public void saveExpenseBills(ExpensePicture eps) {
        expenseDaoService.saveBills(eps);
    }

    @Override
    public List<Expense> getAllExpenseRelatedToMe(Employee employee, ExpenseStatus es) {
        return expenseDaoService.getAllExpRelatedToMe(employee, es);
    }

    @Override
    public List<Expense> fetchAllexpCreatedByMe(Employee employee) {
        return expenseDaoService.expanseCreatedByeMe(employee);
    }

    @Override
    public Expense getExpenseById(int exp_Id) {
        return expenseDaoService.getExpenseByExpId(exp_Id);
    }


    @Override
    public void submitExpenseToNextLavel(List<Integer> expenseIds) {

        for (Integer id : expenseIds) {
            Expense expense = new Expense();
            expense = expenseDaoService.getExpenseByExpId(id);

            int lastStatusId = expense.getExpenseStatus().getExpStatus_Id();
            lastStatusId = lastStatusId + 1;

            ExpenseStatus es = expenseDaoService.findExpenseStatusDetailsById(lastStatusId);
            expense.setExpenseStatus(es);

            expenseDaoService.saveMyExpense(expense);
        }
    }

    @Override
    public void updateExpAsReject(ExpenseReject ex){
        System.out.println(" inside expense service imp class ");
        expenseDaoService.saveExpenseRejectReason(ex);
    }

    @Override
    public List<ExpenseReject> getExpenseRejectReasonData(int expenseID){
        return  expenseDaoService.fetchExpenseRejectReasonDetails(expenseID);
    }

    @Override
    public  List<ExpensePicture> getAllBillsPerExpense(Expense expense){
        return  expenseDaoService.fetchAllAttechedBillWithExpense(expense);
    }

    @Override
    public int getSumAmountOfEmpByExpStatus(Employee employee, ExpenseStatus es){
        return  expenseDaoService.getSumAmountOfEmpByExpStatus(employee,es);
    }

    @Override
    public  int getMyPendingAmount(Employee employee){
        return  expenseDaoService.fetchMyPendingAmountExpense(employee);
    }

    @Override
    public  boolean isExpenseTypeExist(ExpenseType et){
        return  expenseDaoService.findExpenseTypeByName(et.getExpType_Name());
    }

    @Override
    public List<Expense> getAllInspectexpByMEAndExpStatusID(int empId, int expeStatusId) {
        return expenseDaoService.allInspectExpByMEAndexpStatus(empId, expeStatusId);
    }
}
