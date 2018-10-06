package com.sapient.exercise.controller;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.exercise.controller.response.SearchResult;
import com.exercise.service.BitcoinDataService;
import com.exercise.service.BitcoinSearchService;
import com.exercise.service.impl.BitcoinSearchServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;


public class SearchServiceTest {


    private BitcoinSearchService bitcoinSearchService;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        BitcoinDataService bitcoinDataService = mock(BitcoinDataService.class);
        Map<Date, String> map = new HashMap<Date, String>();
        map.put(formatter.parse("01-01-2018"), "233.2");
        map.put(formatter.parse("02-01-2018"), "231.2");
        when(bitcoinDataService.getHistoricalData(formatter.parse("01-01-2018"), formatter.parse("02-01-2018"), "INR")).thenReturn(map);
        this.bitcoinSearchService = new BitcoinSearchServiceImpl(bitcoinDataService);
    }

    @Test
    public void test() throws Exception {
        SearchResult result = bitcoinSearchService.search("01-01-2018", "02-01-2018", "INR");
        Assert.assertEquals(2, result.getBitCoinStats().size());
        Assert.assertEquals("INR", result.getCurrency());
        for (SearchResult.BitcoinStat stat : result.getBitCoinStats()) {
            if (stat.getDate().equals(formatter.parse("01-01-2018"))) {
                Assert.assertEquals("233.2 (highest)", stat.getPrice());
            }
        }
    }
}