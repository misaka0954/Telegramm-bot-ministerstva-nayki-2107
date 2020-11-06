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
        switch(id){
            case "0":
                InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
                kbd.setKeyboard(buttonsMenu);
                return new SendMessage().setText("Главное меню:").setReplyMarkup(kbd);
            case "1":
                return olimpiads();
            case "11":
            case "12":
                return classChooser(cbq);
            case "2":
                return oge();
            case "3":
                return ege();
            case "21":
            case "22":
            case "31":
            case "32":
                return subjectChooseOgeEge(cbq);
            case "4":
                return schSources();
            case "5":
                return infRes();

        }
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        kbd.setKeyboard(buttonsMenu);
        return new SendMessage().setText("Эта функция в разработке. Выберите пункт из меню:").setReplyMarkup(kbd);
    }

    private static SendMessage schSources() {
        SendMessage msg=new SendMessage();
        msg.setText("Ресурсы школы");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Сайт школы").setUrl("https://sch2107.mskobr.ru/"));
        buttons.add(line);
        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Ютуб школы").setUrl("https://www.youtube.com/channel/UCGzMXQcu9o7L1Hb_kxscw2g"));
        buttons.add(line);
        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Инстаграмм старшей школы").setUrl("https://www.instagram.com/2107school/"));
        buttons.add(line);

        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Назад в меню").setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    private static SendMessage infRes() {
        SendMessage msg=new SendMessage();
        msg.setText("Ссылки на образовательные порталы");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Сайт 1").setUrl("https://google.com/"));
        buttons.add(line);
        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Сайт 2").setUrl("https://google.com/"));
        buttons.add(line);
        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Сайт 3").setUrl("https://google.com/"));
        buttons.add(line);

        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Назад в меню").setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    private static SendMessage ege() {
        SendMessage msg=new SendMessage();
        msg.setText("Меню ЕГЭ");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Подписаться на рассылку").setCallbackData("31"));
        buttons.add(line);
        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Отписаться от расссылки").setCallbackData("32"));
        buttons.add(line);

        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Назад в меню").setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    private static SendMessage oge() {
        SendMessage msg=new SendMessage();
        msg.setText("Меню ОГЭ");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Подписаться на рассылку").setCallbackData("21"));
        buttons.add(line);
        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Отписаться от расссылки").setCallbackData("22"));
        buttons.add(line);

        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Назад в меню").setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
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

        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Назад в меню").setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }
    public static SendMessage classChooser(CallbackQuery query){
        SendMessage msg=new SendMessage();
        msg.setText("Выберите ваш класс");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        int i=5;
        while(i<12){
            line.add(new InlineKeyboardButton().setText(""+i).setCallbackData(query.getData()+i));
            buttons.add(line);
            line =new ArrayList<>();
            i++;
        }
        line.add(new InlineKeyboardButton().setText("Назад в меню").setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }
    public static SendMessage subjectChooseOgeEge(CallbackQuery query){
        SendMessage msg=new SendMessage();
        msg.setText("Выберите предмет");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        //деление математики по уровню сложности
        if(query.getData().startsWith("2")){
            line.add(new InlineKeyboardButton().setText("Математика").setCallbackData(query.getData() + "math"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        else{
            line.add(new InlineKeyboardButton().setText("Математика (Базовая)").setCallbackData(query.getData() + "mathb"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("Математика (Профильная)").setCallbackData(query.getData() + "mathp"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        if(true) {

            line.add(new InlineKeyboardButton().setText("Русский язык").setCallbackData(query.getData() + "rus"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("Литература").setCallbackData(query.getData() + "lit"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("Физика").setCallbackData(query.getData() + "fiz"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("Химия").setCallbackData(query.getData() + "him"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("Биология").setCallbackData(query.getData() + "bio"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("География").setCallbackData(query.getData() + "geo"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("История").setCallbackData(query.getData() + "ist"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("Обществознание").setCallbackData(query.getData() + "obs"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("Информатика и ИКТ").setCallbackData(query.getData() + "ikt"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("Английский язык").setCallbackData(query.getData() + "eng"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("Немецкий язык").setCallbackData(query.getData() + "nem"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("Французский язык").setCallbackData(query.getData() + "fra"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("Испанский язык").setCallbackData(query.getData() + "isp"));
            buttons.add(line);
            line = new ArrayList<>();
            if(query.getData().startsWith("3")){
            line.add(new InlineKeyboardButton().setText("Китайский язык").setCallbackData(query.getData() + "kit"));
            buttons.add(line);
            line = new ArrayList<>();}

        }

        line.add(new InlineKeyboardButton().setText("Назад в меню").setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }
}
