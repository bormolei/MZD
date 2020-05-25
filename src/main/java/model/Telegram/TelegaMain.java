package model.Telegram;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

//переименовать на нормальный
/**
* Класс для запуска работы бота в Телеграмм
 * */
public class TelegaMain implements Runnable {
    @Override
    public void run() {

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new BotTelegram());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
