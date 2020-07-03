package com.warwick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warwick.domain.SearchRequest;
import com.warwick.domain.SearchResponse;
import com.warwick.service.SearchService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/search")
@Log4j2
public class SearchController {

	
	//SearchService import
	@Autowired
	private SearchService searchService;
	
	
	/**
	 * Endpoint to call searchKeyWordFrecuency
	 * 
	 * @param request SearchRequest
	 * 
	 * @return response SearchResponse
	 */
	@PostMapping("/split")
	public SearchResponse searchBySplitMethod(@RequestBody SearchRequest request) {
		SearchResponse response = null;
		try {
			response = this.searchService.searchKeyWordSplit(request);
		}catch(Exception e) {
			log.error("An exception happends when trying to invoque method {}, trace message: ", "SearchKeyWordFrecuency", e.getMessage());
		}
		
		return response;
	}
	
}
