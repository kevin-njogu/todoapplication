package com.kevin.todo.todo_application.reconciler.service.reconcile;

import com.kevin.todo.todo_application.reconciler.dto.ReconcileRequestDto;
import com.kevin.todo.todo_application.reconciler.model.EquityBankTransaction;
import com.kevin.todo.todo_application.reconciler.model.WorkpayEquityTransaction;
import com.kevin.todo.todo_application.reconciler.repository.EquityBankTransactionRepository;
import com.kevin.todo.todo_application.reconciler.repository.WorkpayEquityTransactionRepository;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReconcilerServiceImpl implements ReconcilerService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ReconcilerServiceImpl.class);
    private final ReconcilerUtils reconcilerUtils;
    private final EquityBankTransactionRepository equityBankTransactionRepository;
    private final WorkpayEquityTransactionRepository workpayEquityTransactionRepository;

    public ReconcilerServiceImpl(
            ReconcilerUtils reconcilerUtils, EquityBankTransactionRepository equityBankTransactionRepository, WorkpayEquityTransactionRepository workpayEquityTransactionRepository) {
        this.equityBankTransactionRepository = equityBankTransactionRepository;
        this.workpayEquityTransactionRepository = workpayEquityTransactionRepository;
        this.reconcilerUtils = reconcilerUtils;
    }

    @Override
    public void reconcileEquityPayments(ReconcileRequestDto reconcileRequestDto) {
        try {
            List<EquityBankTransaction> equityBankTransactions = reconcilerUtils.findUnreconciledEquityItems(reconcileRequestDto);
            List<WorkpayEquityTransaction> workpayEquityTransactions = reconcilerUtils.findUnreconciledWorkpayEquityItems(reconcileRequestDto);
            if (equityBankTransactions.isEmpty() || workpayEquityTransactions.isEmpty()) {
                log.error("No transactions items to reconcile");
                throw new RuntimeException("No transactions items to reconcile");
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
        } catch (Exception e) {
            log.error("Error reconciling equity payments {}", e.getMessage());
            throw new RuntimeException("Error reconciling equity payments", e);
        }
    }

    @Override
    public Page<?> getUnMatchedItems(String type, int page) {
        try {
            String sortBy = "id";
            String direction = "desc";
            int pageSize = 10;
            Pageable pageable = reconcilerUtils.createPageable(page, pageSize, sortBy, direction);
            if (pageable == null) {
                throw new IllegalStateException("pageable is null");
            }
            if (type.equalsIgnoreCase("equity")) {
                return equityBankTransactionRepository.findByIsReconciled(false, pageable);
            }
            if (type.equalsIgnoreCase("workpayequity")) {
                return workpayEquityTransactionRepository.findByIsReconciled(false, pageable);
            }
            throw new IllegalStateException("Invalid type");
        } catch (Exception e) {
            throw new RuntimeException("Error getting unreconciled Equity items", e);
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
