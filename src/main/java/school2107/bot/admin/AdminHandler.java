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
            line =new ArrayList<>();
            line.add(new InlineKeyboardButton().setText("Ресурсы школы").setCallbackData("4"));
            buttons.add(line);
            line =new ArrayList<>();
            line.add(new InlineKeyboardButton().setText("Полезные ссылки").setCallbackData("5"));
            buttons.add(line);
            kbd.setKeyboard(buttons);
            msg.setReplyMarkup(kbd);
            buttonsMenu=buttons;
            return msg;
        }

        public static SendMessage router(CallbackQuery cbq) {
            String id= cbq.getData();
           // if((id.startsWith("21")||id.startsWith("31"))&&id.length()>2){return subjectPushOgeEge(cbq);}
            switch(id){
                case "0":
                    InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
                    kbd.setKeyboard(buttonsMenu);
                    return new SendMessage().setText("Главное меню:").setReplyMarkup(kbd);
                case "1":
                    //return olimpiads();
                case "11":
                case "12":
                   // return classChooser(cbq);
                case "2":
                  //  return oge();
                case "3":
                  //  return ege();
                case "21":
                case "22":
                case "31":
                case "32":
                  //  return subjectChooseOgeEge(cbq);
                case "4":
                   // return schSources();
                case "5":
                  //  return infRes();

            }
            InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
            kbd.setKeyboard(buttonsMenu);
            return new SendMessage().setText("Эта функция в разработке. Выберите пункт из меню:").setReplyMarkup(kbd);
        }

    }
