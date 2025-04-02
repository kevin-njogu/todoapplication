package com.kevin.todo.todo_application.reconciler.service.reconcile;

import com.kevin.todo.todo_application.reconciler.dto.ReconcileRequestDto;
import org.springframework.data.domain.Page;

public interface ReconcilerService {

    void reconcileEquityPayments(ReconcileRequestDto reconcileRequestDto);

    Page getUnMatchedItems(String type, int page);

}
