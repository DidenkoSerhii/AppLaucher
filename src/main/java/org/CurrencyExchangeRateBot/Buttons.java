package org.CurrencyExchangeRateBot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;




public class Buttons {
    public static InlineKeyboardMarkup setting() {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        " Банк", " Валюта", " Час сповіщень",
                        " Кількість знаків після коми"
                });
    }

    public static InlineKeyboardMarkup info() {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{" Налаштування"});
    }

    public static InlineKeyboardMarkup start() {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{" Отримати інфо", " Налаштування"});
    }
}