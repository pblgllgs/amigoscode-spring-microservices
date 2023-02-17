package com.pblgllgs.fraud.controller;

import com.pblgllgs.clients.fraud.FraudCheckResponse;
import com.pblgllgs.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping("/{customerId}")
    public FraudCheckResponse idFraudster(@PathVariable("customerId") Integer customerID ){
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerID);
        log.info("Fraud check request for customer {}",customerID);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
