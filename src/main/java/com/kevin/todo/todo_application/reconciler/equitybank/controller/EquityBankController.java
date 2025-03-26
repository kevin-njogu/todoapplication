//package com.kevin.todo.todo_application.reconciler.equitybank.controller;
//
//import com.kevin.todo.todo_application.reconciler.equitybank.model.EquityBankStatement;
//import com.kevin.todo.todo_application.reconciler.equitybank.service.EquityBankServiceImpl;
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
//public class EquityBankController {
//
//    private EquityBankServiceImpl equityBankService;
//
//    public EquityBankController(EquityBankServiceImpl equityBankService) {
//        this.equityBankService = equityBankService;
//    }
//
//    @PostMapping(value = "/uploadEquityBankStatement", consumes = "multipart/form-data")
//    public ResponseEntity<String> uploadEquityBankStatement(@RequestParam("file")MultipartFile file) throws Exception{
//        equityBankService.saveEquityBankStatement(file.getInputStream());
//        return ResponseEntity.ok("file uploaded successfully");
//    }
//
//    @GetMapping("/getAllEquityBankStatements")
//    public ResponseEntity<?> getAllEquityBankStatements() {
//        return ResponseEntity.ok(equityBankService.findAll());
//    }
//
//    @GetMapping("download-equity-bank/csv")
//    public void downloadCSV(@RequestParam String startDate, @RequestParam String endDate, HttpServletResponse response) throws IOException {
//        response.setContentType("text/csv");
//        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.csv");
//
//        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
//        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
//
//        List<EquityBankStatement> transactions = equityBankService.getTransactionsByDateRange(start, end);
//
//        PrintWriter writer = response.getWriter();
//
//        // Write CSV Header
//        writer.println("Transaction ID,Transaction Date,Reference,Debit,Credit, Match Status");
//
//        // Write Data
//        for (EquityBankStatement  transaction : transactions) {
//            writer.println(transaction.getId() + "," +
//                    transaction.getTransactionDate() + "," +
//                    transaction.getReference() + "," +
//                    transaction.getDebit() + "," +
//                    transaction.getCredit()+ ","+
//                    transaction.isReconciled()
//            );
//        }
//
//        writer.flush();
//    }
//
//}
