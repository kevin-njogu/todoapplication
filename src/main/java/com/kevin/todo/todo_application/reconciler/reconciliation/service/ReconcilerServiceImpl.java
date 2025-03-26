package com.kevin.todo.todo_application.reconciler.reconciliation.service;

import com.kevin.todo.todo_application.reconciler.reconciliation.dto.ReconcileRequestDto;
import com.kevin.todo.todo_application.reconciler.reconciliation.dto.ReconcileResponseDto;
import com.kevin.todo.todo_application.reconciler.uploader.model.EquityBankTransaction;
import com.kevin.todo.todo_application.reconciler.uploader.model.WorkpayEquityTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReconcilerServiceImpl {
    private final ReconcilerUtils reconcilerUtils;

    public ReconcileResponseDto reconcileEquityPayments(ReconcileRequestDto reconcileRequestDto) {
        try {
            if (reconcileRequestDto == null) {
                throw new IllegalStateException("ReconcileRequestDto is null");
            }
            List<EquityBankTransaction> equityBankTransactions= reconcilerUtils.findUnreconciledEquityItems(reconcileRequestDto);
            List<WorkpayEquityTransaction> workpayEquityTransactions = reconcilerUtils.findUnreconciledWorkpayEquityItems(reconcileRequestDto);
            if (equityBankTransactions.isEmpty() || workpayEquityTransactions.isEmpty()) {
                log.error("No transactions items to reconcile");
                throw new IllegalStateException("No transactions items to reconcile");
            }
            for (EquityBankTransaction equityBankTransaction : equityBankTransactions) {
                for (WorkpayEquityTransaction workpayEquityTransaction : workpayEquityTransactions) {
                    if (workpayEquityTransaction.getReference().equals(equityBankTransaction.getReference())) {
                        if (workpayEquityTransaction.getTransactionDate().equals(equityBankTransaction.getTransactionDate())) {
                            if (workpayEquityTransaction.getDebit() == equityBankTransaction.getDebit()) {
                                equityBankTransaction.setReconciled(true);
                                reconcilerUtils.updateEquityTransaction(equityBankTransaction);
                                workpayEquityTransaction.setReconciled(true);
                                reconcilerUtils.updateWorkpayEquityTransaction(workpayEquityTransaction);
                            }
                        }
                    }
                }
            }
            return ReconcileResponseDto.builder().message("Reconciliation operation completed").build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ReconcileResponseDto.builder().message("Reconciliation operation failed").build();
        }
    }

    /*
    public List<EquityBankStatement> findAllEquityBankCredits() {
        return equityBankService.findAllCredits();
    }

    /*
    public List<EquityBankStatement> findAllEquityBankCharges()  {
        return equityBankService.findAllCharges();
    }


    public Page<EquityBankStatement> findAllEquityBankCharges(Pageable pageable)  {
        return equityBankService.findAllCharges(pageable);
    }


    public List<EquityBankStatement> findUnreconciledEquityItems() {
        return equityBankService.findUnreconciledEquityItems();
    }


    public Page<EquityBankStatement> findUnreconciledEquityItems(Pageable pageable) {
        return equityBankService.findUnreconciledEquityItems(pageable);
    }

    /*
    public List<EquityBankStatement> findReconciledEquityItems() {
        return equityBankService.findReconciledEquityItems();
    }


    public Page<EquityBankStatement> findReconciledEquityItems(Pageable pageable) {
        return equityBankService.findReconciledEquityItems(pageable);
    }

    /*
    public List<WorkpayEquityStatement> findReconciledWorkpayEquityItems() {
        return workpayEquityService.findReconciledWorkpayEquityItems();
    }
     *

    public Page<WorkpayEquityStatement> findReconciledWorkpayEquityItems(Pageable pageable) {
        return workpayEquityService.findReconciledWorkpayEquityItems(pageable);
    }

    public Page<WorkpayEquityStatement> findUnReconciledWorkpayEquityItems(Pageable pageable) {
        return workpayEquityService.findUnReconciledWorkpayEquityItems(pageable);
    }*/

}
