package com.Reimbursement.serviceImpl.expenseServiceImpl;


import com.Reimbursement.models.expense.Expense;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

@Service
public class XlsxReport {



    private static String [] columns = {
        "Expense No."
        , "Expense Date"
        , "Created By"
        , "Expense Name"
        , "Expense Type"
        , "Availed Bill"
        , "Amount"
        , "Team"
    };


    public XSSFWorkbook generateXlsx(List<Expense> expenseList) throws Exception{

        System.out.println(" size of list " + expenseList.size());

        XSSFWorkbook workbook = new  XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Expense list");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        XSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        XSSFRow headerRow = sheet.createRow(0);

        for(int i = 0; i<columns.length; i++){
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(searchUserRole(auth).equals("ADMIN")){
            writeExpenseInToFile(sheet,expenseList);
        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream("Expense.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        return workbook;
    }


    private  String searchUserRole(Authentication auth){
         return "ADMIN";

    }
    private void writeExpenseInToFile(XSSFSheet sheet, List<Expense> expenseList) {

        System.out.println("Inside method....");
        int rowNum = 1;
        for (Expense exp : expenseList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("#00-" + exp.getExp_id());
            row.createCell(1).setCellValue(exp.getExp_Date());
            row.createCell(2).setCellValue(exp.getExp_createdBy());
            row.createCell(3).setCellValue(exp.getExp_name());
            row.createCell(4).setCellValue(exp.getExpenseType().getExpType_Name());

            String isBills = "Not Attached";
            if (exp.getBillable())
                isBills = "Attached";



                row.createCell(5).setCellValue(isBills);
                row.createCell(6).setCellValue(exp.getExp_amount());
                row.createCell(7).setCellValue(exp.getPaymentMode().getPay_type());
            }
        }

}

