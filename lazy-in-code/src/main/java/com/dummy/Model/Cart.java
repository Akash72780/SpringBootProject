package com.dummy.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CART")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "USERID")
	private long userId;
	@Column(name = "PRODID")
	private long prodId;
	@Column(name = "COUNT")
	private int count;
	@Column(name = "PRICE")
	private float price;
	
	public Cart() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getProdId() {
		return prodId;
	}

	public void setProdId(long prodId) {
		this.prodId = prodId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Cart(long id, long userId, long prodId, int count, float price) {
		super();
		this.id = id;
		this.userId = userId;
		this.prodId = prodId;
		this.count = count;
		this.price = price;
	}
	
}
