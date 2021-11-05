package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.output.Output;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {
    JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public BigDecimal getBalance(int userId) {
        String sql = "SELECT balance FROM accounts WHERE user_id = ?;";
        SqlRowSet results;
        BigDecimal balance = null;
        try {
            results = jdbcTemplate.queryForRowSet(sql, userId);

            if(results.next()) {
                balance = results.getBigDecimal("balance");
            }
        }
        catch(DataAccessException e) {
            Output.noDataFound();
        }

        return balance;
    }

    @Override
    public BigDecimal addBalance(int userId, BigDecimal amountToAdd) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = (SELECT account_id FROM accounts WHERE user_id = ?);";
        try {
            jdbcTemplate.update(sql, getBalance(userId).add(amountToAdd), userId);
        }
        catch(DataAccessException e) {
            Output.noDataFound();
        }
        return getBalance(userId);
    }

    @Override
    public BigDecimal subtractBalance(int userId, BigDecimal amountToSubtract) {
        return addBalance(userId, amountToSubtract.negate());
    }

    @Override
    public Account getAccount(int accountId) {
        String sql = "SELECT * FROM accounts WHERE account_id = ?;";
        SqlRowSet results;
        Account account = null;
        try {
            results = jdbcTemplate.queryForRowSet(sql, accountId);

            if(results.next()) {
                account = mapRowToAccount(results);
            }
        }
        catch(DataAccessException e) {
            Output.noDataFound();
        }

        return account;
    }

    private Account mapRowToAccount(SqlRowSet row) {
        Account account = new Account();
        account.setAccountId(row.getInt("account_id"));
        account.setUserId(row.getInt("user_id"));
        account.setBalance(row.getBigDecimal("balance"));

        return account;
    }
}
