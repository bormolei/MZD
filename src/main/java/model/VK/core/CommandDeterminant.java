package model.VK.core;

import com.vk.api.sdk.objects.messages.Message;
import service.Bot;
import service.WeatherParser;
import model.VK.core.commands.Unknown;

import java.util.Collection;

/**
 * Определяет команду
 */
public class CommandDeterminant {


    public static Command getCommand(Collection<Command> commands, Message message) {
        WeatherParser bot = new Bot();
        String test = bot.getReadyForecast(message.getBody());

        return new Unknown(test);
    }

}
