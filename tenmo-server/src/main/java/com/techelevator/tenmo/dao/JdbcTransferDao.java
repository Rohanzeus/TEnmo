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
    public String sendTransfer(int fromUser, int toUser, BigDecimal amount) {

        if (amount.compareTo(accountDao.getBalance(fromUser)) != 1) {
            String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount )" +
                    "VALUES (2, 2, ?, ?, ?);";


            jdbcTemplate.update(sql, fromUser, toUser, amount);

            accountDao.addBalance(toUser, amount);
            accountDao.subtractBalance(fromUser, amount);
            return "Completed";
        }

        return "Failed";
    }
}
