package com.atl.church.mms.com.atl.church.mms.domain;

public enum TransactionStatus {

    PAID("Paid"),
    VOID("Void"),
    PENDING("Pending");
    private String status;

    TransactionStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return status;
    }
}
