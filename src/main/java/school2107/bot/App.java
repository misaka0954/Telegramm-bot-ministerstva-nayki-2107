package school2107.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import school2107.bot.admin.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class App {
    //Статичныей доступ к базе данных
    public static Releaser releaser;
    //Массив с событиями, получаемый из базы данных
    public static ArrayList<Task> tasks = new ArrayList<>();
    //Статичный доступ к обработчику событий
    public static EchoBot bot;
    //Таймер
    public static Timer tmr = new Timer();
    //Статичный класс вызывателя событий
    public static TimerTask tsk = new Updater();
    //Переменная ключа к администрированию
    public static String adminKey = null;
    //Переменные приемной кампании
    public static String prCa = null;
    //Переменные приемной кампании
    public static String prCaLink = null;

    /**
     * Главная функция бота, инициирует его работу
     *
     * @param args ключ для администрирования, ввод при запуске
     * @throws TelegramApiRequestException Стандартное исключение
     */
    public static void main(String[] args) throws TelegramApiRequestException {
        //Создание доступа к базе данных и ее инициация
        releaser = new Releaser();
        //Базовая функция АПИ
        ApiContextInitializer.init();
        //загрузка ключа администрирования в переменную при ее наличии//иначе запуск без пароля и доступа кадминистрированию
        if (args.length > 0) {
            adminKey = args[0];
        }
        //Базовая функция АПИ
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            //Чтение информации из фаила про ПК
            BufferedReader bufferedReader = new BufferedReader(new FileReader("pkinfo.txt"));
            prCa = bufferedReader.readLine();
            prCaLink = bufferedReader.readLine();
        } catch (IOException ignored) {
        }
        //регистрация слушателя и заполнение переменной с прямым доступом к боту
        botsApi.registerBot(bot = new EchoBot());
        //генерация главного меню
        Handler.mainMenu();
        //Запуск выполнятеля событий
        tmr.scheduleAtFixedRate(tsk, 1, 10000);
    }

    /**
     * Класс выполнятеля событий
     */
    public static class Updater extends TimerTask {
        public ArrayList<Task> list;

        @Override
        public void run() {
            try {
                this.list = App.tasks;
                System.out.print("+");
                for (Task t : this.list) {
                    if (t.eventTime.before(new Date()) || t.eventTime.equals(new Date())) {

                        SendMessage msg = new SendMessage();
                        msg.setText(t.message);
                        ArrayList<Long> members = new ArrayList<>();
                        switch (t.eventCategory) {
                            case "oge":
                                members = releaser.getMembersOE(t.subject, 2);
                                break;
                            case "ege":
                                members = releaser.getMembersOE(t.subject, 3);
                                break;
                            case "ddst":
                                members = releaser.getMembersDdst();
                                break;
                            case "olimps":
                                members = releaser.getMemberOlimpiada(t.subject, t.level);
                                break;
                        }
                        for (Long m : members) {
                            msg.setChatId(m);
                            bot.sendMessage(msg);
                        }
                        this.list.remove(t);
                        releaser.rmTask(t);
                    }
                }
                tasks = this.list;
            } catch (Exception ignored) {
            }
        }
    }
}
