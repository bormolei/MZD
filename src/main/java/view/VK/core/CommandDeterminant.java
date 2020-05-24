package view.VK.core;

import com.vk.api.sdk.objects.messages.Message;
import model.Bot;
import model.WeatherParser;
import view.VK.core.commands.Unknown;

import java.util.Collection;

/**
 * Определяет команду
 *
 * @author Артур Куприянов
 * @version 1.1.0
 */
public class CommandDeterminant {


    public static Command getCommand(Collection<Command> commands, Message message) {
        String body = message.getBody();

        WeatherParser bot = new Bot();
         String test = bot.getReadyForecast(body);


        return new Unknown(test);
    }

}
