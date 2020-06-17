package model.Telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.Bot;
import service.WeatherParser;

import java.util.ArrayList;
import java.util.List;

public class BotTelegram extends TelegramLongPollingBot {

    ArrayList<Boolean> flags = new ArrayList<>(); //1 - погода; 2 -тест2; 3-тест3

    {
        flags.add(false);
        flags.add(false);
        flags.add(false);
    }

    private WeatherParser weatherParser = new Bot();

    /**
     * Метод для приема сообщений.
     *
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            sendMsg(message);
//            switch (message.getText()) {
//                case "/help":
//                    sendMsg(message, "Выберете вариант");
//                    break;
//            }
        }
    }


    public void sendMsg(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());

        //Исправить обработку выбора режима
        if(!flags.get(1)){
            sendMessage.setText("Выберите режим");
        }

        if (flags.get(1)) {
            sendMessage.setText(weatherParser.getReadyForecast(message.getText()));
        }

        try {
            setButtons(sendMessage);
            if (!check()) {
                switch (message.getText()) {
                    case "Погода":
                        sendMessage.setText("Введите название города.Например: \"Москва\" или \"Moscow\"");
                        flags.set(1, true);
                        break;
                    case "Тест2":
                        sendMessage.setText("Тест2");
                        flags.set(2, true);
                        break;
                    case "Тест3":
                        sendMessage.setText("Тест3");
                        flags.set(3, true);
                        break;
                }
            }


            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private boolean check() {
        for (Boolean b : flags) {
            if (b.equals(true)) {
                return true;
            }
        }
        return false;
    }

    /**
     *Клавиатура для телеграм
     */
    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Погода"));

        keyboardRows.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

//        keyboardFirstRow.add(new KeyboardButton("Тест2")); ДОРАБОТАТЬ КЛАВУ
//        keyboardFirstRow.add(new KeyboardButton("Тест3"));
    /**
     * Метод возвращает имя бота, указанное при регистрации.
     *
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return "WeatherBotKK";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     *
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        return "765215433:AAEN9q1sxI6e4OWoDTxwzUFjBYoUN0xReqc";
    }
}
