package ru.mts.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mts.dto.DepositDTO;
import ru.mts.repository.DepositRepository;

@RequiredArgsConstructor
@Service
public class DepositService {
    private final DepositRepository depositRepository;

    public Double predictRate(DepositDTO depositDTO) {
        double rate = 12;

        switch (depositDTO.getDepositType()) {
            case "С пополнением, но без снятия" -> rate += 1;
            case "Без пополнения и без снятия" -> rate += 2;
        }

        switch (depositDTO.getDepositTerm()) {
            case 6 -> rate += 1;
            case 12 -> rate += 2;
        }

        if (depositDTO.getDepositAmount().doubleValue() > 100_000) {
            rate += 1;

            if (depositDTO.getDepositAmount().doubleValue() > 1_000_000) {
                rate += 1;
            }
        }

        return rate;
    }
}
