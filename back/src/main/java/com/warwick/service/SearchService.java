package com.warwick.service;

import java.util.List;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.warwick.domain.SearchRequest;
import com.warwick.domain.SearchResponse;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author ManPerGut
 *
 */
@Service
@Log4j2
public class SearchService {

	/**
	 * This method uses the split version of search.
	 * 
	 * Use an String[] of words and the key word.
	 * 
	 * @param SearchRequest not null request object
	 * 
	 * @return SearchResponse response
	 * 
	 */
	public SearchResponse searchKeyWordSplit(@NonNull SearchRequest request) {
		
		log.info("Starting search method -- split");
		//Start instant of the method
		Instant startTime = Instant.now();
		
		//number of occurrences of the keyword
		int occurrences = 0;
	
		//Response object
		SearchResponse response = new SearchResponse();
		
		//String[] of the given text 
		String[] wordsInText = request.splitByWhiteSpace();
				
		//For each word in the given text
		//Words and key word case will be ignored.
		for(String word : wordsInText) {
			//if the word is the same as the key word
			if(word.equalsIgnoreCase(request.getKeyWord())) {
				//One more occurrence
				occurrences++;
			}
		}
		
		//Instant of time at the end of the search method
		Instant endTime = Instant.now();
		
		//Getting the amount of time
		Duration timeElapsed = Duration.between(startTime, endTime);
		
		log.info("Time elapsed: {} ns", timeElapsed.toNanos());
		
		//Setting the values to the response
		//Setting the occurrences
		response.setNumberOccurrences(occurrences);
		//Setting the elapsed time
		response.setTimeElapsed(timeElapsed.toMillis());
		
		log.info("Finish search method -- split");
		
		//Retrieving the response
		return response;
	}
	
}
