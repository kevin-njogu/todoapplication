//package com.kevin.todo.todo_application.reconciler.workpayequity.controller;
//
//
//import com.kevin.todo.todo_application.reconciler.workpayequity.model.WorkpayEquityStatement;
//import com.kevin.todo.todo_application.reconciler.workpayequity.service.WorkpayEquityServiceImpl;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class WorkpayEquityController {
//
//    private WorkpayEquityServiceImpl workpayEquityService;
//
//    public WorkpayEquityController( WorkpayEquityServiceImpl workpayEquityService) {
//        this.workpayEquityService = workpayEquityService;
//    }
//
//    @PostMapping(value = "/workpayEquityStatement", consumes = "multipart/form-data")
//    public ResponseEntity<String> uploadWorkpayEquityStatement(@RequestParam("file")MultipartFile file) throws Exception{
//        workpayEquityService.saveWorkpayEquityStatement(file.getInputStream());
//        return ResponseEntity.ok("file uploaded successfully");
//    }
//
//    @GetMapping("/getAllWorkpayEquityStatements")
//    public ResponseEntity<?> getAllEquityBankStatements() {
//        return ResponseEntity.ok( workpayEquityService.findAll());
//    }
//
//    @GetMapping("download-workpay-equity/csv")
//    public void downloadCSV(@RequestParam String startDate, @RequestParam String endDate, HttpServletResponse response) throws IOException {
//        response.setContentType("text/csv");
//        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.csv");
//
//        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
//        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
//
//        List<WorkpayEquityStatement> transactions =  workpayEquityService.getTransactionsByDateRange(start, end);
//
//        PrintWriter writer = response.getWriter();
//
//        // Write CSV Header
//        writer.println("Transaction ID,Transaction Date,Reference,Debit,Match Status");
//
//        // Write Data
//        for (WorkpayEquityStatement  transaction : transactions) {
//            writer.println(transaction.getId() + "," +
//                    transaction.getTransactionDate() + "," +
//                    transaction.getReference() + "," +
//                    transaction.getDebit() + "," +
//                    transaction.isReconciled()
//            );
//        }
//
//        writer.flush();
//    }
//
//}
