package org.CurrencyExchangeRateBot.service.utils;

import org.CurrencyExchangeRateBot.service.utils.ui.UserModel;
import org.CurrencyExchangeRateBot.service.utils.ui.UserServices;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardMarkupBuilder {

    public static InlineKeyboardMarkup buildMarkup(String[] buttonTexts) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonText : buttonTexts) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonText);
            button.setCallbackData(buttonText.toUpperCase());
            keyboard.add(List.of(button));
        }

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);

        return markup;
    }
    public static InlineKeyboardMarkup buildBanksMarkup(String[] buttonTexts, long chatId) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonText : buttonTexts) {
            if (UserServices.getBankFromUserServices(chatId).equalsIgnoreCase(buttonText)) {
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText("✅ " + buttonText);
                button.setCallbackData(buttonText.toUpperCase());
                keyboard.add(List.of(button));
            } else {
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(buttonText);
                button.setCallbackData(buttonText.toUpperCase());
                keyboard.add(List.of(button));
            }
        }
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);

        return markup;
    }

    public static InlineKeyboardMarkup buildCurrencyMarkup(long chatId) {
        UserModel userModel = UserServices.getUserSettings(chatId);
        String[] currencies = {"USD", "EUR"};
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String currency : currencies) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            boolean isSelected = userModel.isCurrencySelected(currency);
            button.setText(currency + (isSelected ? " ✅" : ""));
            button.setCallbackData(currency);
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(button);
            keyboard.add(row);
        }

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);

        return markup;
    }

    public static InlineKeyboardMarkup buildQuantityOfNumbersMarkup(String[] buttonTexts, long chatId) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonText : buttonTexts) {
            InlineKeyboardButton button = new InlineKeyboardButton();

            if (Integer.parseInt(buttonText) == UserServices.getUserSettings(chatId).getNumber()) {
                button.setText(buttonText + "✅");
            } else {
                button.setText(buttonText);
            }

            button.setCallbackData(buttonText.toUpperCase());
            keyboard.add(List.of(button));
        }

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);

        return markup;
    }
    public static InlineKeyboardMarkup buildTimeOfNotificationMarkup(String[] buttonTexts, long chatId) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonText : buttonTexts) {
            InlineKeyboardButton button = new InlineKeyboardButton();

            if (buttonText.equals(UserServices.getUserSettings(chatId).getTime()))  {
                button.setText(buttonText + "✅");
            } else {
                button.setText(buttonText);
            }

            button.setCallbackData(buttonText.toUpperCase());
            keyboard.add(List.of(button));
        }

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);

        return markup;
    }
}
