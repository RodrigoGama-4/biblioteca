package com.biblioteca.biblioteca.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.biblioteca.biblioteca.dtos.CpfAPIDTO;

public class CpfAPIService {

    private static final String BASE_URL = "https://api.invertexto.com/v1/validator?token=6121%7CQ5RNINMBb5hD76IEpPMjGVYEBKm6JJKe&value=";
    
    public CpfAPIDTO searchCPF(String cpf){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CpfAPIDTO> resp =  
        restTemplate.getForEntity(BASE_URL + cpf + "&type=cpf", CpfAPIDTO.class);
        return resp.getBody();
    }
}
