package school2107.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import school2107.bot.admin.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class App {
public static realiser realiser;
public static ArrayList<Task> tasks = new ArrayList<Task>();
public static EchoBot bot;
public static Timer tmr=new Timer();
public static TimerTask tsk=new Updator();
public static String adminKey=null;



    public static void main(String[] args) throws TelegramApiRequestException {
        realiser=new realiser();
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();
        botsApi.registerBot(bot=new EchoBot());
        Handler.mainMenu();
        tmr.scheduleAtFixedRate(tsk,1,10000);

    }
    public static class Updator extends TimerTask{
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
                                members = realiser.getMembersOE(t.subject, 2);
                                break;
                            case "ege":
                                members = realiser.getMembersOE(t.subject, 3);
                                break;
                            case "ddst":
                                members = realiser.getMembersDdst();
                                break;
                            case "olimps":
                                members=realiser.getMemberOlimpiada(t.subject,t.level);
                                break;
                        }
                        for (Long m : members) {
                            msg.setChatId(m);
                            bot.sendMessage(msg);
                        }
                        this.list.remove(t);
                        realiser.rmTask(t);
                    }
                }
                tasks = this.list;

            } catch (Exception e) {
            }

        }
    }
}
