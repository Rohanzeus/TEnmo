package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {
    private TransferDao transferDao;
    private UserDao userDao;

    @RequestMapping(path = "transfer/", method = RequestMethod.POST)
    public String makeTransfer(@PathVariable Transfer transfer) {
        return transferDao.sendTransfer(transfer.getFromAccount(), transfer.getToAccount(),transfer.getAmountToOrFrom());
    }
}
