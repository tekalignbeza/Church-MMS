package com.atl.church.mms.com.atl.church.mms.domain;

public enum TransactionMethod {

    CASH("Cash"),
    CHECK("Check"),
    CREDIT_CARD("Credit Card"),
    WIRE_TRANSFER("Wire Transfer");
    private String method;

    TransactionMethod(String m) {
        this.method = m;
    }

    public String toString() {
        return this.method;
    }
}
