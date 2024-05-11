package com.harish.demo.service;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.harish.demo.document.BikeDocument;
import com.harish.demo.exception.BikeNotFoundException;
import com.harish.demo.model.Bikes;
import com.harish.demo.repository.BikeRepository;
import com.harish.demo.utils.BikeUtility;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
@Service
public class BikeService {
@Autowired
private BikeRepository bikeRepository;
@Autowired
private MongoTemplate mongoTemplate;

public String saveBike(Bikes bike)
{
	BikeDocument bikeDoucment=new BikeDocument();
	BeanUtils.copyProperties(bike, bikeDoucment);
	BikeDocument updatedBike=bikeRepository.save(bikeDoucment);
	return "Bike with id = "+updatedBike.getId()+ "  save sucefully";
}

public String updateBike(Bikes bike)
{
	//check it is present or not 
	//if it present then only update
	BikeDocument bikeDoucment=new BikeDocument();
	BeanUtils.copyProperties(bike, bikeDoucment);
	if (bike.getId()== null || !bikeRepository.existsById(bike.getId()))
	{
		throw new BikeNotFoundException("Product with id = "+bike.getId()+" doesnt exist");
	}
	BikeDocument updatedBike=bikeRepository.save(bikeDoucment);
	return "Bike with id = "+updatedBike.getId()+ "  save sucefully";
}
public Bikes bikgetOneBike(String id){
	Optional<BikeDocument>optionalBikeDocument=bikeRepository.fetchBikeById(id);
	if (!optionalBikeDocument.isPresent())
	{
		throw new BikeNotFoundException("Product with id = "+id+" doesnt exist");
	}
	BikeDocument bikeDocument= optionalBikeDocument.get();
	Bikes bike=new Bikes();
	BeanUtils.copyProperties(bikeDocument, bike);
	return bike;
}

public List<Bikes> saveMultipleBikes(List<Bikes> bikes)
{
	List<BikeDocument> listOfBikeDocuments=BikeUtility.convertListsOfEntityToListOfDocument(bikes);
	List<BikeDocument> savedBikeDocuments= bikeRepository.saveAll(listOfBikeDocuments);
	bikes.clear();
	bikes=BikeUtility.convertListOfDocumentToListofEntity(savedBikeDocuments);
	return bikes;
	

}

public List<Bikes> findAllBikes()
{
	List<BikeDocument> listOfBikeDocuments=bikeRepository.findAll();
	List<Bikes> bikes=new ArrayList<>();
	//convert
	for(BikeDocument bikeDocument:listOfBikeDocuments)
	{
		Bikes bike=new Bikes();
		BeanUtils.copyProperties(bikeDocument, bike);
		bikes.add(bike);
	}
	return bikes;
}

public List<Bikes> findAllBikesById(List<String> ids)
{
	List<BikeDocument> listOfBikeDocuments=bikeRepository.findAllById(ids);
	List<Bikes> listOfMatchedBikes=BikeUtility.convertListOfDocumentToListofEntity(listOfBikeDocuments);
	
	return listOfMatchedBikes;
}

public String deleteBikeById(String id)
{
	if (!bikeRepository.existsById(id))
	{
		throw new BikeNotFoundException("Thier is no match bike found for this id = "+id);
	}
	bikeRepository.deleteById(id);
	return "Succefully deleted bike with given id = "+id;
}
public List<Bikes> deleteBikesByIds(List<String> listOfIdsNeedToDelete) throws Exception
{
	if(listOfIdsNeedToDelete.isEmpty() && listOfIdsNeedToDelete ==null)
		throw new Exception("Empty list or null pointer excepion");
	List<BikeDocument> listOfBikeDocument=bikeRepository.findAllById(listOfIdsNeedToDelete);
	List<Bikes> listOfMatchedBikes=BikeUtility.convertListOfDocumentToListofEntity(listOfBikeDocument);
	bikeRepository.deleteAllById(listOfIdsNeedToDelete);
	return listOfMatchedBikes;
}
public List<Bikes> deleteAllBikes()
{
List<BikeDocument> listOfBikeDocument=bikeRepository.findAll();
List<Bikes> listOfBikes=BikeUtility.convertListOfDocumentToListofEntity(listOfBikeDocument);
bikeRepository.deleteAll();
return listOfBikes;
}

public Bikes getBikeByName(String bikeName)
{
	Optional<BikeDocument> optionalBike=bikeRepository.findByName(bikeName);
	if (optionalBike.isEmpty())
	{
		throw new BikeNotFoundException("Bike with given name  = "+bikeName+" Not found");
	}
	Bikes bike=new Bikes();
	BikeDocument bikeDocument=optionalBike.get();
	BeanUtils.copyProperties(bikeDocument, bike);
	return bike;
}
public List<Bikes> getBikeByCostLessThan(Double price)
{
	List<BikeDocument> listOfBikeDocument=bikeRepository.findByPriceLessThan(price);
	List<Bikes> listOfMatchedBikes=BikeUtility.convertListOfDocumentToListofEntity(listOfBikeDocument);
	
	return listOfMatchedBikes;
}
public List<Bikes> getBikeByCompanyIsNull()
{
	List<BikeDocument> listOfBikeDocument=bikeRepository.findByCompanyIsNull();
	List<Bikes> listOfMatchedBikes=BikeUtility.convertListOfDocumentToListofEntity(listOfBikeDocument);
	
	return listOfMatchedBikes;
}
public List<String> getUniqueCompanyNames()
{
	return bikeRepository.getUniqueCompanyNames();
}
public List<Bikes> selectedCompanyBikes(List<String> companies)
{
  List<BikeDocument> listOfDocuments=bikeRepository.fetchSelectedCompanyDetails(companies);
  List<Bikes> listOfBikes=new ArrayList();
  for(BikeDocument document:listOfDocuments)
  {
	  Bikes bike=new Bikes();
	  BeanUtils.copyProperties(document, bike);
	  listOfBikes.add(bike);
  }
  return listOfBikes;
}

public List<Bikes> findBikeBetweenPrice(Double lowesPrice,Double highestPrice)
{
  List<BikeDocument> listOfDocuments=bikeRepository.findBikeBetweenPrice(lowesPrice,highestPrice);
  List<Bikes> listOfBikes=new ArrayList();
  for(BikeDocument document:listOfDocuments)
  {
	  Bikes bike=new Bikes();
	  BeanUtils.copyProperties(document, bike);
	  listOfBikes.add(bike);
  }
  return listOfBikes;
}
public Integer countPriceGerterThan(Double price)
{
	return bikeRepository.countPriceGreaterThanGivePrice(price);
}
public Boolean isBikeWithNameExist(String name)
{
	return bikeRepository.isBikeNameExist(name);
}
public List<Bikes> getBikeGreatherThan(Double price)
{
    Query query = new Query();
    query.addCriteria(Criteria.where("price").is(price));
    List<BikeDocument> listOfBikeDocuments=mongoTemplate.find(query, BikeDocument.class);
    List<Bikes> listOfBikes=BikeUtility.convertListOfDocumentToListofEntity(listOfBikeDocuments);
    return listOfBikes;
    
    
}




}
