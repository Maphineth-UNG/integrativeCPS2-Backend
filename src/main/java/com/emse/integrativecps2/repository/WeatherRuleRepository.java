package com.emse.integrativecps2.repository;

import com.emse.integrativecps2.entity.WeatherRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRuleRepository extends JpaRepository<WeatherRule, Long> {
}
