package com.ashok.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashok.entity.Plan;


public interface PlanRepo extends JpaRepository<Plan, Integer> {

	
	
}
