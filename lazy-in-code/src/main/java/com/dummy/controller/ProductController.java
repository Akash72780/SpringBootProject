package com.dummy.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dummy.Model.Product;
import com.dummy.repository.ProductRepository;

@RestController
@RequestMapping("api/v1/")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@CrossOrigin
	@RequestMapping(value = "product", method = RequestMethod.GET)
	 public List<Product> listOfProduct(){
		 return productRepository.findAll();
	 }
	
	@CrossOrigin
	@RequestMapping(value = "product",method = RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		return productRepository.saveAndFlush(product);
	}
	
	@CrossOrigin
	@RequestMapping(value = "product/{id}",method = RequestMethod.GET)
	public Product get(@PathVariable long id) {
		return productRepository.findOne(id);
	}
	
	@CrossOrigin
	@RequestMapping(value = "product/{id}",method = RequestMethod.PUT)
	public Product update(@PathVariable long id,@RequestBody Product product) {
		Product oldOne=productRepository.findOne(id);
		product.setId(oldOne.getId());
		BeanUtils.copyProperties(product, oldOne);
		return productRepository.saveAndFlush(oldOne);
	}
	
	@CrossOrigin
	@RequestMapping(value = "product/{id}",method = RequestMethod.DELETE)
	public Product delete(@PathVariable long id) {
		Product oldOne=productRepository.findOne(id);
		productRepository.delete(oldOne);
		return oldOne;
	}
}
