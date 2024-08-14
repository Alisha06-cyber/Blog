package com.ashok.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.TypeAlias;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="PLAN_Master")
public class Plan {
 @Id
 @GeneratedValue
  private Integer planId;
  private String planName;
  private LocalDate planStartDate;
  private LocalDate planEndDate;
  private String ActiveSW;
  private Integer PlanCategoryId;
  private String createdBy;
  private String updatedBY;
  private LocalDate createdDate;
  private LocalDate updatedDate;
  
}
