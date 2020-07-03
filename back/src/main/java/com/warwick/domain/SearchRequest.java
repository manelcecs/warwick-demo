package com.warwick.domain;

import lombok.Data;

/**
 * @author ManPerGut
 * 
 * This is the Object will be send by post api calls
 * 
 * It has the key word and the text to search in. 
 * */
@Data
public class SearchRequest {

	//matching keyword
	private String keyWord;
	
	//input text
	private String text;
	
	
	//Method to split text by ' ' (space/gaps)
	public String[] splitByWhiteSpace() {
		return this.text.split("[\\s.,;]+");
	}
	
}
