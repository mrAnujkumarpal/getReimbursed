package com.Reimbursement.daoImpl.expenseDaoImpl;

import com.Reimbursement.dao.expenseDaoService.ExpenseDaoService;
import com.Reimbursement.dao.repo.expense.*;
import com.Reimbursement.models.empModel.Employee;
import com.Reimbursement.models.expense.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ExpenseDaoServiceImpl implements ExpenseDaoService {

    @Autowired
    ExpenseTypeRepository expenseTypeRepository;

    @Autowired
    ExpenseStatusRepository expenseStatusRepository;

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    ExpensePictureRepository expensePictureRepository;

    @Autowired
    ExpenseRejectRepository expenseRejectRepository;

    @Override
    public List<Expense> allInspectExpByMEAndexpStatus(int empId, int expeStatusId) {

        List<Expense> al = new ArrayList<>();

        switch (expeStatusId) {
            case 2:
                al = expenseRepository.findAllSubmitExpenseByMeAndExpenseStatus(empId);
                break;
            case 3:
                al = expenseRepository.findAllApproveExpenseByMeAndExpenseStatus(empId);
                break;
            case 4:
                al = expenseRepository.findAllAuditExpenseByMeAndExpenseStatus(empId);
            case 5:
                al = expenseRepository.findAllReimburseExpenseByMeAndExpenseStatus(empId);
                break;
            case 6:
                al = expenseRepository.findAllRejectExpenseByMeAndExpenseStatus(empId);
                break;
            default:
                break;
        }

        System.out.println("inside expDaoServiceImpl " + al.size());
        return al;
    }

    @Override
    public List<Expense> getExpListByExpStatus(ExpenseStatus es) {
        return expenseRepository.findAllExpenseByExpenseStatus(es);
    }

    @Override
    public List<ExpenseType> fetchAllExpenseType() {
        return expenseTypeRepository.findAllByEnabled(true);
    }

    @Override
    public void saveNewExpenseType(ExpenseType expenseType) {
        expenseTypeRepository.save(expenseType);
    }

    @Override
    public ExpenseType getExpenseTypeByExpTypeID(int expType_Id) {
        return expenseTypeRepository.findOne(expType_Id);
    }

    @Override
    public void makeDisableExpenseType(ExpenseType et) {
        System.out.println("expense dao service impl to make disable to  make disbale expense type");
        et.setEnabled(false);
        expenseTypeRepository.save(et);
    }

    @Override
    public List<ExpenseStatus> findAllEnabledExpenseStatus() {
        return expenseStatusRepository.findAllByEnabled(true);
    }

    @Override
    public void saveNewExpenseStatus(ExpenseStatus es) {
        expenseStatusRepository.save(es);
    }

    @Override
    public Expense saveMyExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public ExpenseStatus findExpenseStatusDetailsById(int id) {
        return expenseStatusRepository.findOne(id);
    }

    @Override
    public void saveBills(ExpensePicture expensePicture) {
        expensePictureRepository.save(expensePicture);
    }

    @Override
    public List<Expense> getAllExpRelatedToMe(Employee employee, ExpenseStatus es) {
        return expenseRepository.findAllExpenseByEmployeeAndExpenseStatus(employee, es);
    }

    @Override
    public List<Expense> expanseCreatedByeMe(Employee employee) {
        return expenseRepository.findAllCreatedByMeee(employee);
    }

    @Override
    public Expense getExpenseByExpId(int exp_Id) {
        return expenseRepository.findOne(exp_Id);
    }

    @Override
    public void saveExpenseRejectReason(ExpenseReject er) {
        System.out.println(" inside expense dao service imps");

        expenseRejectRepository.save(er);
    }

    @Override
    public List<ExpenseReject> fetchExpenseRejectReasonDetails(int id) {
        return expenseRejectRepository.findAllRelatedGivenExpense(id);
    }

    @Override
    public List<ExpensePicture> fetchAllAttechedBillWithExpense(Expense expense) {
        return expensePictureRepository.findAllAttechedBillWithExpense(expense);
    }

    @Override
    public boolean findExpenseTypeByName(String etName) {
        return expenseTypeRepository.findExpenseTypeByName(etName) != null;
    }

    @Override
    public int getSumAmountOfEmpByExpStatus(Employee employee, ExpenseStatus es) {

        List<Expense> exp = expenseRepository.findAllExpenseByEmployeeAndExpenseStatus(employee, es);
        int sum = 0;
        if (exp != null) {
            for (Expense e : exp) {
                sum = sum + (int) e.getExp_amount();
            }
        }
        // return  expenseDaoService.getCreatedSumAmountOfEmp(employee);
        return sum;
    }

    @Override
    public int fetchMyPendingAmountExpense(Employee employee) {
        int sum = 0;
        List<Expense> exp = new ArrayList<>();
        for (int i = 2; i < 5; i++) {
            ExpenseStatus es = findExpenseStatusDetailsById(i);
            sum = sum + getSumAmountOfEmpByExpStatus(employee, es);
        }
        return sum;
    }

}
