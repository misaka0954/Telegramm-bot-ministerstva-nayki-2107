package school2107.bot.admin;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import school2107.bot.App;
import school2107.bot.EchoBot;

import javax.script.ScriptEngine;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    public class AdminHandler {
        public static int stage=0;//0 пассив.1 дата.2 сообщение.3 категория.4 предмет(если не дайджест).5 класс(если олимпиада).6 категория.
        public static Date date=new Date();
        public static String message;
        public static String category;
        public static String subject;
        public static int level=0;
        public static int typeO=0;
        public static SimpleDateFormat dt=new SimpleDateFormat("dd/MM/yyyy HH:mm");

        static ArrayList<Task> task=null;
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
            line.add(new InlineKeyboardButton().setText("Удалить рассылку").setCallbackData("a2"));
            buttons.add(line);
            line =new ArrayList<>();

            kbd.setKeyboard(buttons);
            msg.setReplyMarkup(kbd);
            buttonsMenu=buttons;
            return msg;
        }
        public static SendMessage msgRouter(String s){

            switch(stage){

                case 1:
                    return crSt1(s);
                case 2:
                    return crSt2(s);
                case 3:
                    return crSt3(s);
                case 4:
                    return crSt4(s);

            }
            return new SendMessage().setText("для выхода введите exit");
        }
        public static SendMessage crSt1(String s){
            SendMessage msg=new SendMessage();
            try{
                date=dt.parse(s);
            }catch(Exception e){
                msg.setText("Ошибка в преобразовании даты. Попробуйте еще раз");
                return msg;
            }
            stage++;
            msg.setText("Дата успешно установлена на "+dt.format(date)+"\nВведите сообщение которое будет выведено");
            return msg;
        }
        public static SendMessage crSt2(String s){
            SendMessage msg=new SendMessage();
            message=s;
            stage++;
            msg.setText("Сообщение успешно установлено как:\""+message+"\"\nВведите категорию сообщения:\n1 олимпиада\n2 огэ\n3 егэ\n4 дайджест\n для совершения выбора требуется ввести ТОЛЬКО цифру");
            return msg;
        }
        public static SendMessage crSt3(String s){
            SendMessage msg=new SendMessage();
            switch(s){
                case "1":
                    stage=0;
                    msg.setText("Категория олимпиад выбрана. На этом наши полномочия всё");
                    category="olimp";
                    return msg;
                case "2":
                    stage++;
                    msg.setText("категория огэ выбрана,выберите предмет из представленных").setReplyMarkup(kbd(2));
                    category="oge";
                    return msg;
                case "3":
                    stage++;
                    msg.setText("категория егэ выбрана,выберите предмет из представленных").setReplyMarkup(kbd(3));
                    category="ege";
                    return msg;
                case "4":
                    stage=0;
                    msg.setText("категория дайджест выбрана. Успешно создано");
                    App.tasks.add(new Task(date,message,category));
                    category="ddst";
                    return msg;
            }
            return msg;
        }
        public static SendMessage crSt4(String s){
            SendMessage msg =new SendMessage();
            stage=0;
            subject=s.substring(1);
            App.tasks.add(new Task(date,message,category,subject));
            msg.setText("Успешно создано");
            return msg;
        }




        public static SendMessage router(CallbackQuery cbq) {
            String id= cbq.getData();
            if(id.length()>2&&id.startsWith("a2")){return rmCertainTask(cbq);}
            if(id.length()>3){
                return crSt4(cbq.getData());
            }
            switch(id){
                case "a0":
                    InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
                    kbd.setKeyboard(buttonsMenu);
                    return new SendMessage().setText("Админское меню:").setReplyMarkup(kbd);
                case "a1":
                    return createRunner();
                case "a2":
                    return sendRemover();
            }
            InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
            kbd.setKeyboard(buttonsMenu);
            return new SendMessage().setText("Эта функция в разработке. Выберите пункт из меню:").setReplyMarkup(kbd);
        }
        public static SendMessage createRunner(){
            SendMessage msg=new SendMessage();
            stage++;
            msg.setText("Процесс создания инициирован. Введите дату и время в формате дд/мм/гг чч:мм\n пример 01/02/2020 15:30");
            return msg;
        }


        public static SendMessage sendRemover(){
            SendMessage msg=new SendMessage();
            msg.setText("Выберите удаляемое сообщение");
            InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
            List<InlineKeyboardButton> line =new ArrayList<>();
            List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
            int i=0;
            task=App.tasks;
            for(Task t: task){
            line.add(new InlineKeyboardButton().setText(t.eventCategory+" "+t.oType+" "+t.subject+" "+dt.format(t.eventTime)).setCallbackData("a2"+i));
            buttons.add(line);
            line =new ArrayList<>();
            i++;
            }
            kbd.setKeyboard(buttons);
            msg.setReplyMarkup(kbd);
            return msg;
        }
        public static SendMessage rmCertainTask(CallbackQuery cbq){
            App.tasks.remove(task.get(Integer.parseInt(cbq.getData().substring(2))));
            SendMessage msg=new SendMessage().setText("Успешно");
            return msg;
        }
        
        public static InlineKeyboardMarkup kbd(int lvl){
            InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
            List<InlineKeyboardButton> line =new ArrayList<>();
            String prefix="a";
            //деление математики по уровню сложности
            if(lvl==2){
                line.add(new InlineKeyboardButton().setText("Математика").setCallbackData(prefix + "mat"));
                buttons.add(line);
                line = new ArrayList<>();
            }
            else{
                line.add(new InlineKeyboardButton().setText("Математика (Базовая)").setCallbackData(prefix + "mab"));
                buttons.add(line);
                line = new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("Математика (Профильная)").setCallbackData(prefix + "map"));
                buttons.add(line);
                line = new ArrayList<>();
            }
            if(true) {

                line.add(new InlineKeyboardButton().setText("Русский язык").setCallbackData(prefix + "rus"));
                buttons.add(line);
                line = new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("Литература").setCallbackData(prefix + "lit"));
                buttons.add(line);
                line = new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("Физика").setCallbackData(prefix + "fiz"));
                buttons.add(line);
                line = new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("Химия").setCallbackData(prefix + "him"));
                buttons.add(line);
                line = new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("Биология").setCallbackData(prefix + "bio"));
                buttons.add(line);
                line = new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("География").setCallbackData(prefix + "geo"));
                buttons.add(line);
                line = new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("История").setCallbackData(prefix + "ist"));
                buttons.add(line);
                line = new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("Обществознание").setCallbackData(prefix + "obs"));
                buttons.add(line);
                line = new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("Информатика и ИКТ").setCallbackData(prefix + "ikt"));
                buttons.add(line);
                line = new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("Английский язык").setCallbackData(prefix + "eng"));
                buttons.add(line);
                line = new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("Немецкий язык").setCallbackData(prefix + "nem"));
                buttons.add(line);
                line = new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("Французский язык").setCallbackData(prefix + "fra"));
                buttons.add(line);
                line = new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("Испанский язык").setCallbackData(prefix + "isp"));
                buttons.add(line);
                line = new ArrayList<>();
                if(lvl==3){
                    line.add(new InlineKeyboardButton().setText("Китайский язык").setCallbackData(prefix + "kit"));
                    buttons.add(line);
                    line = new ArrayList<>();}

            }


            kbd.setKeyboard(buttons);
            return kbd;
        }
        
    }
