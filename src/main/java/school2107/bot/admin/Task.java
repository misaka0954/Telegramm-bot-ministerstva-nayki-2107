package school2107.bot.admin;

import java.util.Date;

public class Task {
    /**
     * дата выполнения
     */
    public Date eventTime;
    /**
     * Текст сообщения
     */
    public String message;
    /**
     * категория события
     */
    public String eventCategory;
    /**
     * Уровень события(для олимпиад)
     * от 1 до 3
     * в прочих случаях 0
     */
    public int level = 0;
    /**
     * Предмет события, по умолчанию null
     */
    public String subject = null;

    /**
     * Коструктор для дайджеста
     *
     * @param date    дата выполняния
     * @param message сообщение события
     * @param cat     категория события
     */
    public Task(Date date, String message, String cat) {
        this.eventTime = date;
        this.message = message;
        this.eventCategory = cat;
    }

    /**
     * Версия для ЕГЭ и ОГЭ
     *
     * @param date    дата вызова
     * @param message сообщение
     * @param cat     категория события
     * @param subject предмет
     */
    public Task(Date date, String message, String cat, String subject) {
        this.eventTime = date;
        this.message = message;
        this.eventCategory = cat;
        this.subject = subject;
    }

    /**
     * Версия для олимпиады
     *
     * @param date    дата вызова
     * @param message текст сообщения
     * @param cat     категория
     * @param lvl     уровень(1-3)
     * @param subject предмет
     */
    public Task(Date date, String message, String cat, int lvl, String subject) {
        this.eventTime = date;
        this.message = message;
        this.eventCategory = cat;
        this.subject = subject;
        this.level = lvl;
    }
}
