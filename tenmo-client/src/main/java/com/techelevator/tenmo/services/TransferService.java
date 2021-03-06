package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
        TransferDTO transfer = new TransferDTO();
        transfer.setFromAccount(currentUser.getUser().getId());
        Scanner keyboard = new Scanner(System.in);
        try {

            users = restTemplate.exchange(BASE_URL + "allusers", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
            System.out.println("-------------------------------------------");
            System.out.println("Users");
            System.out.println("ID\tName");
            System.out.println("-------------------------------------------");
            for(User user : users) {
                if(!user.getId().equals(currentUser.getUser().getId())) {
                    System.out.println(user.getId() + "\t" + user.getUsername());
                }
            }
            System.out.println("---------");
            System.out.print("Enter ID of user you are sending to (0 to cancel): ");
            transfer.setToAccount(Integer.parseInt(keyboard.nextLine()));
//            System.out.println(transfer.getToAccount());
            if(transfer.getToAccount() != 0) {
                System.out.print("Enter amount: ");
                try {
                    transfer.setAmountToOrFrom(BigDecimal.valueOf(Double.parseDouble(keyboard.nextLine())));
                } catch (NumberFormatException e ){
                    System.out.println("Enter valid amount");
                }
            }
            System.out.println(transfer.getAmountToOrFrom());
            String output = restTemplate.exchange(BASE_URL + "transfer", HttpMethod.POST, makeTransfer(transfer), String.class).getBody();
            System.out.println(output);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }

    private HttpEntity<TransferDTO> makeTransfer (TransferDTO transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity<TransferDTO> entity = new HttpEntity<>(transfer, headers);
        return entity;
    }
}
