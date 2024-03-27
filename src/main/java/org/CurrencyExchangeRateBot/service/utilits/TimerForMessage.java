package org.CurrencyExchangeRateBot.service.utilits;

import org.CurrencyExchangeRateBot.service.utilits.commands.BotCommands;
import org.CurrencyExchangeRateBot.service.utilits.ui.UserModel;
import org.CurrencyExchangeRateBot.service.utilits.ui.UserServices;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class TimerForMessage {
    private static final Timer timer = new Timer();
    private static final MyTimerTask myTimerTask = new MyTimerTask();
    private static long chatId;
    private static BotCommands command;
    private static String date;

    public static void startTimer(String time, long chatIdCallback, BotCommands botCommands){
        LocalTime now = LocalTime.now();
        chatId = chatIdCallback;
        command = botCommands;
        date = time;
        long delay = (Integer.parseInt(time.substring(0, time.indexOf(':'))) - now.getHour()) * 60L + (53 - now.getMinute()) * 60 * 1000 - now.getSecond() * 1000 - now.getNano() / 1000000;
        if (delay < 0) {
            delay += 24 * 60 * 60 * 1000;
        }

        timer.schedule(myTimerTask, delay, 24 * 60 * 60 * 1000);
    }

    static class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            UserModel user = UserServices.getUserSettings(chatId);
            try {
                MessageBuilder.sendMessage(chatId, command.getCourse(command.getCurrencies(user), user));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TimerForMessage.startTimer(date, chatId, command);
        }
    }

    public static void stopTimer() {
        timer.cancel();
    }
}
