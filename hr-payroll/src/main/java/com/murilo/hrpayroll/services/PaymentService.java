package com.murilo.hrpayroll.services;

import com.murilo.hrpayroll.entities.Payment;
import com.murilo.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String wokerHost;

    @Autowired
    private RestTemplate restTemplate;


    public Payment getPayment(long workId, int days){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", ""+workId);

        Worker worker = restTemplate.getForObject(wokerHost+"/workers/{id}", Worker.class, uriVariables);

        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
