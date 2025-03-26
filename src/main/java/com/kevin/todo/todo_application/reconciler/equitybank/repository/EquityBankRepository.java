//package com.kevin.todo.todo_application.reconciler.equitybank.repository;
//
//import com.kevin.todo.todo_application.reconciler.equitybank.model.EquityBankStatement;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Repository
//public interface EquityBankRepository extends JpaRepository<EquityBankStatement, Long> {
//
//    List<EquityBankStatement> findByReferenceIsNotNull();
//
//    List<EquityBankStatement> findByCreditIsNotNull();
//
//   // List<EquityBankStatement> findByReference(String reference);
//    Page<EquityBankStatement> findByReferenceContainingIgnoreCase(String reference, Pageable pageable);
//
//    //List<EquityBankStatement> findByIsReconciledAndCreditIsNotNullAndReferenceNot(boolean isReconciled, String reference);
//    Page<EquityBankStatement> findByIsReconciledAndCreditIsNotNullAndReferenceNot(boolean isReconciled, String reference, Pageable pageable);
//
//    //List<EquityBankStatement> findByIsReconciled(boolean isReconciled);
//    Page<EquityBankStatement> findByIsReconciled(boolean isReconciled, Pageable pageable);
//
//    List<EquityBankStatement> findBytransactionDateBetween(LocalDate startDate, LocalDate endDate);
//}
