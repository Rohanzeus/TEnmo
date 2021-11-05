package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {

    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser userCurrently;

    public AccountService(String url, AuthenticatedUser userCurrently) {
        this.BASE_URL = url;
        this.userCurrently = userCurrently;
    }

    public BigDecimal getBalance() {
        BigDecimal balance = new BigDecimal(0);
        try{
            balance = restTemplate.exchange(BASE_URL + "balance/" + userCurrently.getUser().getId(), HttpMethod.GET, makeAuthEntity(), BigDecimal.class).getBody();
        }catch (RestClientException e) {
            System.out.println("Balance is $" + balance);
        }return balance;
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(userCurrently.getToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        return entity;
    }
}
