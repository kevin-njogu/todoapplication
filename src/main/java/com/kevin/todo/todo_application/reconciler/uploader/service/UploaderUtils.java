package com.kevin.todo.todo_application.reconciler.uploader.service;

import com.kevin.todo.todo_application.reconciler.uploader.model.EquityBankTransaction;
import com.kevin.todo.todo_application.reconciler.uploader.model.WorkpayEquityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
public class UploaderUtils {
    private static final int HEADER_ROW_INDEX = 0;
    private static final int SHEET_ONE_INDEX = 0;
    private static final int TRANSACTION_DATE_COLUMN_INDEX = 0;
    private static final int REFERENCE_COLUMN_INDEX = 1;
    private static final int DEBIT_COLUMN_INDEX = 2;
    private static final int CREDIT_COLUMN_INDEX = 3;

    public List<EquityBankTransaction> processEquityBankStatement(InputStream file) throws IOException {
        List<EquityBankTransaction> equityBankTransactions = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(SHEET_ONE_INDEX);
        int lastRow = sheet.getLastRowNum();
        for (int i = HEADER_ROW_INDEX + 1; i <= lastRow; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                log.error("Equity Bank Row {} is null", i);
                continue;
            }
            EquityBankTransaction transaction = new EquityBankTransaction();
            Cell transactionDateCell = row.getCell(TRANSACTION_DATE_COLUMN_INDEX);
            try {
                if (transactionDateCell == null) {
                    transaction.setTransactionDate(null);
                } else {
                    LocalDate dateRef = LocalDate.parse(transactionDateCell.getStringCellValue(), formatter);
                    transaction.setTransactionDate(dateRef);
                }
            } catch (Exception e) {
                log.error("Error parsing transaction date for row {}", i, e);
                throw new RuntimeException("error parsing transaction date for row " + i, e);
            }
            Cell referenceCell = row.getCell(REFERENCE_COLUMN_INDEX);
            Cell debitCell = row.getCell(DEBIT_COLUMN_INDEX);
            Cell creditCell = row.getCell(CREDIT_COLUMN_INDEX);
            try {
                if (referenceCell == null) {
                    transaction.setReference(null);
                } else {
                    String reference = referenceCell.getStringCellValue();
                    double credit = creditCell.getNumericCellValue();
                    if (reference.startsWith("TPG") && credit > 0.0) {
                        transaction.setReference(reference);
                    }
                    if (reference.startsWith("TPG") && credit < 1) {
                        String regex = "\\/";
                        transaction.setReference(reference.split(regex)[3]);
                    } else if (reference.startsWith("IFT")) {
                        String regex = "\\_";
                        transaction.setReference(reference.split(regex)[0].replaceFirst("IFT", ""));
                    } else if (reference.startsWith("JENGA") || reference.contains("CHARGE")) {
                        String regex = " ";
                        transaction.setReference("charges - " + reference.split(regex)[3]);
                        transaction.setReconciled(true);
                    } else {
                        transaction.setReference(reference);
                    }
                }
            } catch (Exception e) {
                log.error("Error parsing reference for row {} {}", i, e.getMessage());
                throw new RuntimeException("error parsing reference for row " + i, e);
            }
            try {
                if (debitCell == null) {
                    transaction.setDebit(0.0);
                } else {
                    double debitAmount = debitCell.getNumericCellValue();
                    transaction.setDebit(debitAmount);
                }
            } catch (Exception e) {
                log.error("Error parsing debit for row {}", i, e);
                throw new RuntimeException("error parsing debit for row " + i, e);
            }
            try {
                if (creditCell == null) {
                    transaction.setCredit(0.0);
                } else {
                    double creditAmount = creditCell.getNumericCellValue();
                    transaction.setCredit(creditAmount);
                }
            } catch (Exception e) {
                log.error("Error parsing credit for row {}", i, e);
                throw new RuntimeException("error parsing credit for row " + i, e);
            }
            equityBankTransactions.add(transaction);
        }
        return equityBankTransactions;
    }

    public List<WorkpayEquityTransaction> processWorkpayEquityStatement(InputStream file) throws IOException {
        List<WorkpayEquityTransaction> workpayEquityTransactions = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(SHEET_ONE_INDEX);
        int lastRow = sheet.getLastRowNum();
        for (int i = HEADER_ROW_INDEX + 1; i <= lastRow; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                log.error("Workpay Equity Row {} is null", i);
                continue;
            }
            WorkpayEquityTransaction workpayEquityTransaction = new WorkpayEquityTransaction();
            Cell transactionDateCell = row.getCell(TRANSACTION_DATE_COLUMN_INDEX);
            try {
                if (transactionDateCell == null) {
                    workpayEquityTransaction.setTransactionDate(null);
                } else {
                    LocalDate dateRef = LocalDate.parse(transactionDateCell.getStringCellValue(), formatter);
                    workpayEquityTransaction.setTransactionDate(dateRef);
                }
            } catch (Exception e) {
                log.error("Error parsing transaction date for row {}", i, e);
                throw new RuntimeException("error parsing transaction date for row " + i, e);
            }
            Cell referenceCell = row.getCell(REFERENCE_COLUMN_INDEX);
            try {
                if (referenceCell == null) {
                    workpayEquityTransaction.setReference("NA");
                } else {
                    if (referenceCell.getCellType() == CellType.NUMERIC) {
                        String apiReference = new BigDecimal(referenceCell.getNumericCellValue()).toString();
                        workpayEquityTransaction.setReference(apiReference);
                    } else {
                        workpayEquityTransaction.setReference(referenceCell.getStringCellValue());
                    }
                }
            } catch (Exception e) {
                log.error("Error parsing reference for row {}", i, e);
                throw new RuntimeException("error parsing reference for row " + i, e);
            }
            Cell debitCell = row.getCell(DEBIT_COLUMN_INDEX);
            try {
                if (debitCell == null) {
                    workpayEquityTransaction.setDebit(0.0);
                } else {
                    workpayEquityTransaction.setDebit(debitCell.getNumericCellValue());
                }
            } catch (Exception e) {
                log.error("Error parsing debit for row {}", i, e);
                throw new RuntimeException("error parsing debit for row " + i, e);
            }
            workpayEquityTransactions.add(workpayEquityTransaction);
        }
        return workpayEquityTransactions;
    }
}
