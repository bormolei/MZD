package model.VK.vk;

import com.vk.api.sdk.objects.messages.Message;
import model.VK.core.Commander;

public class Messenger implements Runnable{

    private Message message;

    public Messenger(Message message){
        this.message = message;
    }

    @Override
    public void run() {
        Commander.execute(message);
    }

}
