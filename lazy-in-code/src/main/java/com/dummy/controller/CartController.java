package com.dummy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dummy.Model.Cart;
import com.dummy.Model.CartWithDetails;
import com.dummy.Model.Product;
import com.dummy.repository.CartRepository;
import com.dummy.repository.ProductRepository;

@RestController
@RequestMapping("api/v3/")
public class CartController {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@CrossOrigin
	@RequestMapping(value = "cart/{id}",method = RequestMethod.GET)
	public List<CartWithDetails> findAllProduct(@PathVariable long id) {
		System.out.println("cart/{id}");
		Product existProd=null;
		CartWithDetails oldCart=null;
		List<CartWithDetails> result=new ArrayList<CartWithDetails>();
		
		List<Cart> existCart=cartRepository.findByUserId(id);
		if(existCart.size()>0) {
			for (Cart cart : existCart) {
				existProd=productRepository.findOne(cart.getProdId());
				oldCart=new CartWithDetails();
				oldCart.setId(cart.getId());
				oldCart.setUserId(cart.getUserId());
				oldCart.setProdId(cart.getProdId());
				oldCart.setPrice(cart.getPrice());
				oldCart.setCount(cart.getCount());
				oldCart.setProdName(existProd.getProdName());
				oldCart.setAuthor(existProd.getAuthor());
				oldCart.setBrand(existProd.getBrand());
				oldCart.setType(existProd.getType());
				oldCart.setPerPrice(existProd.getPrice());
				oldCart.setGenre(existProd.getGenre());
				oldCart.setDesign(existProd.getDesign());
				result.add(oldCart);
			}
		}
		return result;
	}
	
	@CrossOrigin
	@RequestMapping(value = "cart",method = RequestMethod.POST)
	public Cart createAndUpdate(@RequestBody Cart cart) {
		System.out.println("cart");
		List<Cart> existCart=cartRepository.findByUserIdAndProdId(cart.getUserId(), cart.getProdId());
		if(existCart.size()==0) {
			cart=cartRepository.save(cart);
		}else {
			cart.setId(existCart.get(0).getId());
			float perPrice=existCart.get(0).getPrice()/existCart.get(0).getCount();
			cart.setCount(existCart.get(0).getCount()+1);
			cart.setPrice(perPrice*cart.getCount());
			cart=cartRepository.saveAndFlush(cart);
		}
		return cart;
	}
	
	@CrossOrigin
	@RequestMapping(value = "cart/{id}",method = RequestMethod.DELETE)
	public Cart daleteCart(@PathVariable long id) {
		Cart oldOne=cartRepository.findOne(id);
		cartRepository.delete(oldOne);
		return oldOne;
	}
	
	@CrossOrigin
	@RequestMapping(value = "cartInsert",method =RequestMethod.POST)
	public CartWithDetails updateCartAndProduct(@RequestBody CartWithDetails cartWithDetails) {
		
		Product product=new Product();
		product.setProdName(cartWithDetails.getProdName());
		product.setAuthor(cartWithDetails.getAuthor());
		product.setBrand(cartWithDetails.getBrand());
		product.setDesign(cartWithDetails.getDesign());
		product.setGenre(cartWithDetails.getGenre());
		product.setPrice(cartWithDetails.getPerPrice());
		product.setType(cartWithDetails.getType());
		
		product=productRepository.save(product);
		
		System.out.println(product.getId());
		
		Cart cart=new Cart();
		cart.setUserId(cartWithDetails.getUserId());
		cart.setProdId(product.getId());
		cart.setCount(cartWithDetails.getCount());
		cart.setPrice(cartWithDetails.getPerPrice());
		
		cart=this.createAndUpdate(cart);
		cartWithDetails.setId(cart.getId());
		return cartWithDetails;
	}
	
	
}
