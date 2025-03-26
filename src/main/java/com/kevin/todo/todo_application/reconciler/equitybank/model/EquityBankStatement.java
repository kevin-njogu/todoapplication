//package com.kevin.todo.todo_application.reconciler.equitybank.model;
//
//import jakarta.persistence.*;
//import java.time.LocalDate;
//
//@Entity(name = "EquityBankStatement")
//public class EquityBankStatement {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private LocalDate transactionDate;
//    private String  reference;
//    private Double debit;
//    private Double credit;
//    private boolean isReconciled=false;
//
//    public EquityBankStatement() {
//    }
//
//    public EquityBankStatement(LocalDate transactionDate, String reference, Double debit, Double credit) {
//        this.transactionDate = transactionDate;
//        this.reference = reference;
//        this.debit = debit;
//        this.credit = credit;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public LocalDate getTransactionDate() {
//        return transactionDate;
//    }
//
//    public void setTransactionDate(LocalDate transactionDate) {
//        this.transactionDate = transactionDate;
//    }
//
//    public String getReference() {
//        return reference;
//    }
//
//    public void setReference(String reference) {
//        this.reference = reference;
//    }
//
//    public Double getDebit() {
//        return debit;
//    }
//
//    public void setDebit(Double debit) {
//        this.debit = debit;
//    }
//
//    public Double getCredit() {
//        return credit;
//    }
//
//    public void setCredit(Double credit) {
//        this.credit = credit;
//    }
//
//    public boolean isReconciled() {
//        return isReconciled;
//    }
//
//    public void setReconciled(boolean reconciled) {
//        isReconciled = reconciled;
//    }
//
//    @Override
//    public String toString() {
//        return "EquityBankStatement{" +
//                "id=" + id +
//                ", transactionDate=" + transactionDate +
//                ", reference='" + reference + '\'' +
//                ", debit=" + debit +
//                ", credit=" + credit +
//                ", isReconciled=" + isReconciled +
//                '}';
//    }
//}
