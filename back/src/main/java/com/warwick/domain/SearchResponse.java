package com.warwick.domain;

import lombok.Data;

/**
 * 
 * @author ManPerGut
 *
 * Response of the api calls.
 * 
 * It retrieves the number of occurrences of a given keyword in a text.
 * 
 * Also, gives the amount of time elapsed in the search.
 */
@Data
public class SearchResponse {

	
	//Number of occurrences
	private Integer numberOccurrences;
	
	//Number of milliseconds elapsed in the search
	private Long timeElapsed;
	
}
