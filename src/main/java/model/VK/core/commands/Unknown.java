package model.VK.core.commands;

import com.vk.api.sdk.objects.messages.Message;
import model.VK.core.Command;
import model.VK.vk.VKManager;

public class Unknown extends Command {

    public Unknown(String name) {
        super(name);
    }

    @Override
    public void exec(Message message) {
        new VKManager().sendMessage(name, message.getUserId());
    }
}
