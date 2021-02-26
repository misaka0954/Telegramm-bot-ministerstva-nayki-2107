package school2107.bot.admin;

import java.util.Date;

public class Task {
    //дата выполнения
    public Date eventTime;
    //текст сообщения
    public String message;
    //огэ егэ олимпиада дайджест
    public String eventCategory;
    //уровень\\по умолчанию 0;значения 1-3
    public int level=0;
    //предмет\\по умолчаию null
    public String subject=null;
    //версия для дайджеста
    public Task(Date date,String message,String cat){
        this.eventTime=date;
        this.message=message;
        this.eventCategory=cat;
    }
    //версия огэ егэ
    public Task(Date date,String message,String cat,String subject){
        this.eventTime=date;
        this.message=message;
        this.eventCategory=cat;
        this.subject=subject;
    }
    //версия для олимпиады
    public Task(Date date,String message,String cat,int lvl,String subject){
        this.eventTime=date;
        this.message=message;
        this.eventCategory=cat;
        this.subject=subject;
        this.level=lvl;
    }
}
