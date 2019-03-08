package com.Reimbursement.daoImpl.reportDaoServiceImpl;


import com.Reimbursement.dao.repo.expense.ExpenseRepository;
import com.Reimbursement.dao.reportDaoService.ReportDaoService;
import com.Reimbursement.models.expense.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportDaoServiceImpl implements ReportDaoService {


    @Autowired
    ExpenseRepository expenseRepository;

    @Override
   public List<Expense> fetchExpOfEmpByExpenseStatusID(int es_id){
        return expenseRepository.findAll();

    }
}
