package view.VK.core.commands;

import com.vk.api.sdk.objects.messages.Message;
import view.VK.core.Command;
import view.VK.vk.VKManager;

/**
 * @author Arthur Kupriyanov
 */
public class Unknown extends Command {

    public Unknown(String name) {
        super(name);
    }

    @Override
    public void exec(Message message) {
        new VKManager().sendMessage("Неизвестная команда", message.getUserId());
    }
}
