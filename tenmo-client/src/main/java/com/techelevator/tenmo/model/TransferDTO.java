package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDTO {
    private int fromAccount;
    private int toAccount;
    private BigDecimal amountToOrFrom;

    public int getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(int fromAccount) {
        this.fromAccount = fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public void setToAccount(int toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmountToOrFrom() {
        return amountToOrFrom;
    }

    public void setAmountToOrFrom(BigDecimal amountToOrFrom) {
        this.amountToOrFrom = amountToOrFrom;
    }
}
