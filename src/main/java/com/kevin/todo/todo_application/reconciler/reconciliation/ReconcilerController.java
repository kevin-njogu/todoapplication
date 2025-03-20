package com.kevin.todo.todo_application.reconciler.reconciliation;

import com.kevin.todo.todo_application.reconciler.equitybank.model.EquityBankStatement;
import com.kevin.todo.todo_application.reconciler.workpayequity.model.WorkpayEquityStatement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reconciler")
public class ReconcilerController {

    private ReconcilerServiceImpl reconcilerService;

    public ReconcilerController(ReconcilerServiceImpl reconcilerService) {
        this.reconcilerService = reconcilerService;
    }

   @GetMapping("/reconcile")
    public ResponseEntity<String> reconcile() {
        reconcilerService.reconcile();
        return ResponseEntity.ok("Reconciliation process completed");
    }

    @GetMapping("/credits-equity-bank")
    public ResponseEntity<?> getAllCreditsInEquityBank() {
        return ResponseEntity.ok(reconcilerService.findAllEquityBankCredits());
    }

    /*
    @GetMapping("/charges-equity-bank")
    public ResponseEntity<?> getAllChargesInEquityBank() {
        return ResponseEntity.ok(reconcilerService.findAllEquityBankCharges());
    }  */

    @GetMapping("/charges-equity-bank-page")
    public Page<EquityBankStatement> getAllChargesInEquityBank(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return reconcilerService.findAllEquityBankCharges(pageable);
    }

  /*
    @GetMapping("/uncreconciled-equity-bank")
    public ResponseEntity<?> getAllUnreconciledInEquityBank() {
        return ResponseEntity.ok(reconcilerService.findUnreconciledEquityItems());
    }*/

    @GetMapping("/uncreconciled-equity-bank-page")
    public Page<EquityBankStatement> getUnReconciledEquityItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return reconcilerService.findUnreconciledEquityItems(pageable);
    }

    /*
    @GetMapping("/reconciled-equity-bank")
    public ResponseEntity<?> getAllReconciledInEquityBank() {
        return ResponseEntity.ok(reconcilerService.findReconciledEquityItems());
    }*/

    @GetMapping("/reconciled-equity-bank-page")
    public Page<EquityBankStatement> getReconciledEquityItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return reconcilerService.findReconciledEquityItems(pageable);
    }

    /*
    @GetMapping("/reconciled-workpay-equity")
    public ResponseEntity<?> getReconciledItemsInWorkpayEquity() {
        return ResponseEntity.ok(reconcilerService.findReconciledWorkpayEquityItems());
    }
     */

    @GetMapping("/reconciled-workpay-equity-page")
    public Page<WorkpayEquityStatement> getReconciledItemsInWorkpayEquity(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return reconcilerService.findReconciledWorkpayEquityItems(pageable);
    }

    /*
    @GetMapping("/unreconciled-workpay-equity")
    public ResponseEntity<?> getUnreconciledItemsInWorkpayEquity() {
        return ResponseEntity.ok(reconcilerService.findUnReconciledWorkpayEquityItems());
    }
     */

    @GetMapping("/unreconciled-workpay-equity-page")
    public Page<WorkpayEquityStatement>getUnreconciledItemsInWorkpayEquity(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return reconcilerService.findUnReconciledWorkpayEquityItems(pageable);
    }

}
