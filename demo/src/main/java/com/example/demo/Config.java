package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "custom")
@Component
public class Config {
    private String giphyApiKey;
    private String openExchangesRatesApiKey;
    private String defaultCurrency;

    public String getGiphyApiKey() {
        return giphyApiKey;
    }

    public String getOpenExchangesRatesApiKey() {
        return openExchangesRatesApiKey;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setGiphyApiKey(String giphyApiKey) {
        this.giphyApiKey = giphyApiKey;
    }

    public void setOpenExchangesRatesApiKey(String openExchangesRatesApiKey) {
        this.openExchangesRatesApiKey = openExchangesRatesApiKey;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }
}

