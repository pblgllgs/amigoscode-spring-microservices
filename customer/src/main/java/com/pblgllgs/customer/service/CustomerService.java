package com.pblgllgs.customer.service;

import com.pblgllgs.clients.fraud.FraudCheckResponse;
import com.pblgllgs.clients.fraud.FraudClient;
import com.pblgllgs.clients.notification.NotificationClient;
import com.pblgllgs.clients.notification.NotificationRequest;
import com.pblgllgs.customer.model.Customer;
import com.pblgllgs.customer.model.CustomerRegistrationRequest;
import com.pblgllgs.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final FraudClient fraudClient;

    private final NotificationClient notificationClient;
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken

        customerRepository.saveAndFlush(customer);

        // todo: check if fraudster

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("Fraudster");
        }
        // todo send notification
        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome", customer.getFirstName())
                )
        );
//        rabbitMQMessageProducer.publish(
//                notificationRequest,
//                "internal.exchange",
//                "internal.notification.routing-key"
//        );
    }
}
