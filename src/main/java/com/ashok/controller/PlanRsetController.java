package com.ashok.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.ashok.Service.PlanService;
import com.ashok.constants.AppConstants;
import com.ashok.entity.Plan;
import com.ashok.props.AppProperties;

@RestController
public class PlanRsetController {
	//hellow
  private PlanService planService;
   
  private Map<String, String> messsges;
  
  
  public PlanRsetController(PlanService planService, AppProperties appPros) {
	this.planService=planService;
	this.messsges = appPros.getMessages();
	System.out.println(this.messsges);
  }
  
  @GetMapping("/categories")
  public ResponseEntity<Map<Integer,String>> planCategories(){
	  
	  Map<Integer,String> categories=planService.getPlanCategories();
	  
	  return new ResponseEntity<>(categories,HttpStatus.OK);
  }
  
  @PostMapping("/plan")
  public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
      String responseMsg=AppConstants.EMPTY_STR;
      boolean isSaved = planService.savePlan(plan);
      if (isSaved) {
          responseMsg =messsges.get(AppConstants.PLAN_SAVE_SUCC);
      } else {
          responseMsg =messsges.get(AppConstants.PLAN_SAVE_FAIL);
      }
      return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
  }

  @GetMapping("/plan/{planId}")
  public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
	 Plan plan=planService.getPlanById(planId);
	 return new ResponseEntity<>(plan,HttpStatus.OK);
  }
  
  @PutMapping("/plan")
  public ResponseEntity<String> updatePlan(@RequestBody Plan plan ){
	  boolean isUpdated=planService.updatePlan(plan);
		String msg=AppConstants.EMPTY_STR;
	  
		 if(isUpdated) {
			 msg=messsges.get(AppConstants.PLAN_UPDATE_SUCC);
		 }
		 else {
			msg=messsges.get(AppConstants.PLAN_UPDATE_FAIL);
		 }
		 
		 return new ResponseEntity (msg,HttpStatus.OK);
	}
  
  @DeleteMapping("/plan/{planId}")
  public ResponseEntity<Plan> deletePlan(@PathVariable Integer PlanId){
	 boolean isDeleted=planService.deletePlanById(PlanId);
	 String msg=AppConstants.EMPTY_STR;
	 if(isDeleted) {
		 msg=messsges.get(AppConstants.PLAN_DELETE_SUCC);
	 }
	 else {
		 msg=messsges.get(AppConstants.PLAN_DELETE_FAIL);
	 }
	 return new ResponseEntity (msg,HttpStatus.OK);
  	}
  
  @PutMapping("/status-change/{planId}/{status}")
  public ResponseEntity<String> statuschange(@PathVariable Integer planId,String status){
  boolean isStatusChange=planService.planStatusChange(planId, status);
  String msg=AppConstants.EMPTY_STR;
	 if(isStatusChange) {
		 msg=messsges.get(AppConstants.PLAN_STATUS_CHANGE);
	 }
	 else {
		 msg=messsges.get(AppConstants.PLAN_STATUS_CHNAGE_FAIL);
	 }
	 return new ResponseEntity (msg,HttpStatus.OK);
	}
  
}
  
