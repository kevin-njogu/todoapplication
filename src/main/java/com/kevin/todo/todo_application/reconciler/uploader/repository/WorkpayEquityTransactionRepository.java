package com.kevin.todo.todo_application.reconciler.uploader.repository;

import com.kevin.todo.todo_application.reconciler.uploader.model.WorkpayEquityTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkpayEquityTransactionRepository extends JpaRepository<WorkpayEquityTransaction, Long> {
    List<WorkpayEquityTransaction> findByIsReconciledAndTransactionDateBetween(boolean isReconciled, LocalDate startDate, LocalDate endDate);
}
