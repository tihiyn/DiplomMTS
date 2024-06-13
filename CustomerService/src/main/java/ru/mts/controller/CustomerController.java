package ru.mts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mts.entity.Customer;
import ru.mts.service.CustomerService;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get")
    public Customer findByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber) {
        return customerService.findByPhoneNumber(phoneNumber);
    }

    @PostMapping("/save")
    public void save(@RequestBody Customer customer) {
        customerService.save(customer);
    }

    @GetMapping("/code/save")
    public void saveCode(@RequestParam("phoneNumber") String phoneNumber,
                         @RequestParam("generatedCode") String code) {
        customerService.saveCode(phoneNumber, code);
    }

    @GetMapping("/code/verify")
    public boolean verifyCode(@RequestParam("phoneNumber") String phoneNumber,
                              @RequestParam("smsCode") String smsCode) {
        return customerService.verifyCode(phoneNumber, smsCode);
    }
}
