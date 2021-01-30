package school2107.bot.admin;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import school2107.bot.App;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//todo сдклать олимпиады и вкид в бд
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
        public static void reset(){
            stage=0;
            level=0;
            typeO=0;
            message=null;
            category=null;
            subject=null;
        }
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
            line.add(new InlineKeyboardButton().setText("Перезапись информации о пк").setCallbackData("a3"));
            buttons.add(line);
            line =new ArrayList<>();
            line.add(new InlineKeyboardButton().setText("Сброс информации о приемной кампании").setCallbackData("a4"));
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
                case -2:
                    return crStI2(s);
                case -1:
                    return crStI1(s);

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
                    //todo обработка входящих олимпиад
                    stage++;
                    msg.setText("Категория олимпиад выбрана, выберите предмет из представленных").setReplyMarkup(kbd2("a11"));
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
                    category="ddst";
                    App.tasks.add(new Task(date,message,category));
                    App.realiser.addTask(new Task(date,message,category));
                    return msg;
            }
            return msg;
        }
        public static SendMessage crSt4(String s){
            SendMessage msg =new SendMessage();
            stage=0;
            subject=s.substring(1);
            if(category=="olimp"){
                InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
                List<InlineKeyboardButton> line =new ArrayList<>();
                line.add(new InlineKeyboardButton().setText("4-6").setCallbackData("a11"+subject+1));
                buttons.add(line);
                line =new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("7-9").setCallbackData("a11"+subject+2));
                buttons.add(line);
                line =new ArrayList<>();

                line.add(new InlineKeyboardButton().setText("10-11").setCallbackData("a11"+subject+3));
                buttons.add(line);
                line =new ArrayList<>();
                kbd.setKeyboard(buttons);
                return new SendMessage().setText("Выберите уровень обучения из представленных").setReplyMarkup(kbd);}
            App.tasks.add(new Task(date,message,category,subject));
            App.realiser.addTask(new Task(date,message,category,subject));
            msg.setText("Успешно создано");
            return msg;
        }
        public static SendMessage crStI2(String s){
            SendMessage msg =new SendMessage();
            stage=-1;
            App.prCaLink=s;
            msg.setText("Введите текст для сообщения");
            return msg;
        }
        public static SendMessage crStI1(String s){
            SendMessage msg =new SendMessage();
            stage=0;
            App.prCa=s;
            msg.setText("Успешно");
            return msg;
        }
        public static SendMessage olimpCreate(CallbackQuery cbq){
            SendMessage msg =new SendMessage();
            stage=0;
            stage=Integer.parseInt(cbq.getData().substring(cbq.getData().length()-1));
            App.tasks.add(new Task(date,message,category,stage,subject.substring(2)));
            App.realiser.addTask(new Task(date,message,category,stage,subject.substring(2)));
            msg.setText("Успешно создано");
            return msg;
        }




        public static SendMessage router(CallbackQuery cbq) {
            String id= cbq.getData();
            if(id.length()>6){return olimpCreate(cbq);}
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
                case "a3":
                    return createRERunner();
                case "a4":
                    return clearPC();
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
    public static SendMessage clearPC(){
        SendMessage msg=new SendMessage();
        App.prCaLink=null;
        App.prCa=null;
        //TODO добавить очистку хранилища
        msg.setText("Очистка завершена");
        return msg;
    }
    public static SendMessage createRERunner(){
        SendMessage msg=new SendMessage();
        stage=-2;
        msg.setText("Процесс изменения инициирован. Введите ссылку на сайт");
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
            line.add(new InlineKeyboardButton().setText(t.eventCategory+" "+t.subject+" "+dt.format(t.eventTime)).setCallbackData("a2"+i));
            buttons.add(line);
            line =new ArrayList<>();
            i++;
            }
            kbd.setKeyboard(buttons);
            msg.setReplyMarkup(kbd);
            return msg;
        }
        public static SendMessage rmCertainTask(CallbackQuery cbq){
            App.realiser.rmTask(task.get(Integer.parseInt(cbq.getData().substring(2))));
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
        public static InlineKeyboardMarkup kbd2(String cbq){
        InlineKeyboardMarkup kbd=new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons =new ArrayList<>();
        List<InlineKeyboardButton> line =new ArrayList<>();
        String prefix=cbq;
            line.add(new InlineKeyboardButton().setText("Математика").setCallbackData(prefix + "mat"));
            buttons.add(line);
            line = new ArrayList<>();


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

                line.add(new InlineKeyboardButton().setText("Китайский язык").setCallbackData(prefix + "kit"));
                buttons.add(line);
                line = new ArrayList<>();



        kbd.setKeyboard(buttons);
        return kbd;
    }
        
    }
