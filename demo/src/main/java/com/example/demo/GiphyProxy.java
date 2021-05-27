package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "giphy-client", url = "https://api.giphy.com")
public interface GiphyProxy {


    @GetMapping("/v1/gifs/random?api_key={appId}&tag={tag}&rating=g")
    public Giphy getGif
            (
                    @PathVariable("appId") String appId,
                    @PathVariable("tag") String start
            );

}