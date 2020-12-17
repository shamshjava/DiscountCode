package com.discount.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.discount.model.DiscountCode;

@Repository
public interface DiscountRepository extends MongoRepository<DiscountCode,ObjectId>{
	
	public DiscountCode findByassignedRecipient(String email);

}
