package com.dummy.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 long id;
	
	@Column(name = "NAME")
	 String prodName;
	
	@Column(name = "PRICE")
	 float price;
	
	@Column(name = "GENRE")
	 String genre;
	
	@Column(name = "AUTHOR")
	 String author;
	
	@Column(name = "TYPE")
	 String type;
	
	@Column(name = "BRAND")
	 String brand;
	
	@Column(name = "DESIGN")
	 String design;
	
	public Product() {
		super();
	}
	
	public Product(long prodId, String prodName, float price, String genre, String author, String type, String brand,
			String design) {
		super();
		this.id = prodId;
		this.prodName = prodName;
		this.price = price;
		this.genre = genre;
		this.author = author;
		this.type = type;
		this.brand = brand;
		this.design = design;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long prodId) {
		this.id = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}
	
}
