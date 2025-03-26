package com.kevin.todo.todo_application.reconciler.reconciliation.service;

import com.kevin.todo.todo_application.reconciler.reconciliation.dto.ReconcileRequestDto;
import com.kevin.todo.todo_application.reconciler.uploader.model.EquityBankTransaction;
import com.kevin.todo.todo_application.reconciler.uploader.model.WorkpayEquityTransaction;
import com.kevin.todo.todo_application.reconciler.uploader.repository.EquityBankTransactionRepository;
import com.kevin.todo.todo_application.reconciler.uploader.repository.WorkpayEquityTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReconcilerUtils {
    private final EquityBankTransactionRepository equityBankTransactionRepository;
    private final WorkpayEquityTransactionRepository workpayEquityTransactionRepository;

    public List<EquityBankTransaction> findUnreconciledEquityItems(ReconcileRequestDto reconcileRequestDto) {
        try {
            if (equityBankTransactionRepository == null) {
                throw new IllegalStateException("equityBankTransactionRepository is null");
            }
            if (reconcileRequestDto == null) {
                throw new IllegalArgumentException("reconcileRequestDto is null");
            }
            return equityBankTransactionRepository.findByIsReconciledAndTransactionDateBetween(false, reconcileRequestDto.getStartDate(), reconcileRequestDto.getEndDate());
        } catch (Exception e) {
            log.error("Error finding unreconciled equity items: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<WorkpayEquityTransaction> findUnreconciledWorkpayEquityItems(ReconcileRequestDto reconcileRequestDto) {
        try {
            if (workpayEquityTransactionRepository == null) {
                throw new IllegalStateException("workpayEquityTransactionRepository is null");
            }
            if (reconcileRequestDto == null) {
                throw new IllegalArgumentException("reconcileRequestDto is null");
            }
            return workpayEquityTransactionRepository.findByIsReconciledAndTransactionDateBetween(false, reconcileRequestDto.getStartDate(), reconcileRequestDto.getEndDate());
        } catch (Exception e) {
            log.error("Error finding unreconciled workpay equity items: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void updateWorkpayEquityTransaction(WorkpayEquityTransaction workpayEquityTransaction) {
        WorkpayEquityTransaction transactionToUpdate = workpayEquityTransactionRepository.findById(workpayEquityTransaction.getId()).orElseThrow();
        transactionToUpdate.setReconciled(workpayEquityTransaction.isReconciled());
        workpayEquityTransactionRepository.save(transactionToUpdate);
    }

    public void updateEquityTransaction(EquityBankTransaction equityBankTransaction) {
        if (equityBankTransactionRepository == null) {
            throw new IllegalStateException("equityBankTransactionRepository is null");
        }
        if (equityBankTransaction == null) {
            throw new IllegalArgumentException("equityBankTransaction is null");
        }
        EquityBankTransaction transactionToUpdate = equityBankTransactionRepository.findById(equityBankTransaction.getId()).orElseThrow();
        transactionToUpdate.setReconciled(equityBankTransaction.isReconciled());
        equityBankTransactionRepository.save(transactionToUpdate);
    }
}
