package com.example.demo;

import java.util.HashMap;

public class Courses {
    public String data;
    public String symbols;
    public HashMap <String, String> rates;
    public Boolean error;
    public String status;
    public String message;
    public String description;

    public Courses () {}

    public Courses (
            String data,
            String symbols,
            HashMap <String, String> rates,
            Boolean error,
            String status,
            String message,
            String description
    ) {
        super();
        this.data = data;
        this.symbols = symbols;
        this.error = error;
        this.status = status;
        this.message = message;
        this.description = description;
    }

    public Boolean IsError(){

        if ( this.error!=null && this.error){
            return true;

        }else{
            return false;
        }

    }

    public String GetErrorInfo(){

        if (this.IsError()){

            return String.format("\n%s\n%s\n%s\n",this.status,this.message,this.description);

        }

        return "";

    }

    public String GetRatesByCurrency(String currency){
        if (this.rates != null) {
            return this.rates.get(currency);
        }
        return "null";
    }

}
