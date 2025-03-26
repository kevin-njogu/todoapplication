package com.kevin.todo.todo_application.reconciler.uploader.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equity_bank_transactions")
public class EquityBankTransaction {

    @Id
    @GeneratedValue
    private Long id;
    private String reference;
    private LocalDate transactionDate;
    private double debit;
    private double credit;
    private boolean isReconciled=false;

}
