package school2107.bot.admin;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

    public class AdminHandler {
        public static List<List<InlineKeyboardButton>> buttonsMenu =new ArrayList<>();
        public static SendMessage mainMenu() {
            SendMessage msg=new SendMessage();
            msg.setText("Админское меню");
            InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
            List<InlineKeyboardButton> line =new ArrayList<>();
            List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
            line.add(new InlineKeyboardButton().setText("Создать рассылку").setCallbackData("a1"));
            buttons.add(line);
            line =new ArrayList<>();


            kbd.setKeyboard(buttons);
            msg.setReplyMarkup(kbd);
            buttonsMenu=buttons;
            return msg;
        }

        public static SendMessage router(CallbackQuery cbq) {
            String id= cbq.getData();
            switch(id){
                case "a0":
                    InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
                    kbd.setKeyboard(buttonsMenu);
                    return new SendMessage().setText("Главное меню:").setReplyMarkup(kbd);
                case "a1":
                    //return olimpiads();
            }
            InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
            kbd.setKeyboard(buttonsMenu);
            return new SendMessage().setText("Эта функция в разработке. Выберите пункт из меню:").setReplyMarkup(kbd);
        }

    }
