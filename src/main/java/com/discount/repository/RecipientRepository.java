package com.discount.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.discount.model.Recipient;

public interface RecipientRepository extends MongoRepository<Recipient, Integer>{

}
