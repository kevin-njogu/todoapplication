package com.kevin.todo.todo_application.reconciler.workpayequity.repository;


import com.kevin.todo.todo_application.reconciler.workpayequity.model.WorkpayEquityStatement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkpayEquityRepository extends JpaRepository<WorkpayEquityStatement, Long> {
    List<WorkpayEquityStatement> findByReferenceIsNotNull();

    Page<WorkpayEquityStatement> findByisReconciled(boolean isReconciled, Pageable pageable);

    List<WorkpayEquityStatement> findBytransactionDateBetween(LocalDate startDate, LocalDate endDate);

}
