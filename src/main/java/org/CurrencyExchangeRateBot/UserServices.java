package org.CurrencyExchangeRateBot;

import java.util.HashMap;
import java.util.Map;

public class UserServices {

    private static final Map<Long, UserModel> USERS_SETTINGS = new HashMap<>();

    public static UserModel getUserSettingsById(long chatId) {
        return USERS_SETTINGS.get(chatId);
    }

    public static boolean isUserSettingsExists(long chatId) {
        return USERS_SETTINGS.containsKey(chatId);
    }

    public static void createUserSettings(long chatId) {
        if (!isUserSettingsExists(chatId)) {
            UserModel userModel = new UserModel();
            USERS_SETTINGS.put(chatId, userModel);
        }
    }

    public static String toNumberFormat(long chatId) {
        return null;
    }
}