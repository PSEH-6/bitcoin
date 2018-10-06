package com.exercise.controller.response;

import java.util.List;

public class SearchResult {

    private List<BitcoinStat> bitCoinStats;
    private String currency;


    public List<BitcoinStat> getBitCoinStats() {
        return bitCoinStats;
    }

    public void setBitCoinStats(List<BitcoinStat> bitCoinStats) {
        this.bitCoinStats = bitCoinStats;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public SearchResult(List<BitcoinStat> bitCoinStats, String currency) {
        this.bitCoinStats = bitCoinStats;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public static class BitcoinStat {

        private String date;
        private String price;

        public BitcoinStat(String date, String price) {
            this.date = date;
            this.price = price;
        }


        public void setDate(String date) {
            this.date = date;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDate() {
            return date;
        }

        public String getPrice() {
            return price;
        }

    }

}
