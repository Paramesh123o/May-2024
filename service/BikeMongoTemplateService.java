package com.harish.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.harish.demo.document.BikeDocument;
import com.harish.demo.exception.BikeNotFoundException;
import com.harish.demo.model.Bikes;
import com.harish.demo.utils.BikeUtility;
@Service
public class BikeMongoTemplateService {
	@Autowired
	private MongoTemplate mongotemplate;
	public Bikes fetchBikeByName(String name)
	{
		System.out.println("Enter into  BikeMongoTemplateService ::: fetchBikeByName()");
		Query query =new Query();
		query.addCriteria(Criteria.where("name").is(name));
		BikeDocument bikeDocument=mongotemplate.findOne(query, BikeDocument.class);
		if(bikeDocument == null)
		{
			throw new BikeNotFoundException();
		}
		System.out.println(bikeDocument);
		Bikes bike=new Bikes();
		BeanUtils.copyProperties(bikeDocument, bike);
		return bike;
	}
	public List<Bikes> getBikePriceMoreThanGiven(Double price)
	{
		//1.query object
		Query query =new Query();
		//2.add criteria
		query.addCriteria(Criteria.where("price").gt(price));
		//3.execture
		List<BikeDocument>listOfBikeDocuments=mongotemplate.find(query, BikeDocument.class);
		List<Bikes> listOfBikes=BikeUtility.convertListOfDocumentToListofEntity(listOfBikeDocuments);
		return listOfBikes;
		
	}
	public List<Bikes> getBikesPriceLessThanGive(Double price){
		//1.query object
		Query query =new Query();
		//2.add criteria
		query.addCriteria(Criteria.where("price").lt(price));
		//3.execute
		List<BikeDocument> listOfBikeDocuments=mongotemplate.find(query, BikeDocument.class);
		List<Bikes> listOfBikes=BikeUtility.convertListOfDocumentToListofEntity(listOfBikeDocuments);
		return listOfBikes;
	}
	public List<Bikes> getBikesByFlexiSearchOnName(String regularExperssion)
	{
		Query query=new Query();
		query.addCriteria(Criteria.where("name").regex(regularExperssion, "i"));
		List<BikeDocument> listOfBikeDocuments=mongotemplate.find(query, BikeDocument.class);
		List<Bikes> listOfBikes=BikeUtility.convertListOfDocumentToListofEntity(listOfBikeDocuments);
		return listOfBikes;
	}

}
