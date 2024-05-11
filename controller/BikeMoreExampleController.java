package com.harish.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harish.demo.model.Bikes;
import com.harish.demo.service.BikeMongoTemplateService;

@RestController
@RequestMapping("/bike/mongo/template")
public class BikeMoreExampleController {
@Autowired
private BikeMongoTemplateService service;
//get
//input string @RequestParam
//Bikes
@GetMapping("/get/by/name")
public ResponseEntity<?> getByName(@RequestParam String name)
{
	ResponseEntity<?> response=null;
	try
	{
		Bikes bike=service.fetchBikeByName(name);
		response=new ResponseEntity<Bikes>(bike,HttpStatus.OK);
	}
	catch (Exception e) {
       e.printStackTrace();
       throw e;
	}
	return  response;
}
@GetMapping("/get/list/of/bikes/greaterthan/by/price")
public ResponseEntity<?> getByName(@RequestParam Double price)
{
	ResponseEntity<?> response=null;
	try
	{
		List<Bikes> bike=service.getBikePriceMoreThanGiven(price);
		response=new ResponseEntity<List<Bikes>>(bike,HttpStatus.OK);
	}
	catch (Exception e) {
       e.printStackTrace();
       throw e;
	}
	return  response;
}

@GetMapping("/get/list/of/bikes/lessthan/by/price")
public ResponseEntity<?> getLessThanPrice(@RequestParam Double price)
{
	ResponseEntity<?> response=null;
	try
	{
		List<Bikes> bike=service.getBikesPriceLessThanGive(price);
		response=new ResponseEntity<List<Bikes>>(bike,HttpStatus.OK);
	}
	catch (Exception e) {
       e.printStackTrace();
       throw e;
	}
	return  response;
}
@GetMapping("/get/list/of/bikes/by/name")
public ResponseEntity<?> getBikesByNameFlexiSearch(@RequestParam String name)
{
	ResponseEntity<?> response=null;
	try
	{
		List<Bikes> bike=service.getBikesByFlexiSearchOnName(name);
		response=new ResponseEntity<List<Bikes>>(bike,HttpStatus.OK);
	}
	catch (Exception e) {
       e.printStackTrace();
       throw e;
	}
	return  response;
}
}
