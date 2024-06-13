package ru.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.entity.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {
}
