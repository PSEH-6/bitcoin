package com.exercise.service.impl;

import com.exercise.service.BitcoinDataService;
import com.exercise.service.dto.*;
import com.exercise.service.util.DateUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BitcoinDataServiceImpl implements BitcoinDataService {

    private RestTemplate restTemplate;
    ObjectMapper objectMapper=new ObjectMapper();

    private String basePath="https://api.coindesk.com/v1/bpi/historical/close.json";

    public BitcoinDataServiceImpl(){
        restTemplate = new RestTemplateBuilder().build();
    }

    @Override
    public Map<Date,String> getHistoricalData(Date startDate, Date endDate, String currency) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("start",DateUtil.toBitcoinFormat(startDate));
        params.put("end",DateUtil.toBitcoinFormat(endDate));
        params.put("currency",currency);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(basePath);
        params.entrySet().forEach(e-> builder.queryParam(e.getKey(),e.getValue()));
        String uriBuilder = builder.build().encode().toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(uriBuilder, HttpMethod.GET,entity,String.class);

        try {
            JsonNode json = objectMapper.readTree(response.getBody());
            Iterator<String> fieldnames=json.get("bpi").fieldNames();
            Map<Date,String> result=new LinkedHashMap<>();
            while(fieldnames.hasNext()) {
                String next=fieldnames.next();
                result.put(DateUtil.fromBitcoinDate(next), json.get("bpi").get(next).toString());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot parse json "+response.getBody());
        }


    }

}
