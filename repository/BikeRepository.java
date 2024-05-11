package com.harish.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.harish.demo.document.BikeDocument;

public interface BikeRepository extends MongoRepository<BikeDocument, String> {
//SQL :SELECT * FROM BIKE WHERE name=?
//findBy<variable><condition>
public Optional<BikeDocument> findByName(String bikeName);
//SELECT * FROM BIKE WHERE COST<?
//findBy<variable><Condtion>
List<BikeDocument> findByPriceLessThan(Double price);
//SELECT * FROM PUBLIC.Bike where company is null;
//findBy<Variable><condition>
List<BikeDocument> findByCompanyIsNull();

//SELECT distinct(name) from bikes
@Query(value= "{'company': {$exists: true}}",fields= "{'company':0}" ,sort= "{price:1}")
List<String> getUniqueCompanyNames();

@Query(value="{'company':{$in:?0}}",sort= "{price:1}")
List<BikeDocument> fetchSelectedCompanyDetails(List<String> companyNames);

@Query(value="{$and:[{'price':{$gte:?0},'price':{$lte:?1}}]}",sort="{price:1}")
List<BikeDocument> findBikeBetweenPrice(Double lowesPrice,Double highestPrice);

@Query(value="{'_id':?0}",sort="{price:1}")
Optional<BikeDocument> fetchBikeById(String bikeId);

@Query(value= "{'price':{$gt:?0}}",count=true)
Integer countPriceGreaterThanGivePrice(Double price);
@Query(value= "{'name':?0}",exists=true)
Boolean isBikeNameExist(String name);


}
