package school2107.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import school2107.bot.admin.AdminHandler;

import java.util.ArrayList;


/**
 * Класс-обработчик поступающих к боту сообщений.
 */
public class EchoBot extends TelegramLongPollingBot {

    //Список активных администраторов
    public static ArrayList<Long> adminsActive = new ArrayList<>();

    /**
     * Метод, который возвращает токен, выданный нам ботом @BotFather.
     *
     * @return токен
     */
    @Override
    public String getBotToken() {
        return "1268306799:AAFZyRy0wtPpUj0NlTcl0_5bHNmh9vpvlio";
    }

    /**
     * Отправка сообщение
     *
     * @param msg сообщение
     */
    public void sendMessage(SendMessage msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    /*
      Метод-обработчик поступающих сообщений.
      @param update объект, содержащий информацию о входящем сообщении
     */
    public void onUpdateReceived(Update update) {
        try {
            //Вызов главного меню
            if (update.hasMessage() && update.getMessage().hasText() && !update.getMessage().getText().equalsIgnoreCase(App.adminKey)&&!adminsActive.contains(update.getMessage().getChatId())) {
                SendMessage msg = Handler.mainMenu();
                msg.setChatId(update.getMessage().getChatId());
                execute(msg);
            }
            //Авторизация в админское меню
            if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equalsIgnoreCase(App.adminKey)) {
                SendMessage msg= AdminHandler.mainMenu();
                adminsActive.add(update.getMessage().getChatId());
                msg.setChatId(update.getMessage().getChatId());
                execute(msg);
            }
            //проверка на последовательность выхода
            if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equalsIgnoreCase("exit")) {
                SendMessage msg= Handler.mainMenu();
                adminsActive.remove(update.getMessage().getChatId());
                AdminHandler.reset();
                msg.setChatId(update.getMessage().getChatId());
                execute(msg);
            }
            //ввод сообщений при запросах
            if (update.hasMessage() && update.getMessage().hasText() && adminsActive.contains(update.getMessage().getChatId())){
                SendMessage msg=AdminHandler.msgRouter(update.getMessage().getText());
                msg.setChatId(update.getMessage().getChatId());
                if(msg.getText()==null){msg.setText(B.ERROR_AM);}
                execute(msg);
            }
            //Обработка нажатия кнопок обычного меню
            if (update.hasCallbackQuery()&&update.getCallbackQuery().getId()!=null&&!update.getCallbackQuery().getData().startsWith("a")){
                SendMessage msg=Handler.router(update.getCallbackQuery());
                Long chatId=update.getCallbackQuery().getMessage().getChatId();
                msg.setChatId(chatId);
                execute(msg);
            }
            //Обработка нажатий кнопок админского меню
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
