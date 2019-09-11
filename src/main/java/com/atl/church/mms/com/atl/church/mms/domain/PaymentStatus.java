package com.atl.church.mms.com.atl.church.mms.domain;

public enum PaymentStatus {

    PAID("Paid"),
    VOID("Void"),
    PENDING("Pending");
    private String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return status;
    }
}
