package com.emse.integrativecps2.repository;

import com.emse.integrativecps2.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
}
