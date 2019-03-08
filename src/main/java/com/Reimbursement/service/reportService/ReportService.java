package com.Reimbursement.service.reportService;



import com.Reimbursement.models.expense.Expense;

import java.util.List;

public interface ReportService {

    List<Expense> getExpOfEmpByExpenseStatusID(int expStatus_id);
}
