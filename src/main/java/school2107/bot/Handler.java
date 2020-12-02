package school2107.bot;

import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;

//TODO изменить убирание подписок
public class Handler {
    public static ArrayList<SubjectsFO> olimpiadsSC=new ArrayList<>();

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
        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Дайджест").setCallbackData("6"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        buttonsMenu=buttons;
        return msg;
    }

    public static SendMessage router(CallbackQuery cbq) {
        String id= cbq.getData();
        if((id.startsWith("21")||id.startsWith("31"))&&id.length()>2){return subjectPushOgeEge(cbq);}
        if((id.startsWith("22")||id.startsWith("32"))&&id.length()>2){return subjectRemoveOgeEge(cbq);}
        if((id.startsWith("11")||id.startsWith("12"))&&id.length()>2){return sbtChooser(cbq);}
        if((id.startsWith("11")||id.startsWith("12"))&&id.length()>3){return PRolimpiada(cbq);}
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
            case "6":
                return ddt();
            case "61":
                return pRddt(cbq,1);
            case "62":
                return pRddt(cbq,0);

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

    private static SendMessage ddt() {
        SendMessage msg=new SendMessage();
        msg.setText("Меню Дайджеста");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Подписаться на рассылку").setCallbackData("61"));
        buttons.add(line);
        line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Отписаться от расссылки").setCallbackData("62"));
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
        line.add(new InlineKeyboardButton().setText("Назад в меню").setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    public static SendMessage classChooser(CallbackQuery query){
        SendMessage msg=new SendMessage();
        msg.setText("Выберите вашу категорию:");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
            line.add(new InlineKeyboardButton().setText("4-6").setCallbackData(query.getData()+1));
            buttons.add(line);
            line =new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("7-9").setCallbackData(query.getData()+2));
            buttons.add(line);
            line =new ArrayList<>();

        line.add(new InlineKeyboardButton().setText("10-11").setCallbackData(query.getData()+3));
        buttons.add(line);
        line =new ArrayList<>();


        line.add(new InlineKeyboardButton().setText("Назад в меню").setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    public static SendMessage sbtChooser(CallbackQuery query){
        SendMessage msg=new SendMessage();
        msg.setText("Выберите предмет");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        int i=Integer.parseInt(query.getData().substring(2));
        for(SubjectsFO o:olimpiadsSC){
            if(o.checkLvl(i)){
                line.add(new InlineKeyboardButton().setText(o.sbt).setCallbackData(query.getData()+o.sKey));
                buttons.add(line);
                line =new ArrayList<>();
            }
        }
        line.add(new InlineKeyboardButton().setText("Назад в меню").setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    public static SendMessage subjectPushOgeEge(CallbackQuery query){
        int type=0;
        if(query.getData().startsWith("2")){type=2;}
        if(query.getData().startsWith("3")){type=3;}
        App.realiser.addMemberOE(query.getMessage().getChatId(),query.getData().substring(2),type);
        SendMessage msg=new SendMessage();
        msg.setText("Успешно");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Назад в меню").setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    public static SendMessage pRddt(CallbackQuery query,int a){
        if(a==1) {
            App.realiser.addMemberDdst(query.getMessage().getChatId());
        }else{
            App.realiser.rmMemberDdst(query.getMessage().getChatId());
        }
        SendMessage msg=new SendMessage();
        msg.setText("Успешно");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Назад в меню").setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    public static SendMessage subjectRemoveOgeEge(CallbackQuery query){
        int type=0;
        if(query.getData().startsWith("2")){type=2;}
        if(query.getData().startsWith("3")){type=3;}
        App.realiser.rmMemberOE(query.getMessage().getChatId(),query.getData().substring(2),type);
        SendMessage msg=new SendMessage();
        msg.setText("Успешно");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
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
            line.add(new InlineKeyboardButton().setText("Математика").setCallbackData(query.getData() + "mat"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        else{
            line.add(new InlineKeyboardButton().setText("Математика (Базовая)").setCallbackData(query.getData() + "mab"));
            buttons.add(line);
            line = new ArrayList<>();

            line.add(new InlineKeyboardButton().setText("Математика (Профильная)").setCallbackData(query.getData() + "map"));
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

    public static SendMessage PRolimpiada(CallbackQuery query){
        int type=0;
        if(query.getData().startsWith("11")){type=1;}
        if(query.getData().startsWith("12")){type=2;}
        if(type==1) {
            App.realiser.addMemberOlimpiada(query.getMessage().getChatId(), query.getData().substring(2,3),query.getData().substring(3));
        }
        if(type==2) {
            App.realiser.rmMemberOlimpiada(query.getMessage().getChatId(), query.getData().substring(2,3),query.getData().substring(3));

        }
        SendMessage msg=new SendMessage();
        msg.setText("Успешно");
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Назад в меню").setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    public static void init(){
        //шаблон
        olimpiadsSC.add(new SubjectsFO(0,null,null));
    }
}
class SubjectsFO{
    int lvl;//1-3
    String sbt;//Русский текст
    String sKey;//ключ 3 символа
    public SubjectsFO(int l,String s,String k){
        this.lvl=l;
        this.sbt=s;
        this.sKey=k;
    }
    public boolean checkLvl(int l){
        return l>=lvl;
    }
}
