package school2107.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

//TODO изменить убирание подписок
public class Handler {
    //public static ArrayList<SubjectsFO> olimpiadsSC=new ArrayList<>();

    public static List<List<InlineKeyboardButton>> buttonsMenu = new ArrayList<>();
    /**
    * return Шаблон меню, Требуется указать канал отправления
    */
    public static SendMessage mainMenu() {
        SendMessage msg = new SendMessage();
        msg.setText(B.MENU_HEADER);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> line = new ArrayList<>();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.OLIMPIADS).setCallbackData("1"));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.OGE).setCallbackData("2"));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.EGE).setCallbackData("3"));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.SHOOLSOURCES).setCallbackData("4"));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.LINKS).setCallbackData("5"));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.DAIGEST).setCallbackData("6"));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.DEVS).setCallbackData("7"));
        buttons.add(line);
        if(App.prCa!=null&&App.prCaLink!=null){
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.PR_CA).setCallbackData("8"));
        buttons.add(line);
        }
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        buttonsMenu = buttons;
        return msg;
    }
    /**
    * @return сообщение подходящее под заданый ключ
    */
    public static SendMessage router(CallbackQuery cbq) {
        String id = cbq.getData();
        if ((id.startsWith("21") || id.startsWith("31")) && id.length() > 2) {
            return subjectPushOgeEge(cbq);
        }
        if ((id.startsWith("22") || id.startsWith("32")) && id.length() > 2) {
            return subjectRemoveOgeEge(cbq);
        }
        if ((id.startsWith("11") || id.startsWith("12")) && id.length() > 3) {
            return PRolimpiada(cbq);
        }
        if ((id.startsWith("11") || id.startsWith("12")) && id.length() > 2) {
            return sbtChooser(cbq);
        }
        switch (id) {
            case "0":
                InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
                kbd.setKeyboard(buttonsMenu);
                return new SendMessage().setText(B.MENU_HEADER).setReplyMarkup(kbd);
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
                return pRddt(cbq, 1);
            case "62":
                return pRddt(cbq, 0);
            case "7":
                return devs();
            case "8":
                return priemnaa_comissia();

        }
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        kbd.setKeyboard(buttonsMenu);
        return new SendMessage().setText(B.ACTION_ERR).setReplyMarkup(kbd);
    }
    /**
     * @return сообщение про разработчиков
     */
    private static SendMessage devs() {
        SendMessage msg = new SendMessage();
        msg.setText(B.DEVS_INFO);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line;
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }
    /**
     * @return сообщение о приемной комиссии
     */
    private static SendMessage priemnaa_comissia() {
        SendMessage msg = new SendMessage();
        msg.setText(App.prCa);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line;
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("Ссылка").setUrl(App.prCaLink));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }
    /**
     * @return сообщение о школьных ресурсах
     */
    private static SendMessage schSources() {
        SendMessage msg = new SendMessage();
        msg.setText(B.SHOOLSOURCES);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.SITE).setUrl("https://sch2107.mskobr.ru/"));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.YT).setUrl("https://www.youtube.com/channel/UCGzMXQcu9o7L1Hb_kxscw2g"));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.IS).setUrl("https://www.instagram.com/2107school/"));
        buttons.add(line);

        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }
    /**
     * @return сообщение о полезных ресурсах для подготовки
     */
    private static SendMessage infRes() {
        SendMessage msg = new SendMessage();
        msg.setText(B.IR_HEADER);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.SITE1).setUrl(B.SITE1IP));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.SITE2).setUrl(B.SITE2IP));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.SITE3).setUrl(B.SITE3IP));
        buttons.add(line);

        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }
    /**
     * @return сообщение о выборе предмета регистрация егэ
     */
    private static SendMessage ege() {
        SendMessage msg = new SendMessage();
        msg.setText(B.EGE_HEADER);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.SUB).setCallbackData("31"));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.UNSUB).setCallbackData("32"));
        buttons.add(line);

        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }
    /**
     * @return сообщение о выборе предмета регистация огэ
     */
    private static SendMessage oge() {
        SendMessage msg = new SendMessage();
        msg.setText(B.OGE_HEADER);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.SUB).setCallbackData("21"));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.UNSUB).setCallbackData("22"));
        buttons.add(line);

        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }
    /**
     * @return сообщение о регистрация на дайжесты
     */
    private static SendMessage ddt() {
        SendMessage msg = new SendMessage();
        msg.setText(B.DDT_HEADER);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.SUB).setCallbackData("61"));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.UNSUB).setCallbackData("62"));
        buttons.add(line);

        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }
    
    private static SendMessage olimpiads() {
        SendMessage msg = new SendMessage();
        msg.setText(B.OLP_HEADER);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.SUB).setCallbackData("11"));
        buttons.add(line);
        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.UNSUB).setCallbackData("12"));
        buttons.add(line);

        line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    public static SendMessage classChooser(CallbackQuery query) {
        SendMessage msg = new SendMessage();
        msg.setText(B.CLASS_LEVEL);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText("4-6").setCallbackData(query.getData() + 1));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText("7-9").setCallbackData(query.getData() + 2));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText("10-11").setCallbackData(query.getData() + 3));
        buttons.add(line);
        line = new ArrayList<>();


        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    public static SendMessage sbtChooser(CallbackQuery query) {
        SendMessage msg = new SendMessage();
        msg.setText(B.SBT_SELECT);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.MAT).setCallbackData(query.getData() + "mat"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.RUS).setCallbackData(query.getData() + "rus"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.LIT).setCallbackData(query.getData() + "lit"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.FIZ).setCallbackData(query.getData() + "fiz"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.HIM).setCallbackData(query.getData() + "him"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.BIO).setCallbackData(query.getData() + "bio"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.GEO).setCallbackData(query.getData() + "geo"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.IST).setCallbackData(query.getData() + "ist"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.OBS).setCallbackData(query.getData() + "obs"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.IKT).setCallbackData(query.getData() + "ikt"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.ENG).setCallbackData(query.getData() + "eng"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.NEM).setCallbackData(query.getData() + "nem"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.FRA).setCallbackData(query.getData() + "fra"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.ISP).setCallbackData(query.getData() + "isp"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.KIT).setCallbackData(query.getData() + "kit"));
        buttons.add(line);
        line = new ArrayList<>();

        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    public static SendMessage subjectPushOgeEge(CallbackQuery query) {
        int type = 0;
        if (query.getData().startsWith("2")) {
            type = 2;
        }
        if (query.getData().startsWith("3")) {
            type = 3;
        }
        App.releaser.addMemberOE(query.getMessage().getChatId(), query.getData().substring(2), type);
        SendMessage msg = new SendMessage();
        msg.setText(B.ACTION_S);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    public static SendMessage pRddt(CallbackQuery query, int a) {
        if (a == 1) {
            App.releaser.addMemberDdst(query.getMessage().getChatId());
        } else {
            App.releaser.rmMemberDdst(query.getMessage().getChatId());
        }
        SendMessage msg = new SendMessage();
        msg.setText(B.ACTION_S);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    public static SendMessage subjectRemoveOgeEge(CallbackQuery query) {
        int type = 0;
        if (query.getData().startsWith("2")) {
            type = 2;
        }
        if (query.getData().startsWith("3")) {
            type = 3;
        }
        App.releaser.rmMemberOE(query.getMessage().getChatId(), query.getData().substring(2), type);
        SendMessage msg = new SendMessage();
        msg.setText(B.ACTION_S);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    public static SendMessage subjectChooseOgeEge(CallbackQuery query) {
        SendMessage msg = new SendMessage();
        msg.setText(B.SBT_SELECT);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line = new ArrayList<>();

        boolean rm = false;
        int oe=2;
        if(query.getData().startsWith("3")){oe=3;}
        if(query.getData().startsWith("22")||query.getData().startsWith("32")){rm=true;}
        //деление математики по уровню сложности
        if (query.getData().startsWith("2")) {
            if (App.releaser.isMemberOE(query.getMessage().getChatId(), "mat", oe) == rm) {
                line.add(new InlineKeyboardButton().setText(B.MAT).setCallbackData(query.getData() + "mat"));
                buttons.add(line);
                line = new ArrayList<>();
            }
        } else {
            if (App.releaser.isMemberOE(query.getMessage().getChatId(), "mab", oe) == rm) {
                line.add(new InlineKeyboardButton().setText(B.MAT_B).setCallbackData(query.getData() + "mab"));
                buttons.add(line);
                line = new ArrayList<>();
            }
            if (App.releaser.isMemberOE(query.getMessage().getChatId(), "map", oe) == rm) {

                line.add(new InlineKeyboardButton().setText(B.MAT_P).setCallbackData(query.getData() + "map"));
                buttons.add(line);
                line = new ArrayList<>();
            }
        }
        if (App.releaser.isMemberOE(query.getMessage().getChatId(), "rus", oe) == rm) {

            line.add(new InlineKeyboardButton().setText(B.RUS).setCallbackData(query.getData() + "rus"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        if (App.releaser.isMemberOE(query.getMessage().getChatId(), "lit", oe) == rm) {

            line.add(new InlineKeyboardButton().setText(B.LIT).setCallbackData(query.getData() + "lit"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        if (App.releaser.isMemberOE(query.getMessage().getChatId(), "fiz", oe) == rm) {
            line.add(new InlineKeyboardButton().setText(B.FIZ).setCallbackData(query.getData() + "fiz"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        if (App.releaser.isMemberOE(query.getMessage().getChatId(), "him", oe) == rm) {
            line.add(new InlineKeyboardButton().setText(B.HIM).setCallbackData(query.getData() + "him"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        if (App.releaser.isMemberOE(query.getMessage().getChatId(), "bio", oe) == rm) {
            line.add(new InlineKeyboardButton().setText(B.BIO).setCallbackData(query.getData() + "bio"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        if (App.releaser.isMemberOE(query.getMessage().getChatId(), "geo", oe) == rm) {
            line.add(new InlineKeyboardButton().setText(B.GEO).setCallbackData(query.getData() + "geo"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        if (App.releaser.isMemberOE(query.getMessage().getChatId(), "ist", oe) == rm) {
            line.add(new InlineKeyboardButton().setText(B.IST).setCallbackData(query.getData() + "ist"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        if (App.releaser.isMemberOE(query.getMessage().getChatId(), "obs", oe) == rm) {

            line.add(new InlineKeyboardButton().setText(B.OBS).setCallbackData(query.getData() + "obs"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        if (App.releaser.isMemberOE(query.getMessage().getChatId(), "ikt", oe) == rm) {

            line.add(new InlineKeyboardButton().setText(B.IKT).setCallbackData(query.getData() + "ikt"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        if (App.releaser.isMemberOE(query.getMessage().getChatId(), "eng", oe) == rm) {
            line.add(new InlineKeyboardButton().setText(B.ENG).setCallbackData(query.getData() + "eng"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        if (App.releaser.isMemberOE(query.getMessage().getChatId(), "nem", oe) == rm) {
            line.add(new InlineKeyboardButton().setText(B.NEM).setCallbackData(query.getData() + "nem"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        if (App.releaser.isMemberOE(query.getMessage().getChatId(), "fra", oe) == rm) {

            line.add(new InlineKeyboardButton().setText(B.FRA).setCallbackData(query.getData() + "fra"));
            buttons.add(line);
            line = new ArrayList<>();
        }
        if (App.releaser.isMemberOE(query.getMessage().getChatId(), "isp", oe) == rm) {

            line.add(new InlineKeyboardButton().setText(B.ISP).setCallbackData(query.getData() + "isp"));
            buttons.add(line);
            line = new ArrayList<>();
        }

        if (query.getData().startsWith("3") && App.releaser.isMemberOE(query.getMessage().getChatId(), "kit", oe) == rm) {
            line.add(new InlineKeyboardButton().setText(B.KIT).setCallbackData(query.getData() + "kit"));
            buttons.add(line);
            line = new ArrayList<>();
        }

        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

    public static SendMessage PRolimpiada(CallbackQuery query) {
        int type = 0;
        if (query.getData().startsWith("11")) {
            type = 1;
        }
        if (query.getData().startsWith("12")) {
            type = 2;
        }
        if (type == 1) {
            App.releaser.addMemberOlimpiada(query.getMessage().getChatId(), query.getData().substring(2, 3), query.getData().substring(3));
        }
        if (type == 2) {
            App.releaser.rmMemberOlimpiada(query.getMessage().getChatId(), query.getData().substring(2, 3), query.getData().substring(3));

        }
        SendMessage msg = new SendMessage();
        msg.setText(B.ACTION_S);
        InlineKeyboardMarkup kbd = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().setText(B.RTN).setCallbackData("0"));
        buttons.add(line);
        kbd.setKeyboard(buttons);
        msg.setReplyMarkup(kbd);
        return msg;
    }

}

