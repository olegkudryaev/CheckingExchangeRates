package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "111-client", url = "https://openexchangerates.org")

public interface RateProxy {
    @GetMapping("/api/historical/{start}.json?symbols={symbols}&app_id={appId}")
    public Courses getHistoricalRates
            (
                    @PathVariable("appId") String appId,
                    @PathVariable("start") String start,
                    @PathVariable("symbols") String symbols
            );

    @GetMapping("/api/latest.json?symbols={symbols}&app_id={appId}")
    public Courses getLatestRates
            (
                    @PathVariable("appId") String appId,
                    @PathVariable("symbols") String symbols
            );
}
