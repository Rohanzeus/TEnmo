package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import com.techelevator.tenmo.model.Account;

public interface AccountDao {
    BigDecimal getBalance(int userId);
    BigDecimal addBalance(int userId, BigDecimal amountToAdd);
    BigDecimal subtractBalance(int userId, BigDecimal amountToSubtract);
    Account getAccount(int accountId);
}
