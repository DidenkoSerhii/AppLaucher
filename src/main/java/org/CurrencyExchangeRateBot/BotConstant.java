package org.CurrencyExchangeRateBot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BotConstant {
    public static final String BOT_NAME = "Currency_Changes_bot";
    public static String BOT_TOKEN;
    public static final String PRIVAT_API = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    public static final String MONO_API = "https://api.monobank.ua/bank/currency";
    public static final String NBU_API = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    public static void setBotToken() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/resources/BotToken.txt")));
        BOT_TOKEN = reader.readLine().strip();
        reader.close();
    }

}
