package com.discount.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.discount.model.Offer;

public interface OfferRepository extends MongoRepository<Offer, Integer>{

	Offer findByofferName(String offerCode);

}
