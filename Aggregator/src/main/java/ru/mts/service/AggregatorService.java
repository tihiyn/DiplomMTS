package ru.mts.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.mts.dto.AccountDTO;
import ru.mts.dto.DepositDTO;
import ru.mts.entity.MyUser;

@RequiredArgsConstructor
@Service
public class AggregatorService {
    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    private static final String ACCOUNT_SERVICE_NAME = "Account";
    private static final String DEPOSIT_SERVICE_NAME = "Deposit";
    private static final String CUSTOMER_SERVICE_NAME = "CustomerService";
    private static final String NOTIFICATION_SERVICE_NAME = "Notification";

    public AccountDTO getAccount(int id) {
        ServiceInstance serviceInstance = discoveryClient.getInstances(ACCOUNT_SERVICE_NAME)
                .stream()
                .findFirst()
                .orElse(null);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(serviceInstance.getUri().toString() + "/account/" + id);

        return restTemplate.getForEntity(uriComponentsBuilder.toUriString(), AccountDTO.class).getBody();
    }

    public void saveUser(MyUser user) {
        ServiceInstance serviceInstance = discoveryClient.getInstances(CUSTOMER_SERVICE_NAME)
                .stream()
                .findFirst()
                .orElse(null);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(serviceInstance.getUri().toString() + "/customer/save");

        restTemplate.postForEntity(uriComponentsBuilder.toUriString(), user, MyUser.class);
    }

    public MyUser findUserByPhoneNumber(String phoneNumber) {
        ServiceInstance serviceInstance = discoveryClient.getInstances(CUSTOMER_SERVICE_NAME)
                .stream()
                .findFirst()
                .orElse(null);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(serviceInstance.getUri().toString() + "/customer/get")
                .queryParam("phoneNumber", phoneNumber);

        return restTemplate.getForEntity(uriComponentsBuilder.toUriString(), MyUser.class).getBody();
    }

    public Double predictRate(DepositDTO depositDTO) {
        ServiceInstance serviceInstance = discoveryClient.getInstances(DEPOSIT_SERVICE_NAME)
                .stream()
                .findFirst()
                .orElse(null);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(serviceInstance.getUri().toString() + "/deposit/predict");

        return restTemplate.postForEntity(uriComponentsBuilder.toUriString(), depositDTO, Double.class).getBody();
    }

    private MyUser getCurrentSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (MyUser) authentication.getPrincipal();
    }

    public String createRequest(DepositDTO depositDTO) {
        MyUser user = getCurrentSessionUser();

        String numBankAccount = user.getNumBankAccount(); //TODO

        ServiceInstance serviceInstance = discoveryClient.getInstances(DEPOSIT_SERVICE_NAME)
                .stream()
                .findFirst()
                .orElse(null);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(serviceInstance.getUri().toString() + "/request/new");

        restTemplate.postForEntity(uriComponentsBuilder.toUriString(), depositDTO, DepositDTO.class);


        String phoneNumber = user.getPhoneNumber();

        serviceInstance = discoveryClient.getInstances(NOTIFICATION_SERVICE_NAME)
                .stream()
                .findFirst()
                .orElse(null);

        uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(serviceInstance.getUri().toString() + "/send-sms")
                .queryParam("phoneNumber", phoneNumber);

        String generatedCode =  restTemplate.getForEntity(uriComponentsBuilder.toUriString(), String.class).getBody();


        //TODO
        serviceInstance = discoveryClient.getInstances(CUSTOMER_SERVICE_NAME)
                .stream()
                .findFirst()
                .orElse(null);

        uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(serviceInstance.getUri().toString() + "/customer/code/save")
                .queryParam("phoneNumber", phoneNumber)
                .queryParam("generatedCode", generatedCode);

        restTemplate.getForEntity(uriComponentsBuilder.toUriString(), Void.class);


        return "Введите код из смс";
    }

    public String verifyRequest(String smsCode) {
        MyUser user = getCurrentSessionUser();
        String phoneNumber = user.getPhoneNumber();

        ServiceInstance serviceInstance = discoveryClient.getInstances(CUSTOMER_SERVICE_NAME)
                .stream()
                .findFirst()
                .orElse(null);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(serviceInstance.getUri().toString() + "/customer/code/verify")
                .queryParam("phoneNumber", phoneNumber)
                .queryParam("smsCode", smsCode);

        boolean isOk = Boolean.TRUE.equals(restTemplate.getForEntity(uriComponentsBuilder.toUriString(), Boolean.class).getBody());

        serviceInstance = discoveryClient.getInstances(DEPOSIT_SERVICE_NAME)
                .stream()
                .findFirst()
                .orElse(null);

        if (isOk) {
            uriComponentsBuilder = UriComponentsBuilder
                    .fromHttpUrl(serviceInstance.getUri().toString() + "/check");

            boolean isChecked = Boolean.TRUE.equals(restTemplate.getForEntity(uriComponentsBuilder.toUriString(), Boolean.class).getBody());
            if (isChecked) {
                return "Заявка одобрена";
            }
            return "Заявка отклонена, недостаточно средств на счёте";
        }

        return "Неверный код";
    }
}
