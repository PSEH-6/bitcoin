package com.exercise.service.impl;

import com.exercise.controller.response.SearchResult;
import com.exercise.service.BitcoinDataService;
import com.exercise.service.BitcoinSearchService;
import com.exercise.service.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BitcoinSearchServiceImpl implements BitcoinSearchService {

    @Autowired
    private BitcoinDataService bitcoinDataService;

    @Override
    public SearchResult search(String startDate, String endDate, String currency) {

        Map<Date, String> result = bitcoinDataService.getHistoricalData(DateUtil.fromUserDate(startDate), DateUtil.fromUserDate(endDate), currency);
        List<SearchResult.BitcoinStat> bitCoinStatList =
                result.entrySet().stream().map(e -> new SearchResult.BitcoinStat(DateUtil.toUserFormat(e.getKey()), e.getValue())).collect(Collectors.toList());
        tagHighest(bitCoinStatList);
        return new SearchResult(bitCoinStatList, currency);
    }

    private void tagHighest(List<SearchResult.BitcoinStat> bitCoinStatList) {
        SearchResult.BitcoinStat maxstat = null;
        double maxPrice = -1;
        for (SearchResult.BitcoinStat stat : bitCoinStatList)
            if (Double.parseDouble(stat.getPrice()) > maxPrice) {
                maxstat = stat;
                maxPrice = Double.parseDouble(stat.getPrice());
            }
        maxstat.setPrice(maxstat.getPrice() + " (highest)");
    }


}
