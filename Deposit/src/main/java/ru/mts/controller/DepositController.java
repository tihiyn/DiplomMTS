package ru.mts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.dto.DepositDTO;
import ru.mts.entity.Deposit;
import ru.mts.entity.DepositTerm;
import ru.mts.entity.DepositType;
import ru.mts.entity.TypePercentPayment;
import ru.mts.service.DepositService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/deposit")
public class DepositController {
    private final DepositService depositService;

//    @GetMapping("/{id}")
//    public ResponseEntity<DepositDTO> getDepositById(@PathVariable("id") int id) {
//        return ResponseEntity.ok(depositService.getDepositById(id));
//    }
//
//    @GetMapping()
//    public ResponseEntity<List<DepositDTO>> getAllDeposits() {
//        return ResponseEntity.ok(depositService.getAllDeposits());
//    }
//
//    @PostMapping("/new")
//    public void createDeposit(@RequestBody int depositAccountId,
//                              @RequestBody DepositType depositType,
//                              @RequestBody DepositTerm depositTerm,
//                              @RequestBody TypePercentPayment typePercentPayment,
//                              @RequestBody BigDecimal depositAmount,
//                              @RequestBody LocalDate startDate, @RequestBody LocalDate endDate,
//                              @RequestBody boolean capitalization) {
//        depositService.createDeposit(depositAccountId, depositType, depositTerm, typePercentPayment,
//                depositAmount, startDate, endDate, capitalization);
//    }

    @PostMapping("/predict")
    public ResponseEntity<Double> predictRate(@RequestBody DepositDTO depositDTO) {
        return ResponseEntity.ok(depositService.predictRate(depositDTO));
    }
}
