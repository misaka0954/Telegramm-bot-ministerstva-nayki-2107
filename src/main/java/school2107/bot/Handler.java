package school2107.bot;

import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;

public class Handler {

    public static SendMessage mainMenu() {
        SendMessage msg=new SendMessage();
        msg.setText("Главное меню");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Олимпиады").setCallbackData("1"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }
}
