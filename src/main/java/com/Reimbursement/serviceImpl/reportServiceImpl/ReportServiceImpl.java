package com.Reimbursement.serviceImpl.reportServiceImpl;


import com.Reimbursement.dao.reportDaoService.ReportDaoService;
import com.Reimbursement.models.expense.Expense;
import com.Reimbursement.service.reportService.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportDaoService reportDaoService;

    @Override
   public List<Expense> getExpOfEmpByExpenseStatusID(int expStatus_id){
        return  reportDaoService.fetchExpOfEmpByExpenseStatusID(expStatus_id);
    }


}
