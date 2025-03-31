package com.kevin.todo.todo_application.reconciler.uploader.repository;

import com.kevin.todo.todo_application.reconciler.uploader.model.WorkpayEquityTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkpayEquityTransactionRepository extends JpaRepository<WorkpayEquityTransaction, Long> {
    List<WorkpayEquityTransaction> findByIsReconciledAndTransactionDateBetween(boolean isReconciled, LocalDate startDate, LocalDate endDate);
    List<WorkpayEquityTransaction> findByTransactionDateBetween(LocalDate startDate, LocalDate endDate);
    Page<WorkpayEquityTransaction> findByIsReconciled(boolean isReconciled, Pageable pageable);
}
