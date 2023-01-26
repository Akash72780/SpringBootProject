package com.dummy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dummy.Model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	
	@Query("SELECT u from Cart u where u.userId =:userId and u.prodId=:prodId ") 
	List<Cart> findByUserIdAndProdId(@Param("userId") Long userId, @Param("prodId") Long prodId);
	
	@Query("SELECT u from Cart u where u.userId =:userId ") 
	List<Cart> findByUserId(@Param("userId") Long userId);

}
