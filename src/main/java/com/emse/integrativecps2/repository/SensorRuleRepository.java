package com.emse.integrativecps2.repository;

import com.emse.integrativecps2.entity.SensorRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRuleRepository extends JpaRepository<SensorRule, Long> {
}
