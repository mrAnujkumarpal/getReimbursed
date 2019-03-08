package com.Reimbursement.dao.reportDaoService;


import com.Reimbursement.models.expense.Expense;

import java.util.List;

public interface ReportDaoService {

    List<Expense> fetchExpOfEmpByExpenseStatusID(int es_id);
}
