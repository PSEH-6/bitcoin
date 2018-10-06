package com.exercise.controller;

import com.exercise.controller.response.SearchResult;
import com.exercise.service.BitcoinSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class SearchController {

    @Autowired
    private BitcoinSearchService bitcoinSearchService;

    @RequestMapping("/healthCheck")
    public String healthCheck() {
        return "Service is Up";
    }

    @RequestMapping("/get")
    public SearchResult search(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("currency") String currency) {
        return bitcoinSearchService.search(startDate,endDate,currency);
    }

}
