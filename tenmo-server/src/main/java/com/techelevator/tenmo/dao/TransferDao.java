package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;

public interface TransferDao {
    StringWrapper sendTransfer(int fromUser, int toUser, BigDecimal amount);

}
