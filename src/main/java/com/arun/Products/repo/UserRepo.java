package com.arun.Products.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arun.Products.model.Users;

@Repository
public interface UserRepo extends MongoRepository<Users,String>{

	Optional<Users> findByUsername(String username);

}
