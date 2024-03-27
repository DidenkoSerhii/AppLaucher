package org.CurrencyExchangeRateBot;

import java.util.Timer;
import java.util.TimerTask;

public class DelayedMessageSender {
    public static void SendDelayedMessage(String execTime, long chatId) {
        int[] timeArray = convertTimeStringToInt(execTime);

        Timer timer = new Timer();
        timer.schedule(new SendMessageTask(chatId), getNextExecutionTime(timeArray[0], timeArray[1]));
    }

    public static int[] convertTimeStringToInt(String timeString) {
        String[] parts = timeString.split(":");

        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return new int[]{hours, minutes};
    }


    private static long getNextExecutionTime(int hour, int minute) {

        long currentTime = System.currentTimeMillis();
        long scheduledTime = getTimeToday(hour, minute);

        if (currentTime > scheduledTime) {
            scheduledTime += 24 * 60 * 60 * 1000; // add 24 hours in mills for next day
        }

        return scheduledTime;
    }

    private static long getTimeToday(int hour, int minute) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();

        calendar.set(java.util.Calendar.HOUR_OF_DAY, hour);
        calendar.set(java.util.Calendar.MINUTE, minute);
        calendar.set(java.util.Calendar.SECOND, 0);

        return calendar.getTimeInMillis();
    }

    static class SendMessageTask extends TimerTask {
        private final long chatId;
        public SendMessageTask(long chatId) {
            this.chatId=chatId;
            String message = "test";
            System.out.println(message);
            //todo botCommands.printCourse(chatId);
        }

        @Override
        public void run() {
            String message = "test" + chatId;
            System.out.println(message);
            //todo botCommands.printCourse(chatId);
        }
    }
}
