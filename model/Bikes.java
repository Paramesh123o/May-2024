package com.harish.demo.model;

public class Bikes {
private String id;
private String name;
private String company;
private Double price;
private Integer mileage;
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
	return mileage;
}
public void setMilleage(Integer milleage) {
	this.mileage = milleage;
}
public Boolean getIsABS() {
	return isABS;
}
public void setIsABS(Boolean isABS) {
	this.isABS = isABS;
}
@Override
public String toString() {
	return "Bikes [id=" + id + ", name=" + name + ", company=" + company + ", price=" + price + ", milleage=" + mileage
			+ ", isABS=" + isABS + "]";
}
public Bikes() {
	super();
	// TODO Auto-generated constructor stub
}

}
