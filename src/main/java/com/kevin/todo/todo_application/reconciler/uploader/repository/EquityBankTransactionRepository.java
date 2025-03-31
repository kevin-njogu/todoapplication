package com.kevin.todo.todo_application.reconciler.uploader.repository;

import com.kevin.todo.todo_application.reconciler.uploader.model.EquityBankTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EquityBankTransactionRepository extends JpaRepository<EquityBankTransaction, Long> {
    List<EquityBankTransaction> findByIsReconciledAndTransactionDateBetween(boolean isReconciled, LocalDate startDate, LocalDate endDate);
    List<EquityBankTransaction> findByTransactionDateBetween(LocalDate startDate, LocalDate endDate);
    Page<EquityBankTransaction> findByIsReconciled(boolean isReconciled, Pageable pageable);
}
