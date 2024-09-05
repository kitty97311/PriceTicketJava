package main.java.service;

import com.google.gson.Gson;
import main.java.model.entity.symbol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PriceSymbol {
    public static symbol priceSym() {
        String urlString = "https://fapi.binance.com/fapi/v1/ticker/price?symbol=BTCUSDT";
        symbol data = null;
        try {
            URL url = new URL(urlString);
            URLConnection connectionSymbol = url.openConnection();
            BufferedReader readerSymbol = new BufferedReader(new InputStreamReader(connectionSymbol.getInputStream()));
            data = new Gson().fromJson(readerSymbol, symbol.class);
            readerSymbol.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
