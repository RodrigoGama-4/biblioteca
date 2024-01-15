package com.biblioteca.biblioteca.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biblioteca.biblioteca.dtos.CpfAPIDTO;


@Service
public class CpfService {
    
    public CpfAPIDTO searchCpf( String cpf){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CpfAPIDTO> resp = restTemplate.getForEntity("https://api.invertexto.com/v1/validator?token=6121|Q5RNINMBb5hD76IEpPMjGVYEBKm6JJKe&value=11529795400", 
                                                    CpfAPIDTO.class);
        return resp.getBody();
    }
}
