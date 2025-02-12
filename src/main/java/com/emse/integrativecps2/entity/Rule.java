package com.emse.integrativecps2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rules")
@Data
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String triggerType;
    private String condition;
    private double threshold;
    private String action;
}
