package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Scanner;

public class TransferService {

    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;

    public TransferService(String url, AuthenticatedUser currentUser) {
        this.BASE_URL = url;
        this.currentUser = currentUser;
    }
    public void sendTEBucks() {
        User[] users;
        Transfer transfer = new Transfer();
        transfer.setFromAccount(currentUser.getUser().getId());
        Scanner keyboard = new Scanner(System.in);
        try {
            System.out.println("test");
            users = restTemplate.exchange(BASE_URL + "allusers", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
            System.out.println("-------------------------------------------");
            System.out.println("Users");
            System.out.println("ID\tName");
            System.out.println("-------------------------------------------");
            for(User user : users) {
                if(user.getId() != currentUser.getUser().getId()) {
                    System.out.println(user.getId() + "\t" + user.getUsername());
                }
            }
            System.out.println("---------");
            System.out.print("Enter ID of user you are sending to (0 to cancel): ");
            transfer.setToAccount(Integer.parseInt(keyboard.nextLine()));
            if(transfer.getToAccount() != 0) {
                System.out.print("Enter amount: ");
                transfer.setAmountToOrFrom(new BigDecimal(Double.parseDouble(keyboard.nextLine())));
            }

            restTemplate.exchange(BASE_URL + "transfer/", HttpMethod.POST, makeAuthEntity(), Transfer.class);
        }
        catch(Exception e) {
            System.out.println("INVALID INPUT");
        }
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }
}
