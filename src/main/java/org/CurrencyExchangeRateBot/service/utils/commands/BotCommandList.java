package org.CurrencyExchangeRateBot.service.utils.commands;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import java.util.ArrayList;
import java.util.List;

public class BotCommandList {

    public static List<BotCommand> getBotCommandList() {
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand("/start", "Запустити бота"));
        botCommandList.add(new BotCommand("/info", "Отримати інфо"));
        botCommandList.add(new BotCommand("/setting", "Налаштування"));
        return botCommandList;
    }
}
