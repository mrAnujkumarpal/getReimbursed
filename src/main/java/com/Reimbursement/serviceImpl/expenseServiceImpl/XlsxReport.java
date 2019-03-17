package com.Reimbursement.serviceImpl.expenseServiceImpl;

import com.Reimbursement.models.expense.Expense;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

@Service
public class XlsxReport {

    public XSSFWorkbook generateXlsx(List<Expense> expenseList, int clkID, String colNameInReport) throws Exception {

        System.out.println(" size of list " + expenseList.size());

        String[] columns = {
            "Expense No.",
            colNameInReport + "Date",
            "Expense Date",
            "Expense Name",
            "Created By",
            "Expense Type",
            "Payment By",
            "Availed Bill",
            "Amount"
        };

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Expense list");
        Font headerFont = workbook.createFont();

        headerFont.setBold(
                true);
        headerFont.setFontHeightInPoints(
                (short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        XSSFCellStyle headerCellStyle = workbook.createCellStyle();

        headerCellStyle.setFont(headerFont);

        XSSFRow headerRow = sheet.createRow(0);

        for (int i = 0;
                i < columns.length;
                i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        writeExpenseInToFile(sheet, expenseList, clkID);

        for (int i = 0;
                i < columns.length;
                i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream("Expense.xlsx")) {
            workbook.write(fileOut);
        }

        return workbook;
    }

    private void writeExpenseInToFile(XSSFSheet sheet, List<Expense> expenseList, int clkID) {

        System.out.println("Inside method....");
        int rowNum = 1;
        for (Expense exp : expenseList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("#00-" + exp.getExp_id());

            switch (clkID) {
                case 1:
                    row.createCell(1).setCellValue(exp.getExp_createdDate());
                    break;
                case 2:
                    row.createCell(1).setCellValue(exp.getExp_submittedDate());
                    break;
                case 3:
                    row.createCell(1).setCellValue(exp.getExp_approvedDate());
                    break;
                case 4:
                    row.createCell(1).setCellValue(exp.getExp_auditedDate());
                    break;
                case 5:
                    row.createCell(1).setCellValue(exp.getExp_reimbursedDate());
                    break;
                case 6:
                    row.createCell(1).setCellValue(exp.getExp_rejectedDate());
                    break;
                default:
                    break;
            }
            row.createCell(2).setCellValue(exp.getExp_Date());
            row.createCell(3).setCellValue(exp.getExp_name());
            row.createCell(4).setCellValue(exp.getExp_createdBy());
            row.createCell(5).setCellValue(exp.getExpenseType().getExpType_Name());
            row.createCell(6).setCellValue(exp.getPaymentMode().getPay_type());
            String isBills = "Not Attached";
            if (exp.getBillable()) {
                isBills = "Attached";
            }
            row.createCell(7).setCellValue(isBills);
            row.createCell(8).setCellValue(exp.getExp_amount());
        }
    }

}
