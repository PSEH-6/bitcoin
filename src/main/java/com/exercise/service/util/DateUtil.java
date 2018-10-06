package com.exercise.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final String USER_FORMAT = "dd-MM-yyyy";
    private static SimpleDateFormat userDateFormat = new SimpleDateFormat(USER_FORMAT);

    private static final String BITCOIN_FORMAT = "yyyy-MM-dd";
    private static SimpleDateFormat bitcoinDateFormat = new SimpleDateFormat(BITCOIN_FORMAT);

    public static Date fromUserDate(String date) {
        try {
            return userDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(new StringBuilder("Date:").append(date).append(" is not in format of ").append(USER_FORMAT).toString());
        }
    }

    public static String toUserFormat(Date date) {
        return userDateFormat.format(date);
    }

    public static Date fromBitcoinDate(String date) {
        try {
            return bitcoinDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(new StringBuilder("Date:").append(date).append(" is not in format of ").append(BITCOIN_FORMAT).toString());
        }
    }

    public static String toBitcoinFormat(Date date) {
        return bitcoinDateFormat.format(date);
    }
}
