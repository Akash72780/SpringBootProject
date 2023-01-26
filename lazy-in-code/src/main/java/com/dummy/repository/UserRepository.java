package com.dummy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dummy.Model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u from User u where u.email =:email ")       // using @query
    List<User> findByEmail(@Param("email") String email);

}
