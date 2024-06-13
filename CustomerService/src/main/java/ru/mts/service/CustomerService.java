package ru.mts.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mts.entity.Code;
import ru.mts.entity.Customer;
import ru.mts.repository.CodeRepository;
import ru.mts.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CodeRepository codeRepository;

    @Transactional
    public Customer findByPhoneNumber(String phoneNumber) {
        System.out.println(phoneNumber);
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElse(null);
        System.out.println(customer.getPhoneNumber());
        return customer;
    }

    @Transactional
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional
    public void saveCode(String phoneNumber, String code) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElse(null);
        codeRepository.save(new Code(customer, code));
//        customer.setCode(new Code(code));
    }

    @Transactional
    public boolean verifyCode(String phoneNumber, String smsCode) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElse(null);

        Code code = customer.getCode();
        System.out.println(customer.getCode().getCode());

        boolean isOk = smsCode.equals(customer.getCode().getCode());

//        codeRepository.delete(customer.getCode());
//        codeRepository.deleteById(3);
//        codeRepository.flush();
        customer.setCode(null);
        customerRepository.save(customer);
        codeRepository.delete(code);
//        codeRepository.delete();

//        System.out.println(customer.getCode().getCode());
//        customer.setCode(null);

        return isOk;
    }
}
