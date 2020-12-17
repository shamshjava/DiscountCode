package com.discount.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DiscountCode")
public class DiscountCode {
	
	@Id
	private int id;
	private String offerCode;
	private String assignedRecipient;
	private Date expirationDate;
	private boolean used;
	private Date usedDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	public String getAssignedRecipient() {
		return assignedRecipient;
	}
	public void setAssignedRecipient(String assignedRecipient) {
		this.assignedRecipient = assignedRecipient;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
	public Date getUsedDate() {
		return usedDate;
	}
	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}
	
	

}
