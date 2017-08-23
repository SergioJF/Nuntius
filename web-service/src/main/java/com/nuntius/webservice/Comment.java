package com.nuntius.webservice;

import java.math.BigInteger;


public class Comment {

	
	private BigInteger id;
	
	private Client client;
	
	private String text;

	

	public Comment() {
		super();

	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}




	public Client getClient() {
		return client;
	}

	public void setClient(Client user) {
		this.client = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
