package com.kevin.todo.todo_application.reconciler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "workpayequitytransactions")
public class WorkpayEquityTransaction {

    @Id
    @GeneratedValue
    private Long id;
    private String reference;
    private LocalDate transactionDate;
    private double debit;
    private boolean isReconciled = false;

    public WorkpayEquityTransaction(Long id, String reference, LocalDate transactionDate, double debit, boolean isReconciled) {
        this.id = id;
        this.reference = reference;
        this.transactionDate = transactionDate;
        this.debit = debit;
        this.isReconciled = isReconciled;
    }

    public WorkpayEquityTransaction() {
    }

    public Long getId() {
        return this.id;
    }

    public String getReference() {
        return this.reference;
    }

    public LocalDate getTransactionDate() {
        return this.transactionDate;
    }

    public double getDebit() {
        return this.debit;
    }

    public boolean isReconciled() {
        return this.isReconciled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public void setReconciled(boolean isReconciled) {
        this.isReconciled = isReconciled;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof WorkpayEquityTransaction)) return false;
        final WorkpayEquityTransaction other = (WorkpayEquityTransaction) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$reference = this.getReference();
        final Object other$reference = other.getReference();
        if (this$reference == null ? other$reference != null : !this$reference.equals(other$reference)) return false;
        final Object this$transactionDate = this.getTransactionDate();
        final Object other$transactionDate = other.getTransactionDate();
        if (this$transactionDate == null ? other$transactionDate != null : !this$transactionDate.equals(other$transactionDate))
            return false;
        if (Double.compare(this.getDebit(), other.getDebit()) != 0) return false;
        if (this.isReconciled() != other.isReconciled()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof WorkpayEquityTransaction;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $reference = this.getReference();
        result = result * PRIME + ($reference == null ? 43 : $reference.hashCode());
        final Object $transactionDate = this.getTransactionDate();
        result = result * PRIME + ($transactionDate == null ? 43 : $transactionDate.hashCode());
        final long $debit = Double.doubleToLongBits(this.getDebit());
        result = result * PRIME + (int) ($debit >>> 32 ^ $debit);
        result = result * PRIME + (this.isReconciled() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "WorkpayEquityTransaction(id=" + this.getId() + ", reference=" + this.getReference() + ", transactionDate=" + this.getTransactionDate() + ", debit=" + this.getDebit() + ", isReconciled=" + this.isReconciled() + ")";
    }
}
