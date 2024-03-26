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

    public static InlineKeyboardMarkup start() {
        return InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{" Отримати інфо", " Налаштування"});
    }
    public static InlineKeyboardMarkup banks(long chatId) {
        return InlineKeyboardMarkupBuilder.buildBanksMarkup(
                new String[]{
                        "ПриватБанк", "НБУ", "Монобанк"
                }, chatId);
    }

    public static InlineKeyboardMarkup currency(long chatId) {
        return InlineKeyboardMarkupBuilder.buildCurrencyMarkup(chatId);
    }

    public static InlineKeyboardMarkup quantityOfNumbers(long chatId) {
        return InlineKeyboardMarkupBuilder.buildQuantityOfNumbersMarkup(
                new String[]{"2", "3", "4"}, chatId);
    }
}