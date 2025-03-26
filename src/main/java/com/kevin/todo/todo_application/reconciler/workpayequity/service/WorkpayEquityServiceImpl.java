//package com.kevin.todo.todo_application.reconciler.workpayequity.service;
//
//import com.kevin.todo.todo_application.reconciler.equitybank.model.EquityBankStatement;
//import com.kevin.todo.todo_application.reconciler.workpayequity.model.WorkpayEquityStatement;
//import com.kevin.todo.todo_application.reconciler.workpayequity.repository.WorkpayEquityRepository;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.LinkedList;
//import java.util.List;
//
//@Service
//public class WorkpayEquityServiceImpl {
//
//    private static final int SHEET_ONE_INDEX = 0;
//    private static final int HEADER_ROW_INDEX = 0;
//
//    private static final int DATE_COLUMN_INDEX = 0;
//    private static final int API_REFERENCE_COLUMN_INDEX = 2;
//    private static final int AMOUNT_COLUMN_INDEX = 6;
//
//    private static final Logger log = LoggerFactory.getLogger( WorkpayEquityServiceImpl.class);
//    private WorkpayEquityRepository workpayEquityRepository;
//
//
//    public  WorkpayEquityServiceImpl(WorkpayEquityRepository workpayEquityRepository) {
//        this.workpayEquityRepository = workpayEquityRepository;
//    }
//
//    /**
//     * Reads an Excel file and saves the transactions in the database.
//     *
//     * This method assumes that the Excel file has a header row, and that the columns are in the following order:
//     *  - Date
//     *  - API Reference
//     *  - Amount
//     *
//     * The method ignores the header row, and for each row, it checks if the cell value is null. If it is, it sets the
//     * corresponding field in the {@link WorkpayEquityStatement} object to null. If it is not null, it tries to parse the
//     * cell value as a {@link LocalDate} and sets the corresponding field in the object to the parsed date.
//     *
//     * If the cell value cannot be parsed as a {@link LocalDate}, the method logs an error and sets the field to null.
//     *
//     * The method then tries to parse the API Reference column. If it starts with "TPG", it splits the value using "/" as the
//     * delimiter and takes the fourth element as the API Reference. If it starts with "IFT", it splits the value using "_" as
//     * the delimiter and takes the first element, removes the first three characters and sets the API Reference to the
//     * resulting string. If it contains "CHARGE" or starts with "JENGA", it sets the API Reference to "charges".
//     *
//     * The method then sets the debit and credit fields in the object to the cell values in the debit and credit columns
//     * respectively. If the cell value is null, it sets the field to 0.0.
//     *
//     * Finally, the method sets the reconciled field to false and adds the object to a list. After all the rows have been
//     * processed, it saves the list of objects to the database.
//     *
//     * @param file the Excel file to read
//     * @throws IOException if there is an error reading the file
//     */
//    public void saveWorkpayEquityStatement(InputStream  file) throws IOException {
//        List<WorkpayEquityStatement> workpayEquityStatementList = new LinkedList<>();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        Workbook workbook = WorkbookFactory.create(file);
//        Sheet sheet = workbook.getSheetAt(SHEET_ONE_INDEX);
//
//        sheet.forEach(row -> {
//            WorkpayEquityStatement workpayEquityStatement = new WorkpayEquityStatement();
//
//                if (row.getRowNum() != HEADER_ROW_INDEX) {
//
//                    try{
//                        if (row.getCell(DATE_COLUMN_INDEX) == null) {
//                            workpayEquityStatement.setTransactionDate(null);
//                        } else {
//                            LocalDate transactionDate = LocalDate.parse(row.getCell(DATE_COLUMN_INDEX).toString(), formatter);
//                            workpayEquityStatement.setTransactionDate(transactionDate);
//                        }
//                    }
//                    catch (Exception e){
//                        log.error(e.getMessage());
//                    }
//
//                    try{
//                        if (row.getCell(API_REFERENCE_COLUMN_INDEX) == null) {
//                            workpayEquityStatement.setReference(null);
//                        } else {
//                            BigDecimal item = new BigDecimal(row.getCell(API_REFERENCE_COLUMN_INDEX).getNumericCellValue());
//                            String apiReference = item.toString();
//                            workpayEquityStatement.setReference(apiReference);
//                        }
//                    }catch (Exception e){
//                        log.error(e.getMessage());
//                    }
//
//                    try {
//                        if (row.getCell(AMOUNT_COLUMN_INDEX) == null) {
//                            workpayEquityStatement.setDebit(0.0);
//                        } else {
//                            Double amount = row.getCell(AMOUNT_COLUMN_INDEX).getNumericCellValue();
//                            workpayEquityStatement.setDebit(amount);
//                        }
//                    }catch (Exception e){
//                        log.error(e.getMessage());
//                    }
//
//                    workpayEquityStatement.setReconciled(false);
//
//                    workpayEquityStatementList.add(workpayEquityStatement);
//
//                } else {
//                    log.info("This is the header row");
//                }
//        } );
//
//        workpayEquityRepository.saveAll(workpayEquityStatementList);
//    }
//
//    /**
//     * Returns a list of all {@link WorkpayEquityStatement}s in the database.
//     *
//     * @return A list of all {@link WorkpayEquityStatement}s in the database.
//     */
//    public List<WorkpayEquityStatement> findAll() {
//        return workpayEquityRepository.findAll();
//    }
//
//    /**
//     * Returns a list of {@link WorkpayEquityStatement}s from the database where the reference is not null.
//     *
//     * @return A list of {@link WorkpayEquityStatement}s with non-null references.
//     */
//    public List<WorkpayEquityStatement> findNonNullReferences() {
//        return workpayEquityRepository.findByReferenceIsNotNull();
//    }
//
//    /**
//     * Updates the reconciled status of a {@link WorkpayEquityStatement} in the database.
//     *
//     * @param workpayEquityStatement The {@link WorkpayEquityStatement} to update. Only the id and isReconciled
//     *                              status are used. The other fields are ignored.
//     */
//    public void updateWorkpayEquityStatement(WorkpayEquityStatement workpayEquityStatement) {
//        WorkpayEquityStatement workpayEquityStatementToUpdate = workpayEquityRepository.findById(workpayEquityStatement.getId()).get();
//        workpayEquityStatementToUpdate.setReconciled(workpayEquityStatement.isReconciled());
//        workpayEquityRepository.save(workpayEquityStatementToUpdate);
//    }
//
//    /*
//    public List<WorkpayEquityStatement> findReconciledWorkpayEquityItems() {
//        return workpayEquityRepository.findByisReconciled(true);
//    }
//    */
//
//    public Page<WorkpayEquityStatement> findReconciledWorkpayEquityItems(Pageable pageable) {
//        return workpayEquityRepository.findByisReconciled(true, pageable);
//    }
//
//    public Page<WorkpayEquityStatement> findUnReconciledWorkpayEquityItems(Pageable pageable) {
//        return workpayEquityRepository.findByisReconciled(false, pageable);
//    }
//
//    public List<WorkpayEquityStatement>getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
//        return workpayEquityRepository.findBytransactionDateBetween(startDate, endDate);
//    }
//}