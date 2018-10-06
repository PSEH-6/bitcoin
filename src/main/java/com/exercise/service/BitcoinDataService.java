package com.exercise.service;

import com.exercise.service.dto.BitcoinHistory;

import java.util.Date;
import java.util.Map;

public interface BitcoinDataService {

    Map<Date,String> getHistoricalData(Date startDate, Date endDate, String currency);
}
