package com.harish.demo.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.BeanUtils;

import com.harish.demo.document.BikeDocument;
import com.harish.demo.model.Bikes;

public class BikeUtility {
	public static List<BikeDocument> convertListsOfEntityToListOfDocument(List<Bikes> listOfEntity)
	{
		List<BikeDocument> listOfBikeDocuments=new LinkedList<BikeDocument>();
		for(Bikes bike : listOfEntity)
		{
			BikeDocument bikeDocument =new BikeDocument();
			BeanUtils.copyProperties(bike, bikeDocument);
			listOfBikeDocuments.add(bikeDocument);
		}
		return listOfBikeDocuments;
	}
	public static List<Bikes> convertListOfDocumentToListofEntity(List<BikeDocument> listOfDocument)
	{
		List<Bikes> listOfBikes=new Stack();
		for(BikeDocument bikeDocument : listOfDocument)
		{
			Bikes bike=new Bikes();
			BeanUtils.copyProperties(bikeDocument,bike);
			listOfBikes.add(bike);
		}
		return listOfBikes;
	}

}
