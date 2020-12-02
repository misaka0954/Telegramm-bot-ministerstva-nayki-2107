package school2107.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import school2107.bot.admin.AdminHandler;
import school2107.bot.admin.Task;

import java.util.ArrayList;


/**
 * Класс-обработчик поступающих к боту сообщений.
 */
public class EchoBot extends TelegramLongPollingBot {
    ArrayList<Task> tasksRM=new ArrayList<>();
    public void sendMessage(SendMessage msg){
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод, который возвращает токен, выданный нам ботом @BotFather.
     * @return токен
     */
    @Override
    public String getBotToken() {
        return "1268306799:AAFZyRy0wtPpUj0NlTcl0_5bHNmh9vpvlio";
    }

    /**
     * Метод-обработчик поступающих сообщений.
     * @param update объект, содержащий информацию о входящем сообщении
     */
    public static ArrayList<Long> adminsActive=new ArrayList<>();
    @Override
    public void onUpdateReceived(Update update) {
        try {

            //проверяем есть ли сообщение и текстовое ли оно
            if (update.hasMessage() && update.getMessage().hasText() && !update.getMessage().getText().equalsIgnoreCase("admin mod")&&!adminsActive.contains(update.getMessage().getChatId())) {
            SendMessage msg=Handler.mainMenu();
            msg.setChatId(update.getMessage().getChatId());
            execute(msg);
            }
            if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equalsIgnoreCase("admin mod")) {
                SendMessage msg= AdminHandler.mainMenu();
                adminsActive.add(update.getMessage().getChatId());
                msg.setChatId(update.getMessage().getChatId());
                execute(msg);
            }
            if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equalsIgnoreCase("exit")) {
                SendMessage msg= Handler.mainMenu();
                adminsActive.remove(update.getMessage().getChatId());
                msg.setChatId(update.getMessage().getChatId());
                execute(msg);
            }
            if (update.hasMessage() && update.getMessage().hasText() && adminsActive.contains(update.getMessage().getChatId())){
                SendMessage msg=AdminHandler.msgRouter(update.getMessage().getText());
                msg.setChatId(update.getMessage().getChatId());
                execute(msg);
            }
            if (update.hasCallbackQuery()&&update.getCallbackQuery().getId()!=null&&!update.getCallbackQuery().getData().startsWith("a")){
                SendMessage msg=Handler.router(update.getCallbackQuery());
                Long chatId=update.getCallbackQuery().getMessage().getChatId();
                msg.setChatId(chatId);
                execute(msg);
            }
            if (update.hasCallbackQuery()&&update.getCallbackQuery().getId()!=null&&update.getCallbackQuery().getData().startsWith("a")){
                SendMessage msg=AdminHandler.router(update.getCallbackQuery());
                Long chatId=update.getCallbackQuery().getMessage().getChatId();
                msg.setChatId(chatId);
                execute(msg);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    /**
     * Метод, который возвращает имя пользователя бота.
     * @return имя пользователя
     */
    @Override
    public String getBotUsername() {
        return "baka";
    }
}
