package com.kevin.todo.todo_application.reconciler.reconciliation;


import com.kevin.todo.todo_application.reconciler.equitybank.model.EquityBankStatement;
import com.kevin.todo.todo_application.reconciler.equitybank.service.EquityBankServiceImpl;
import com.kevin.todo.todo_application.reconciler.workpayequity.model.WorkpayEquityStatement;
import com.kevin.todo.todo_application.reconciler.workpayequity.service.WorkpayEquityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReconcilerServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(ReconcilerServiceImpl.class);
    private EquityBankServiceImpl  equityBankService;
    private WorkpayEquityServiceImpl workpayEquityService;


    public ReconcilerServiceImpl(EquityBankServiceImpl equityBankService, WorkpayEquityServiceImpl workpayEquityService) {
        this.equityBankService = equityBankService;
        this.workpayEquityService = workpayEquityService;
    }

    public void reconcile() {
        List<EquityBankStatement> equityBankStatements = equityBankService.findNonNullReferences();
        List<WorkpayEquityStatement> workpayEquityStatements = workpayEquityService.findNonNullReferences();

        for (EquityBankStatement equityBankStatement : equityBankStatements) {
            for (WorkpayEquityStatement workpayEquityStatement : workpayEquityStatements) {
                if (workpayEquityStatement.getReference().equals(equityBankStatement.getReference())) {
                    if (workpayEquityStatement.getTransactionDate().equals(equityBankStatement.getTransactionDate())) {
                            if (workpayEquityStatement.getDebit().equals(equityBankStatement.getDebit())) {
                                    equityBankStatement.setReconciled(true);
                                    workpayEquityService.updateWorkpayEquityStatement(workpayEquityStatement);
                                    workpayEquityStatement.setReconciled(true);
                                   workpayEquityService.updateWorkpayEquityStatement(workpayEquityStatement);
                            }
                    }
                }
            }
        }
    }

    public List<EquityBankStatement> findAllEquityBankCredits() {
        return equityBankService.findAllCredits();
    }

    /*
    public List<EquityBankStatement> findAllEquityBankCharges()  {
        return equityBankService.findAllCharges();
    }
     */

    public Page<EquityBankStatement> findAllEquityBankCharges(Pageable pageable)  {
        return equityBankService.findAllCharges(pageable);
    }

    /*
    public List<EquityBankStatement> findUnreconciledEquityItems() {
        return equityBankService.findUnreconciledEquityItems();
    }
     */

    public Page<EquityBankStatement> findUnreconciledEquityItems(Pageable pageable) {
        return equityBankService.findUnreconciledEquityItems(pageable);
    }

    /*
    public List<EquityBankStatement> findReconciledEquityItems() {
        return equityBankService.findReconciledEquityItems();
    }
     */

    public Page<EquityBankStatement> findReconciledEquityItems(Pageable pageable) {
        return equityBankService.findReconciledEquityItems(pageable);
    }

    /*
    public List<WorkpayEquityStatement> findReconciledWorkpayEquityItems() {
        return workpayEquityService.findReconciledWorkpayEquityItems();
    }
     */

    public Page<WorkpayEquityStatement> findReconciledWorkpayEquityItems(Pageable pageable) {
        return workpayEquityService.findReconciledWorkpayEquityItems(pageable);
    }

    public Page<WorkpayEquityStatement> findUnReconciledWorkpayEquityItems(Pageable pageable) {
        return workpayEquityService.findUnReconciledWorkpayEquityItems(pageable);
    }

}
