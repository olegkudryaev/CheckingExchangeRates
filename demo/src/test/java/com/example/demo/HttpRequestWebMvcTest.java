package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentScan
@WebMvcTest
public class HttpRequestWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public Config config;



    @MockBean
    public RateProxy proxy;

    @MockBean
    public GiphyProxy giphyProxy;

    @Test
    public void CheckServicesWithMock() throws Exception {
        Courses RubToday = new Courses();
        HashMap<String, String> rthm = new HashMap<String, String>();
        rthm.put("RUB", "74");
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

        Giphy gfb = new Giphy();
        GiphyResponse gr = new GiphyResponse();
        gr.image_url = "test complete";
        gfb.data = gr;
        when(giphyProxy.getGif(config.getGiphyApiKey(), "broke")).thenReturn(gfb);

        this.mockMvc
                .perform(get("/USD"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<img src='test complete'>")));
    }
}



