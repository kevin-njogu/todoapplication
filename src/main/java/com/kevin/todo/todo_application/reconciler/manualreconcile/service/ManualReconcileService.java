package com.kevin.todo.todo_application.reconciler.manualreconcile.service;

import com.kevin.todo.todo_application.reconciler.manualreconcile.dto.ManualReconcileResponseDto;
import com.kevin.todo.todo_application.reconciler.uploader.repository.EquityBankTransactionRepository;
import com.kevin.todo.todo_application.reconciler.uploader.repository.WorkpayEquityTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManualReconcileService {
    private final EquityBankTransactionRepository equityBankTransactionRepository;
    private final WorkpayEquityTransactionRepository workpayEquityTransactionRepository;
    public ManualReconcileResponseDto getUnMatchedItems(String type, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page page1 = null;
        try {
            if (equityBankTransactionRepository == null || workpayEquityTransactionRepository == null) {
                throw new IllegalStateException("equityBankTransactionRepository or workpayEquityTransactionRepository is null");
            }
            if (type.equalsIgnoreCase("equity")) {
               page1 = equityBankTransactionRepository.findByIsReconciled(false, pageable);
            }
            if (type.equalsIgnoreCase("workpayequity")) {
                page1 = workpayEquityTransactionRepository.findByIsReconciled(false, pageable);
            }
            return ManualReconcileResponseDto.builder().pages(page1).message("Success").build();
        } catch (Exception e) {
            return ManualReconcileResponseDto.builder().message(e.getMessage()).build();
        }
    }
}
