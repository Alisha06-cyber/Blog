package com.ashok.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="PLAN_CATEGORY")
public class PlanCategory {
	@Id
	@GeneratedValue
	private Integer categoryId;
	private String categoryName;
	private String activeSw;
	private String  createdBy;
	private String updatedBy;
	@Column(name="CREATED_DATE",updatable =false)
	@CreationTimestamp
	private LocalDate createDate;
	@Column(name="UPDATED_DATE",insertable =false)
	@CreationTimestamp
	private LocalDate updateDate;
 
 
}
