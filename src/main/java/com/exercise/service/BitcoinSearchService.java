package com.exercise.service;

import com.exercise.controller.response.SearchResult;
import org.springframework.stereotype.Service;

@Service
public interface BitcoinSearchService {

    SearchResult search(String startDate, String endDate, String currency) ;

}
