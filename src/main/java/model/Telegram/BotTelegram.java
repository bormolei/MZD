package model.Telegram;

import service.Bot;
import service.WeatherParser;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.MessageContext;

import static org.telegram.abilitybots.api.objects.Flag.TEXT;
import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

/**
 * Класс-обработчик поступающих к боту сообщений.
 */
public class BotTelegram extends AbilityBot {
    private static final String BOT_TOKEN = "765215433:AAEN9q1sxI6e4OWoDTxwzUFjBYoUN0xReqc";
    private static final String BOT_NAME = "BotFindJob";
    private WeatherParser weatherParser = new Bot();

    public BotTelegram() {
        super(BOT_TOKEN, BOT_NAME);
    }

    @Override
    public int creatorId() {
        return -1;
    }

    public Ability startCommand() {
        return Ability
                .builder()
                .name("start")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> silent.send("Привет! Введи город в чат и получи предсказание на 5 дней! " +
                        "Например: \"Нью Йорк\" или \"Москва\"", ctx.chatId()))
                .build();
    }

    public Ability sendWeather() {
        return Ability.builder()
                .name(DEFAULT)
                .flag(TEXT)
                .privacy(PUBLIC)
                .locality(ALL)
                .input(0)
                .action((MessageContext ctx) -> {
                    if (ctx.firstArg().equals(ctx.secondArg())) {
                        silent.send(weatherParser.getReadyForecast(ctx.firstArg()), ctx.chatId());
                    } else {
                        silent.send(weatherParser.getReadyForecast(String.format("%s %s", ctx.firstArg(), ctx.secondArg())), ctx.chatId());
                    }
                })
                .build();
    }

}
