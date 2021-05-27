package com.example.demo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Configurable

public class RateService {

    public  Config config;

    public RateProxy proxy;

    public Double getTodayRate(String currency) {
        Courses latestResponse = proxy.getLatestRates(config.getOpenExchangesRatesApiKey(), currency);

        if (latestResponse.IsError()) {

            System.out.println("Error latestResponse!");
            System.out.println(latestResponse.GetErrorInfo());

            return null;

        }
        return Double.parseDouble(latestResponse.GetRatesByCurrency(currency));

    }

    public Double getYesterdayRate(String currency) {
        LocalDate yesterday = LocalDate.now().minusDays(1L);

        Courses response = proxy.getHistoricalRates(config.getOpenExchangesRatesApiKey(), yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), currency);

        if (response.IsError()) {

            System.out.println("Error response!");
            System.out.println(response.GetErrorInfo());

            return null;

        }
        return Double.parseDouble(response.GetRatesByCurrency(currency));
    }

    public String getRates(String currency) {
        double RubToUSDToday = getTodayRate("RUB");
        double CurrToUSDToday = getTodayRate(currency);
        double RubToUSDYesterday = getYesterdayRate("RUB");
        double CurrToUSDYesterday = getYesterdayRate(currency);

        Double RubToCurrToday = RubToUSDToday / CurrToUSDToday;
        Double RubToCurrYesterday = RubToUSDYesterday / CurrToUSDYesterday;

        String result = "";

        if (RubToCurrToday.equals(RubToCurrYesterday)) {
            result = "rich";
        } else {

            if (RubToCurrToday < RubToCurrYesterday) {
                result = "rich";
            } else {
                result = "broke";
            }

        }
        return result;
    }

}






