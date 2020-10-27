package school2107.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



/**
 * Класс-обработчик поступающих к боту сообщений.
 */
public class EchoBot extends TelegramLongPollingBot {

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
    @Override
    public void onUpdateReceived(Update update) {
        try {
            //проверяем есть ли сообщение и текстовое ли оно
            if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage msg=Handler.mainMenu();
            msg.setChatId(update.getMessage().getChatId());
            execute(msg);
            }
            if (update.hasCallbackQuery()&&update.getCallbackQuery().getId()!=null){
                SendMessage msg=Handler.router(update.getCallbackQuery());
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
