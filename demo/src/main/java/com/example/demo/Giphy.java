package com.example.demo;

import java.util.HashMap;

class GiphyResponse {

    public String image_url;

}

public class Giphy {
    public GiphyResponse data;

    public Giphy() {
    }

    public Giphy(GiphyResponse data) {
        super();
        this.data = data;
    }

    public String GetUrl(){
        if (this.data != null) {
            return this.data.image_url;
        }
        return "null";
    }

}
