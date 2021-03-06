package com.discount.service;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.discount.model.DiscountCode;
import com.discount.model.Offer;
import com.discount.model.Recipient;
import com.discount.repository.DiscountRepository;
import com.discount.repository.OfferRepository;
import com.discount.repository.RecipientRepository;

@Service
public class DiscountService {
	
	final int codeLength = 8;
	
	@Autowired
	DiscountRepository discountRepo;
	
	@Autowired
	RecipientRepository recipientRepo;
	
	@Autowired
	OfferRepository offerRepo;
	
	public Object getDiscountCode(String name, String email) {
		Recipient rec = recipientRepo.findByemail(email);
		if(rec != null)
			return "Already Discount Code is generated for this Recipient";
		addRecipient(name,email);
		DiscountCode disCode = new DiscountCode();
		disCode.setAssignedRecipient(email);
		disCode.setExpirationDate(expiryDate());
		String generatedCode = createRandomCode(codeLength);
		disCode.setOfferCode(generatedCode);
		disCode.setUsed(false);
		DiscountCode disCodeNew = discountRepo.save(disCode);
		Offer offer1 = null;
		if(disCodeNew != null)
			 offer1 = addOffer(generatedCode);
		if(offer1 == null)
			return null;
		else
			return disCode;
	}

	private Offer addOffer(String generatedCode) {

		Offer offer = new Offer();
		offer.setOfferName(generatedCode);
		offer.setPercentage(assignPercentage());
		Offer offerNew = offerRepo.save(offer);
		if(offerNew == null)
			return null;
		else
			return offerNew;
	}

	private int assignPercentage() {
		
		 Random random = new Random();        
	        int inputNumber = 20;
	        int generatedNumber = 0;
	        for (int i = 5; i < inputNumber; i++) {
	            generatedNumber = random.nextInt(inputNumber)+5;

	        }
		return generatedNumber;
	}

	private Date expiryDate() {
		Date currentDate = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        c.add(Calendar.DATE, 7); 
        Date currentDatePlusOne = c.getTime();
		return currentDatePlusOne;
	}

	private void addRecipient(String name, String email) {
		Recipient rec = new Recipient();
		rec.setName(name);
		rec.setEmail(email);
		recipientRepo.save(rec);
		
	}

	public Object getgetDiscountCode(String email, String discountCode) {
		int percentage = 0;
		DiscountCode disCode = discountRepo.findByassignedRecipient(email);
		if(disCode != null) {
			disCode.setUsed(true);
			disCode.setUsedDate(new Date());
			Offer offer = offerRepo.findByofferName(disCode.getOfferCode());
			if(offer.getOfferName().equals(discountCode))
				percentage = offer.getPercentage();
			else
				return "Invalid Offer Code";
			discountRepo.save(disCode);
		}else {
			return "Invalid Recipient";
		}
		return percentage;
	}
	
	public String createRandomCode(int codeLength){   
	     char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
	        StringBuilder sb = new StringBuilder();
	        Random random = new SecureRandom();
	        for (int i = 0; i < codeLength; i++) {
	            char c = chars[random.nextInt(chars.length)];
	            sb.append(c);
	        }
	        String output = sb.toString();
	        return output ;
	    }

}
