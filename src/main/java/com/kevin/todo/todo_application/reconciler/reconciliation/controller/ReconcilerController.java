package com.kevin.todo.todo_application.reconciler.reconciliation.controller;

import com.kevin.todo.todo_application.reconciler.reconciliation.dto.ReconcileRequestDto;
import com.kevin.todo.todo_application.reconciler.reconciliation.dto.ReconcileResponseDto;
import com.kevin.todo.todo_application.reconciler.reconciliation.service.ReconcilerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reconciliation")
public class ReconcilerController {
    private final ReconcilerServiceImpl reconcilerService;
    @PostMapping("/reconcile")
    public ResponseEntity<ReconcileResponseDto> reconcile(@RequestBody ReconcileRequestDto reconcileRequestDto) {
        if (reconcileRequestDto == null) {
            return ResponseEntity.badRequest().body(ReconcileResponseDto.builder().message("Reconcile request is null").build());
        }
        if ("equity".equals(reconcileRequestDto.getAccount())) {
            return ResponseEntity.ok(reconcilerService.reconcileEquityPayments(reconcileRequestDto));
        }
        return ResponseEntity.badRequest().body(ReconcileResponseDto.builder().message("Account type not supported").build());
    }
}

    /*
    @GetMapping("/credits-equity-bank")
    public ResponseEntity<?> getAllCreditsInEquityBank() {
        return ResponseEntity.ok(reconcilerService.findAllEquityBankCredits());
    }

    /*
    @GetMapping("/charges-equity-bank")
    public ResponseEntity<?> getAllChargesInEquityBank() {
        return ResponseEntity.ok(reconcilerService.findAllEquityBankCharges());
    }

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
    }

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
    }

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


    @GetMapping("/unreconciled-workpay-equity-page")
    public Page<WorkpayEquityStatement>getUnreconciledItemsInWorkpayEquity(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return reconcilerService.findUnReconciledWorkpayEquityItems(pageable);
    }*/


