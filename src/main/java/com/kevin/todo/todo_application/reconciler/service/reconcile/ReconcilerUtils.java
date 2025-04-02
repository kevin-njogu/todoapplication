package com.kevin.todo.todo_application.reconciler.service.reconcile;

import com.kevin.todo.todo_application.reconciler.dto.ReconcileRequestDto;
import com.kevin.todo.todo_application.reconciler.model.EquityBankTransaction;
import com.kevin.todo.todo_application.reconciler.model.WorkpayEquityTransaction;
import com.kevin.todo.todo_application.reconciler.repository.EquityBankTransactionRepository;
import com.kevin.todo.todo_application.reconciler.repository.WorkpayEquityTransactionRepository;
import org.slf4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReconcilerUtils {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ReconcilerUtils.class);
    private final EquityBankTransactionRepository equityBankTransactionRepository;
    private final WorkpayEquityTransactionRepository workpayEquityTransactionRepository;

    public ReconcilerUtils(EquityBankTransactionRepository equityBankTransactionRepository,
                           WorkpayEquityTransactionRepository workpayEquityTransactionRepository) {
        this.equityBankTransactionRepository = equityBankTransactionRepository;
        this.workpayEquityTransactionRepository = workpayEquityTransactionRepository;
    }

    public Pageable createPageable(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return pageable;
    }

    public List<EquityBankTransaction> findUnreconciledEquityItems(ReconcileRequestDto reconcileRequestDto) {
        try {
            return equityBankTransactionRepository
                    .findByIsReconciledAndTransactionDateBetween(false, reconcileRequestDto.getStartDate(), reconcileRequestDto.getEndDate());
        } catch (Exception e) {
            log.error("Error finding unreconciled equity items: {}", e.getMessage());
            throw new RuntimeException("Error loading unreconciled Equity bank items");
        }
    }

    public List<WorkpayEquityTransaction> findUnreconciledWorkpayEquityItems(ReconcileRequestDto reconcileRequestDto) {
        try {
            return workpayEquityTransactionRepository
                    .findByIsReconciledAndTransactionDateBetween(false, reconcileRequestDto.getStartDate(), reconcileRequestDto.getEndDate());
        } catch (Exception e) {
            log.error("Error finding unreconciled workpay equity items: {}", e.getMessage());
            throw new RuntimeException("Error loading unreconciled WorkpayEquity bank items", e);
        }
    }

    public void updateWorkpayEquityTransaction(WorkpayEquityTransaction workpayEquityTransaction) {
        try {
            WorkpayEquityTransaction transactionToUpdate = workpayEquityTransactionRepository.findById(
                    workpayEquityTransaction.getId()).orElseThrow(() -> new RuntimeException("Error getting WorkpayEquity payment to update"));
            transactionToUpdate.setReconciled(workpayEquityTransaction.isReconciled());
            workpayEquityTransactionRepository.save(transactionToUpdate);
        } catch (Exception e) {
            log.error("Error finding workpay equity item to update: {}", e.getMessage());
            throw new RuntimeException("Error finding workpay equity item to update", e);
        }
    }

    public void updateEquityTransaction(EquityBankTransaction equityBankTransaction) {
        try {
            EquityBankTransaction transactionToUpdate = equityBankTransactionRepository.findById(
                    equityBankTransaction.getId()).orElseThrow(() -> new RuntimeException("Error getting Equity item to update"));
            transactionToUpdate.setReconciled(equityBankTransaction.isReconciled());
            equityBankTransactionRepository.save(transactionToUpdate);
        } catch (Exception e) {
            log.error("Error finding equity item to update: {}", e.getMessage());
            throw new RuntimeException("Error finding equity item to update", e);
        }
    }
}
