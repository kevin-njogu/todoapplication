package com.kevin.todo.todo_application.reconciler.controller;

import com.kevin.todo.todo_application.reconciler.dto.DownloadRequestDto;
import com.kevin.todo.todo_application.reconciler.model.EquityBankTransaction;
import com.kevin.todo.todo_application.reconciler.model.WorkpayEquityTransaction;
import com.kevin.todo.todo_application.reconciler.repository.EquityBankTransactionRepository;
import com.kevin.todo.todo_application.reconciler.repository.WorkpayEquityTransactionRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/downloader")
public class DownloadController {

    private final EquityBankTransactionRepository equityBankTransactionRepository;
    private final WorkpayEquityTransactionRepository workpayEquityTransactionRepository;

    public DownloadController(EquityBankTransactionRepository equityBankTransactionRepository, WorkpayEquityTransactionRepository workpayEquityTransactionRepository) {
        this.equityBankTransactionRepository = equityBankTransactionRepository;
        this.workpayEquityTransactionRepository = workpayEquityTransactionRepository;
    }

    @PostMapping("/download")
    public void downloadCSV(@RequestBody DownloadRequestDto downloadRequestDto, HttpServletResponse response) throws IOException {
        if (downloadRequestDto == null || response == null) {
            throw new IllegalArgumentException("DownloadRequestDto or HttpServletResponse is null");
        }
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.csv");
        PrintWriter writer = response.getWriter();
        List<? extends Object> transactions;
        String header;
        if ("equitybank".equals(downloadRequestDto.getAccount())) {
            transactions = equityBankTransactionRepository
                    .findByTransactionDateBetween(downloadRequestDto.getStartDate(), downloadRequestDto.getEndDate());
            header = "Transaction ID,Transaction Date,Reference,Debit,Credit, Match Status";
        } else if ("workpayequity".equals(downloadRequestDto.getAccount())) {
            transactions = workpayEquityTransactionRepository
                    .findByTransactionDateBetween(downloadRequestDto.getStartDate(), downloadRequestDto.getEndDate());
            header = "Transaction ID,Transaction Date,Reference,Debit, Match Status";
        } else {
            throw new IllegalArgumentException("Invalid account type");
        }
        writer.println(header);
        for (Object transaction : transactions) {
            if (transaction instanceof EquityBankTransaction equityTransaction) {
                writer.println(equityTransaction.getId() + "," +
                        equityTransaction.getTransactionDate() + "," +
                        equityTransaction.getReference() + "," +
                        equityTransaction.getDebit() + "," +
                        equityTransaction.getCredit() + "," +
                        equityTransaction.isReconciled());
            } else if (transaction instanceof WorkpayEquityTransaction workpayTransaction) {
                writer.println(workpayTransaction.getId() + "," +
                        workpayTransaction.getTransactionDate() + "," +
                        workpayTransaction.getReference() + "," +
                        workpayTransaction.getDebit() + "," +
                        workpayTransaction.isReconciled());
            } else {
                throw new IllegalStateException("Unexpected transaction type");
            }
        }
        writer.flush();
    }
}
