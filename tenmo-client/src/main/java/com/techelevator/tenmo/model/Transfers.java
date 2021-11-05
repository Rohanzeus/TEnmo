package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfers {
    private int fromAccount;
    private int toAccount;
    private int transfer_Id;
    private int Status_Id;
    private int Type_Id;
    private BigDecimal amountToOrFrom;
    private String type;
    private String transfer;
    private String fromUser;
    private String toUser;

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

    public int getTransfer_Id() {
        return transfer_Id;
    }

    public void setTransfer_Id(int transfer_Id) {
        this.transfer_Id = transfer_Id;
    }

    public int getStatus_Id() {
        return Status_Id;
    }

    public void setStatus_Id(int status_Id) {
        Status_Id = status_Id;
    }

    public int getType_Id() {
        return Type_Id;
    }

    public void setType_Id(int type_Id) {
        Type_Id = type_Id;
    }

    public BigDecimal getAmountToOrFrom() {
        return amountToOrFrom;
    }

    public void setAmountToOrFrom(BigDecimal amountToOrFrom) {
        this.amountToOrFrom = amountToOrFrom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }
}
