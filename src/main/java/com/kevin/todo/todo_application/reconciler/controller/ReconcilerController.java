package com.kevin.todo.todo_application.reconciler.controller;

import com.kevin.todo.todo_application.reconciler.dto.ReconcileRequestDto;
import com.kevin.todo.todo_application.reconciler.dto.ReconcileResponseDto;
import com.kevin.todo.todo_application.reconciler.service.reconcile.ReconcilerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reconciliation")
public class ReconcilerController {
    private final ReconcilerServiceImpl reconcilerService;

    public ReconcilerController(ReconcilerServiceImpl reconcilerService) {
        this.reconcilerService = reconcilerService;
    }

    @PostMapping("/reconcile")
    public ResponseEntity<?> reconcile(@Valid @RequestBody ReconcileRequestDto reconcileRequestDto) {
        try {
            if (reconcileRequestDto == null) {
                throw new IllegalStateException("ReconcileRequestDto is null");
            }
            if ("equity".equals(reconcileRequestDto.getAccount())) {
               reconcilerService.reconcileEquityPayments(reconcileRequestDto);
            }else {
                throw new RuntimeException("The account selected is not supported");
            }
            return ResponseEntity.ok(ReconcileResponseDto.builder().message("Reconciliation operation completed").build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ReconcileResponseDto.builder().message(e.getMessage()).build());
        }
    }

    @GetMapping("/getunmatched")
    public Page<?> getUnmatched(@RequestParam(defaultValue = "equity") String type, @RequestParam(defaultValue = "0") int page) {
        try{
            var pageItem = reconcilerService.getUnMatchedItems(type, page);
            if (pageItem.isEmpty()) {
                throw new IllegalStateException("pageItem is empty");
            }
            if (page < 0 || page > pageItem.getTotalPages()-1) {
                throw new IllegalStateException("page is out of range");
            }
            return pageItem;
        } catch (Exception e) {
            throw new RuntimeException("Error getting unreconciled Equity items", e);
        }
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


