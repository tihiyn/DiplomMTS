package ru.mts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.dto.AccountDTO;
import ru.mts.dto.DepositDTO;
import ru.mts.service.AggregatorService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AggregatorController {
    private final AggregatorService aggregatorService;

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("id") int id) {
        return ResponseEntity.ok(aggregatorService.getAccount(id));
    }

    @PostMapping("/deposit/predict")
    public ResponseEntity<Double> predictRate(@RequestBody DepositDTO depositDTO) {
        return ResponseEntity.ok(aggregatorService.predictRate(depositDTO));
    }

    @PostMapping("/request/new")
    public String createRequest(@RequestBody DepositDTO depositDTO) {
        return aggregatorService.createRequest(depositDTO);
    }

    public String verifyRequest(@RequestParam("smsCode") String smsCode) {
        return aggregatorService.verifyRequest(smsCode);
    }
}
