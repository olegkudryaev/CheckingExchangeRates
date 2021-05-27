package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Autowired
    private RateProxy proxy;

    @Autowired
    private GiphyProxy proxy2;

    @Autowired
    public Config config;

    @RequestMapping("/")
    public @ResponseBody
    String greeting() {

        if ( config.getDefaultCurrency() != null && config.getDefaultCurrency().length()>0){
            RateService rs = new RateService();
            rs.proxy = proxy;
            rs.config = config;
            String result = rs.getRates(config.getDefaultCurrency());

            Giphy gif = proxy2.getGif(config.getGiphyApiKey(), result);

            return String.format("<img src='%s'>", gif.GetUrl());
        }

        return String.format("currency param is required");

    }

    @RequestMapping("/{currency}")
    public @ResponseBody
    String greetingWithParams(@PathVariable(value = "currency") String currency) {

        RateService rs = new RateService();
        rs.proxy = proxy;
        rs.config = config;
        String result = rs.getRates(currency);

        Giphy gif = proxy2.getGif(config.getGiphyApiKey(), result);

        return String.format("<img src='%s'>", gif.GetUrl());

    }

}
