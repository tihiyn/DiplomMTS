package ru.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.entity.Code;

@Repository
public interface CodeRepository extends JpaRepository<Code, Integer> {
}
