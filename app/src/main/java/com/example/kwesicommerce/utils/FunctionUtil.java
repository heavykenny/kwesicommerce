package com.example.kwesicommerce.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FunctionUtil {
    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String generateOrderTracking() {
        // generate a random number between 100000 and 999999 and append it with "KWESI-"
        int random = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
        return "KWESI-" + random;
    }
}