package com.kevin.todo.todo_application.reconciler.equitybank.service;

import com.kevin.todo.todo_application.reconciler.equitybank.model.EquityBankStatement;
import com.kevin.todo.todo_application.reconciler.equitybank.repository.EquityBankRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Service
public class EquityBankServiceImpl {

    private static final int TRANSACTION_DATE_COLUMN_INDEX = 0;
    private static final int REFERENCE_COLUMN_INDEX = 2;
    private static final int DEBIT_COLUMN_INDEX = 3;
    private static final int CREDIT_COLUMN_INDEX = 4;

    private static final int HEADER_ROW_INDEX = 0;

    private static final int SHEET_ONE_INDEX = 0;


    private static final Logger log = LoggerFactory.getLogger(EquityBankServiceImpl.class);
    private EquityBankRepository equityBankRepository;


    public EquityBankServiceImpl(EquityBankRepository equityBankRepository) {
        this.equityBankRepository = equityBankRepository;
    }

    /**
     * Reads a Excel file and saves the transactions in the database.
     *
     * This method assumes that the Excel file has a header row, and that the columns are in the following order:
     *  - Transaction Date
     *  - Reference
     *  - Debit
     *  - Credit
     *
     * The method ignores the header row, and for each row, it checks if the cell value is null. If it is, it sets the
     * corresponding field in the {@link EquityBankStatement} object to null. If it is not null, it tries to parse the
     * cell value as a {@link LocalDate} and sets the corresponding field in the object to the parsed date.
     *
     * If the cell value cannot be parsed as a {@link LocalDate}, the method logs an error and sets the field to null.
     *
     * The method then tries to parse the reference column. If it starts with "TPG", it splits the value using "/" as the
     * delimiter and takes the fourth element as the reference. If it starts with "IFT", it splits the value using "_" as
     * the delimiter and takes the first element, removes the first three characters and sets the reference to the
     * resulting string. If it contains "CHARGE" or starts with "JENGA", it sets the reference to "charges".
     *
     * The method then sets the debit and credit fields in the object to the cell values in the debit and credit columns
     * respectively. If the cell value is null, it sets the field to 0.0.
     *
     * Finally, the method sets the reconciled field to false and adds the object to a list. After all the rows have been
     * processed, it saves the list of objects to the database.
     *
     * @param file the Excel file to read
     * @throws IOException if there is an error reading the file
     */
    public void saveEquityBankStatement(InputStream  file) throws IOException {
        List<EquityBankStatement> equityBankStatementList = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(SHEET_ONE_INDEX);

        sheet.forEach(row -> {
            EquityBankStatement equityBankStatement = new EquityBankStatement();

            if (row.getRowNum() != HEADER_ROW_INDEX) {

                try {
                    if (row.getCell(TRANSACTION_DATE_COLUMN_INDEX) == null) {
                        equityBankStatement.setTransactionDate(null);
                    } else {
                        LocalDate dateRef = LocalDate.parse(row.getCell(TRANSACTION_DATE_COLUMN_INDEX).toString(), formatter);
                        equityBankStatement.setTransactionDate(dateRef);
                    }
                }
                catch (Exception e) {
                    log.error(e.getMessage());
                }

                try{
                    if(row.getCell(REFERENCE_COLUMN_INDEX) == null) {
                        equityBankStatement.setReference(null);
                    }else if (row.getCell(REFERENCE_COLUMN_INDEX).toString().startsWith("TPG")) {
                        String regex = "\\/";
                        String reference = row.getCell(REFERENCE_COLUMN_INDEX).toString().split(regex)[3];
                        equityBankStatement.setReference(reference);
                    } else if (row.getCell(REFERENCE_COLUMN_INDEX).toString().startsWith("IFT")) {
                        String regex = "\\_";
                        String reference = row.getCell(REFERENCE_COLUMN_INDEX).toString().split(regex)[0].replaceFirst("IFT", "");
                        equityBankStatement.setReference(reference);
                    } else if (row.getCell(REFERENCE_COLUMN_INDEX).toString().startsWith("JENGA") ||
                            row.getCell(REFERENCE_COLUMN_INDEX).toString().contains("CHARGE")) {
                        String regex = " ";
                        String reference  = row.getCell(2).toString().split(regex)[3];
                        equityBankStatement.setReference("charges - " + reference);
                    } else {
                        equityBankStatement.setReference(row.getCell(REFERENCE_COLUMN_INDEX).toString());
                    }
                }
                catch (Exception e) {
                    log.error(e.getMessage());
                }


                try {
                    if (row.getCell(DEBIT_COLUMN_INDEX) == null) {
                        equityBankStatement.setDebit(0.0);
                    } else {
                        String cellValue= row.getCell(DEBIT_COLUMN_INDEX).toString();
                        Double debitAmount = Double.valueOf(cellValue);
                        equityBankStatement.setDebit(debitAmount);
                    }
                } catch (Exception e) {
                    log.error( e.getMessage());
                }

                try {
                    if (row.getCell(CREDIT_COLUMN_INDEX) == null) {
                        equityBankStatement.setCredit(0.0);
                    } else {
                        String cellValue= row.getCell(CREDIT_COLUMN_INDEX).toString();
                        Double creditAmount = Double.valueOf(cellValue);
                        equityBankStatement.setCredit(creditAmount);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

                equityBankStatement.setReconciled(false);

                equityBankStatementList.add(equityBankStatement);

        }else {
                log.info("This is the header row");
            }
        });

        equityBankRepository.saveAll(equityBankStatementList);
    }

    /**
     * Returns a list of all {@link EquityBankStatement}s in the database.
     *
     * @return A list of all {@link EquityBankStatement}s in the database.
     */
    public List<EquityBankStatement> findAll() {
        return equityBankRepository.findAll();
    }

    /**
     * Returns a list of {@link EquityBankStatement}s from the database where the reference is not null.
     *
     * @return A list of {@link EquityBankStatement}s with non-null references.
     */
    public List<EquityBankStatement> findNonNullReferences() {
        return equityBankRepository.findByReferenceIsNotNull();
    }

    /**
     * Updates the reconciled status of an {@link EquityBankStatement} in the database.
     *
     * @param equityBankStatement The {@link EquityBankStatement} to update. Only the id and isReconciled
     *                            status are used. The other fields are ignored.
     */
    public void updateEquityBankStatement(EquityBankStatement equityBankStatement) {
        EquityBankStatement equityBankStatementToUpdate = equityBankRepository.findById(equityBankStatement.getId()).get();
        equityBankStatementToUpdate.setReconciled(equityBankStatement.isReconciled());
        equityBankRepository.save(equityBankStatementToUpdate);
    }

    public List<EquityBankStatement> findAllCredits() {
        return equityBankRepository.findByCreditIsNotNull();
    }

    /*
    public List<EquityBankStatement> findAllCharges() {
        return equityBankRepository.findByReference("charges");
    }
     */

    public Page<EquityBankStatement> findAllCharges(Pageable pageable) {
        return equityBankRepository.findByReferenceContainingIgnoreCase("charges", pageable);
    }

    /*
    public List<EquityBankStatement> findUnreconciledEquityItems () {
        return equityBankRepository.findByIsReconciledAndCreditIsNotNullAndReferenceNot(false,  "charges");
    }*/

    public Page<EquityBankStatement> findUnreconciledEquityItems (Pageable pageable) {
        return equityBankRepository.findByIsReconciledAndCreditIsNotNullAndReferenceNot(false,  "charges", pageable);
    }

    /*
    public List<EquityBankStatement> findReconciledEquityItems () {
        return equityBankRepository.findByIsReconciled(true);
    }*/

    public Page<EquityBankStatement> findReconciledEquityItems (Pageable pageable) {
        return equityBankRepository.findByIsReconciled(true, pageable);
    }

    public List<EquityBankStatement>getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        return equityBankRepository.findBytransactionDateBetween(startDate, endDate);
    }

}