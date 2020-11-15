package school2107.bot.admin;

import java.util.Date;

public class Task {
    //дата выполнения
    public Date eventTime=new Date();
    //текст сообщения
    public String message;
    //огэ егэ олимпиада дайджест
    public String eventCategory;
    //класс\\по умолчанию 0;
    public int level=0;
    //тип олимпиады\\по умолчанию 0;
    public int oType=0;
    //предмет\\по умолчаию null
    public String subject=null;
    //версия для дайджеста
    Task(Date date,String message,String cat){
        this.eventTime=date;
        this.message=message;
        this.eventCategory=cat;
    }
    //версия огэ егэ
    Task(Date date,String message,String cat,String subject){
        this.eventTime=date;
        this.message=message;
        this.eventCategory=cat;
        this.subject=subject;
    }
    //версия для олимпиады
    Task(Date date,String message,String cat,int lvl,String subject,int tp){
        this.eventTime=date;
        this.message=message;
        this.eventCategory=cat;
        this.subject=subject;
        this.level=lvl;
        this.oType=tp;
    }
}
