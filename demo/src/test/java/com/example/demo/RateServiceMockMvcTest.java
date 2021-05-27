package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class RateServiceMockMvcTest {
    @MockBean
    public RateProxy proxy;
    @Autowired
    private RateService rs;
    @Autowired
    public Config config;

    @Test
    public void checkIfRatesGrow() throws Exception {
        Courses RubToday = new Courses();
        HashMap<String, String> rthm = new HashMap<String, String>();
        rthm.put("RUB", "72");
        RubToday.rates = rthm;
        RubToday.error = false;
        when(proxy.getLatestRates(config.getOpenExchangesRatesApiKey(), "RUB")).thenReturn(RubToday);

        Courses UsdToday = new Courses();
        HashMap<String, String> uthm = new HashMap<String, String>();
        uthm.put("USD", "1");
        UsdToday.rates = uthm;
        UsdToday.error = false;
        when(proxy.getLatestRates(config.getOpenExchangesRatesApiKey(), "USD")).thenReturn(UsdToday);

        LocalDate yesterday = LocalDate.now().minusDays(1L);

        Courses RubYesterday = new Courses();
        HashMap<String, String> rythm = new HashMap<String, String>();
        rythm.put("RUB", "73");
        RubYesterday.rates = rythm;
        RubYesterday.error = false;
        when(proxy.getHistoricalRates(config.getOpenExchangesRatesApiKey(), yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "RUB")).thenReturn(RubYesterday);

        Courses UsdYesterday = new Courses();
        HashMap<String, String> uythm = new HashMap<String, String>();
        uythm.put("USD", "1");
        UsdYesterday.rates = uythm;
        UsdYesterday.error = false;
        when(proxy.getHistoricalRates(config.getOpenExchangesRatesApiKey(), yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "USD")).thenReturn(UsdYesterday);

        rs.proxy = proxy;
        rs.config = config;

        assertThat(rs.getRates("USD")).contains("rich");
    }

    @Test
    public void checkIfRatesShrink() throws Exception {
        Courses RubToday = new Courses();
        HashMap<String, String> rthm = new HashMap<String, String>();
        rthm.put("RUB", "75");
        RubToday.rates = rthm;
        RubToday.error = false;
        when(proxy.getLatestRates(config.getOpenExchangesRatesApiKey(), "RUB")).thenReturn(RubToday);

        Courses UsdToday = new Courses();
        HashMap<String, String> uthm = new HashMap<String, String>();
        uthm.put("USD", "1");
        UsdToday.rates = uthm;
        UsdToday.error = false;
        when(proxy.getLatestRates(config.getOpenExchangesRatesApiKey(), "USD")).thenReturn(UsdToday);

        LocalDate yesterday = LocalDate.now().minusDays(1L);

        Courses RubYesterday = new Courses();
        HashMap<String, String> rythm = new HashMap<String, String>();
        rythm.put("RUB", "73");
        RubYesterday.rates = rythm;
        RubYesterday.error = false;
        when(proxy.getHistoricalRates(config.getOpenExchangesRatesApiKey(), yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "RUB")).thenReturn(RubYesterday);

        Courses UsdYesterday = new Courses();
        HashMap<String, String> uythm = new HashMap<String, String>();
        uythm.put("USD", "1");
        UsdYesterday.rates = uythm;
        UsdYesterday.error = false;
        when(proxy.getHistoricalRates(config.getOpenExchangesRatesApiKey(), yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "USD")).thenReturn(UsdYesterday);

        rs.proxy = proxy;
        rs.config = config;

        assertThat(rs.getRates("USD")).contains("broke");
    }

}
