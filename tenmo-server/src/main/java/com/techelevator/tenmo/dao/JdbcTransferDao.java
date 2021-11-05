package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;

public class JdbcTransferDao implements TransferDao {
    JdbcTemplate jdbcTemplate;
    AccountDao accountDao;

    public JdbcTransferDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        accountDao = new JdbcAccountDao(dataSource);
    }

    @Override
    public String makeTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfers VALUES (?, ?, ?, ?, ?, ?);";

        int transferId = transfer.getTransfer_Id();
        int typeId = transfer.getType_Id();
        int statusId = transfer.getStatus_Id();
        int accountFrom = transfer.getFromAccount();
        int accountTo = transfer.getToAccount();
        BigDecimal amount = transfer.getAmountToOrFrom();
        jdbcTemplate.update(sql, transferId, typeId, statusId, accountFrom, accountTo, amount);

        accountDao.addBalance(accountTo, amount);
        accountDao.subtractBalance(accountFrom, amount);

        return transfer;
    }
}
