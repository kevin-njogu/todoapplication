package com.kevin.todo.todo_application.reconciler.equitybank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class EquityBankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String reference;
    private LocalDate transactionDate;
    private double debit;
    private double credit;

    public EquityBankTransaction() {}

    public EquityBankTransaction(String reference, LocalDate transactionDate, double debit, double credit) {
        this.reference = reference;
        this.transactionDate = transactionDate;
        this.debit = debit;
        this.credit = credit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "EquityBankTransaction{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", transactionDate=" + transactionDate +
                ", debit=" + debit +
                ", credit=" + credit +
                '}';
    }
}
