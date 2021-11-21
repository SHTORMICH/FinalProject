package com.kabaldin.controller.DAO.entity;

public class PaymentStatus {
    private int id;
    private String paymentStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PaymentStatus)) return false;
        PaymentStatus that = (PaymentStatus) obj;
        return id == that.id && paymentStatus == that.paymentStatus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((paymentStatus == null) ? 0 : paymentStatus.hashCode());
        return result;
    }
}
