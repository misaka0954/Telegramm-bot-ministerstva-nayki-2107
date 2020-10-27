package school2107.bot;

import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;

public class Handler {
public static List<List<InlineKeyboardButton>> buttonsMenu =new ArrayList<>();
    public static SendMessage mainMenu() {
        SendMessage msg=new SendMessage();
        msg.setText("Главное меню");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<InlineKeyboardButton> line =new ArrayList<>();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Олимпиады").setCallbackData("1"));
        buttons.add(line);
        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("ОГЭ").setCallbackData("2"));
        buttons.add(line);
        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("ЕГЭ").setCallbackData("3"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        buttonsMenu=buttons;
        return msg;
    }

    public static SendMessage router(CallbackQuery cbq) {
        String id= cbq.getData();
        switch(id){
            case "1":
                return olimpiads();
        }
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        kbd.setKeyboard(buttonsMenu);
        return new SendMessage().setText("Эта функция в разработке. Выберите пункт из меню:").setReplyMarkup(kbd);
    }

    private static SendMessage olimpiads() {
        SendMessage msg=new SendMessage();
        msg.setText("Меню олимпиад");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Подписаться на рассылку").setCallbackData("11"));
        buttons.add(line);
        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Отписаться от расссылки").setCallbackData("12"));
        buttons.add(line);
        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Близжайшая олимпиада").setCallbackData("13"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }
}
