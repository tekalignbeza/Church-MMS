package com.atl.church.mms.com.atl.church.mms.domain;

public enum PaymentMethod {

    CASH("Cash"),
    CHECK("Check"),
    CREDIT_CARD("Credit Card");
    private String method;

    PaymentMethod(String m) {
        this.method = m;
    }

    public String toString() {
        return this.method;
    }
}
