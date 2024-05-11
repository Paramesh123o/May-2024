package com.harish.demo.document;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="bike_showroom")
public class BikeDocument {
	
private String id;
private String name;
private String company;
private Double price;
private Integer milleage;
private Boolean isABS;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public Integer getMilleage() {
	return milleage;
}
public void setMilleage(Integer milleage) {
	this.milleage = milleage;
}
public Boolean getIsABS() {
	return isABS;
}
public void setIsABS(Boolean isABS) {
	this.isABS = isABS;
}
public BikeDocument() {
	super();
	// TODO Auto-generated constructor stub
}
public BikeDocument(String id, String name, String company, Double price, Integer milleage, Boolean isABS) {
	super();
	this.id = id;
	this.name = name;
	this.company = company;
	this.price = price;
	this.milleage = milleage;
	this.isABS = isABS;
}


}

