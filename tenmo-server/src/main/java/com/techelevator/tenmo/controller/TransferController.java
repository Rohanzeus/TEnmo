package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.StringWrapper;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {
    private TransferDao transferDao;
    private UserDao userDao;


    @RequestMapping(path = "transfers", method = RequestMethod.POST)
    public StringWrapper makeTransfer(@RequestBody Transfer transfer) {
        return transferDao.sendTransfer(transfer.getFromAccount(), transfer.getToAccount(),transfer.getAmountToOrFrom());
    }
}
