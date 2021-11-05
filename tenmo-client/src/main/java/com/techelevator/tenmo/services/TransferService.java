package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfers;
import com.techelevator.tenmo.model.User;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class TransferService {

    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;

    public TransferService(String url, AuthenticatedUser currentUser) {
        this.BASE_URL = url;
        this.currentUser = currentUser;
    }
//    public void sendTEBucks() {
//        User[] users;
//        Transfers transfer = new Transfers();
//        try {
//            Scanner keyboard = new Scanner(System.in);
//            users = restTemplate.exchange(BASE_URL + "")
//        }
//    }
}
