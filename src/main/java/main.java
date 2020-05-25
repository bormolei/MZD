import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import model.Telegram.TelegaMain;
import model.VK.vk.VKServer;

public class main {
    static TelegaMain telegaMain;
    static VKServer vkServer;

    public static void main(String[] args) throws TelegramApiRequestException {
        vkServer = new VKServer();
        telegaMain = new TelegaMain();
        Thread myThread = new Thread(telegaMain);
        Thread vkThread = new Thread(vkServer);
        myThread.start();
        vkThread.start();
//        System.out.println("Игра закончена");
    }
}
