package com.harish.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harish.demo.model.Bikes;
import com.harish.demo.repository.BikeRepository;
import com.harish.demo.service.BikeService;

import jakarta.validation.constraints.Pattern;
@RestController
@RequestMapping("/Bike")
public class BikeController {
@Autowired
private BikeService service;

//@RequestBody
//model bike
//serivcei method call
@PostMapping("/save/bike")
public ResponseEntity<String> saveBike(@RequestBody Bikes bike)
{
	try {
	String message=service.saveBike(bike);
	ResponseEntity<String> response=new ResponseEntity<String>(message,HttpStatus.CREATED);
    return response;
	}
	catch(Exception e) {
		throw e;
	}
	
}
@PutMapping("/update/bike")
public ResponseEntity<String> updateBike(@RequestBody Bikes bike)
{
	try {
	String message=service.updateBike(bike);
	ResponseEntity<String> response=new ResponseEntity<String>(message,HttpStatus.OK);
    return response;
	}
	catch (Exception e) {
		// TODO: handle exception
		throw e;
	}
	
}
@GetMapping("/find/{id}")
public ResponseEntity<?> getOneBike(@PathVariable String id)
{
	ResponseEntity<?> response=null;
	try
	{
		Bikes bike=service.bikgetOneBike(id);
		response=new ResponseEntity<Bikes>(bike,HttpStatus.OK);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		throw e;
	}
	return response;
}


@PostMapping("/save/all/bikes")
public ResponseEntity<?> saveAllBikes(@RequestBody List<Bikes> bikes)
{
	ResponseEntity<?> response=null;
	try
	{
		List<Bikes> listOfBikes=service.saveMultipleBikes(bikes);
		response=new ResponseEntity<List<Bikes>>(listOfBikes, HttpStatus.OK);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		throw e;
		
	}
	return response;
}
//simple
//no arguements
//return list<Bikes>
@GetMapping("/get/all/bikes")
public ResponseEntity<?> getAllBikes()
{
	ResponseEntity<?> response=null;
	try
	{
		List<Bikes> listOfBikes=service.findAllBikes();
		response=new ResponseEntity<List<Bikes>>(listOfBikes,HttpStatus.OK);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		throw e;
	}
	return response;
}
//method type -GET
//return type List<Bikes>
//body arguemnts List<String>FV
@PostMapping("/based/on/ids")
public ResponseEntity<?> fetchBikesByIds(@RequestBody List<String> ids)
{
	ResponseEntity<?> response=null;
	try
	{
	List<Bikes> listOfMatchedBikes=service.findAllBikesById(ids);
	response=new ResponseEntity<List<Bikes>>(listOfMatchedBikes,HttpStatus.OK);
	}
	catch (Exception e) {
		e.printStackTrace();
		throw e;
		// TODO: handle exception
	}
	return response;
}
@DeleteMapping("/delete/id")
public ResponseEntity<?> removeBikeById(@RequestParam  @Pattern(regexp = "^[a-zA-Z0-9_-]{1,50}$") String bikeId )
{
	ResponseEntity<?> response =null;
	try
	{
	String message=service.deleteBikeById(bikeId);
	response=new ResponseEntity<String>(message,HttpStatus.OK);	
	}catch (Exception e) {
		e.printStackTrace();
		throw e;
	}
	return response;
}


@DeleteMapping("/remove/bike/ids")
public ResponseEntity<?> removeBikeById(@RequestBody List<String> listOfBikeWantedToRemove) throws Exception
{
	ResponseEntity<?> response=null;
	try
	{
		List<Bikes> listOfBikesRemoved=service.deleteBikesByIds(listOfBikeWantedToRemove);
		response=new ResponseEntity<List<Bikes>>(listOfBikesRemoved,HttpStatus.OK);
	}
	catch (Exception e) {
		// TODO: handle exception
	    e.printStackTrace();
	    throw e;
	}
	return response;
}

@DeleteMapping("/remove/all")
public ResponseEntity<?> removeAllBikes()
{
	ResponseEntity<?> response=null;
	try
	{
		List<Bikes> listOfBikes=service.deleteAllBikes();
		response=new ResponseEntity<List<Bikes>>(listOfBikes,HttpStatus.OK);
	}
	catch (Exception e) {
		// TODO: handle exception
		throw e;
	}
	return response;
}
//requestParam  -->BikeName
//return  -- > Bike
//@gET
@GetMapping("/fetch/by")
public ResponseEntity<?> fetchBikeByName(@RequestParam String bikeName)
{
	ResponseEntity<?> response=null;
	try
	{
		Bikes bike=service.getBikeByName(bikeName);
		response=new ResponseEntity<Bikes>(bike,HttpStatus.OK);
	}catch (Exception e) {
		// TODO: handle exception
		throw e;
	}
	return response;
}
@GetMapping("/fetch/bike/lessthan")
public ResponseEntity<?> fetchBikeByName(@RequestParam Double bikePrice)
{
	ResponseEntity<?> response=null;
	try
	{
		List<Bikes> listOfbikes=service.getBikeByCostLessThan(bikePrice);
		response=new ResponseEntity<List<Bikes>>(listOfbikes,HttpStatus.OK);
	}catch (Exception e) {
		// TODO: handle exception
		throw e;
	}
	return response;
}
@GetMapping("/fetch/bike/company/null")
public ResponseEntity<?> fetchBikeByCompanyIsNull()
{
	ResponseEntity<?> response=null;
	try
	{
		List<Bikes> listOfbikes=service.getBikeByCompanyIsNull();
		response=new ResponseEntity<List<Bikes>>(listOfbikes,HttpStatus.OK);
	}catch (Exception e) {
		// TODO: handle exception
		throw e;
	}
	return response;
}

//get
//no input
//output->List of string
@GetMapping("/unique/company")
public ResponseEntity<?> getUniqueBikeCompany()
{
	ResponseEntity<?> response=null;
	try
	{
		List<String> uniqueCompanies=service.getUniqueCompanyNames();
		response=new ResponseEntity<>(uniqueCompanies,HttpStatus.OK);
		
	}
	catch (Exception e) {
		e.printStackTrace();
		throw e;
		// TODO: handle exception
	}
	return response;
}
@PostMapping("/seleced/companies")
public ResponseEntity<?> fethSelectedCompanies(@RequestBody List<String> listOfCompanies)
{
	ResponseEntity<?> response=null;
	try
	{
		List<Bikes> uniqueCompanies=service.selectedCompanyBikes(listOfCompanies);
		response=new ResponseEntity<>(uniqueCompanies,HttpStatus.OK);
		
	}
	catch (Exception e) {
		e.printStackTrace();
		throw e;
		// TODO: handle exception
	}
	return response;
}
//get
//input ->Two query parameter
//outpu ->list bike

@GetMapping("/price/range")
public ResponseEntity<?> getBikeRanges(@RequestParam(required = true) Double lowesPrice,@RequestParam(required = true) Double highestPrice)
{
	ResponseEntity<?> response=null;
	try
	{
		List<Bikes> bikesInGivePriceRange=service.findBikeBetweenPrice(lowesPrice,highestPrice);
		response=new ResponseEntity<>(bikesInGivePriceRange,HttpStatus.OK);
		
	}
	catch (Exception e) {
		e.printStackTrace();
		throw e;
		// TODO: handle exception
	}
	return response;
}
@GetMapping("/price/greaterthan/count/{price}")
public ResponseEntity<?> getCountBikeGreaterThan(@PathVariable Double price)
{
	ResponseEntity<?> response=null;
	try
	{
	    Integer count =service.countPriceGerterThan(price);
		response=new ResponseEntity<>(count,HttpStatus.OK);
		
	}
	catch (Exception e) {
		e.printStackTrace();
		throw e;
		// TODO: handle exception
	}
	return response;
}
@GetMapping("/isExist")
public ResponseEntity<?> isExistByName(@RequestParam String name)
{
	ResponseEntity<?> response=null;
	try
	{
		Boolean isExist=service.isBikeWithNameExist(name);
		response=new ResponseEntity<Boolean>(isExist,HttpStatus.OK);
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return response;
}
@GetMapping("/find")
public ResponseEntity<?> getListOfBikes(@RequestParam Double price)
{
	ResponseEntity<?> response=null;
	try
	{
		List<Bikes> bike=service.getBikeGreatherThan(price);
		response=new ResponseEntity<List<Bikes>>(bike,HttpStatus.OK);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		throw e;
	}
	return response;
}
}
