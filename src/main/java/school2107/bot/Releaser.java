package school2107.bot;

import school2107.bot.admin.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Класс сдоступом к бд
 */
public class Releaser {
    /**
     * Адрес бд
     */
    public static final String DB_URL = "jdbc:h2:./t";
    /**
     * Драйвер БД
     */
    public static final String DB_Driver = "org.h2.Driver";
    public Connection connection;

    /**
     * Конструктор класса, вызов подключения к базе
     */
    public Releaser() {
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL);
            System.out.print("\nПодключение к субд прошло успешно\n");

        } catch (ClassNotFoundException e) {
            System.out.print("\ncnf");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.print("\nsql");
            e.printStackTrace();
        }
        assertTables();
    }

    /**
     * Провирка на существование таблиц
     */
    public void assertTables() {
        try {
            Statement s = connection.createStatement();
            s.executeQuery("SELECT * FROM ddst");
            System.out.println("Таблица Дайджестов существует");
        } catch (SQLException e) {
            createTableDdst("ddst");
            System.out.println("Таблица Дайджестов сгенерирована");

        }
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM task");
            System.out.println("Таблица Ивентов существует");
            while(rs.next()){
                App.tasks.add(new Task(new Date(rs.getLong("dta")),rs.getString("msg"),rs.getString("cat"),rs.getInt("lvl"),rs.getString("sbt")));
            }
        }catch(SQLException e){
            createTableTask("task");
            System.out.println("Таблица Ивентов сгенерирована");

        }
        try {
            Statement s = connection.createStatement();
            s.executeQuery("SELECT * FROM ege");
            System.out.println("Таблица ЕГЭ существует");

        }catch(SQLException e){
            createTableOE("ege");
            System.out.println("Таблица ЕГЭ сгенерирована");

        }
        try {
            Statement s = connection.createStatement();
            s.executeQuery("SELECT * FROM oge");
            System.out.println("Таблица ОГЭ существует");

        }catch(SQLException e){
            createTableOE("oge");
            System.out.println("Таблица ОГЭ сгенерирована");

        }
        try {
            Statement s = connection.createStatement();
            s.executeQuery("SELECT * FROM olp");
            System.out.println("Таблица Олимпиад существует");

        }catch(SQLException e){
            createTableOp("olp");
            System.out.println("Таблица Олимпиад сгенерирована");

        }


        try {
            Statement s = connection.createStatement();
            s.executeQuery("SELECT * FROM errhandler");
            System.out.println("Таблица errhandler существует");

        } catch (SQLException e) {
            createTableOE("errhandler");
            System.out.println("Таблица errhander сгенерирована");

        }
    }

    /**
     * Генератор таблиц ОГЭ и ЕГЭ
     *
     * @param name имя таблицы
     */
    public void createTableOE(String name) {
        try {
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE TABLE " + name + " (uid LONG not NULL, sbt VARCHAR(3) not NULL)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Генератор таблицы олимпиад
     *
     * @param name имя таблицы
     */
    public void createTableOp(String name) {
        try {
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE TABLE " + name + " (uid LONG not NULL,lvl int not NUll, sbt VARCHAR(3) not NULL)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Генератор таблицы заданий
     *
     * @param name Имя таблицы
     */
    public void createTableTask(String name) {
        try {
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE TABLE " + name + " (uid LONG not NULL,dta long not null, sbt VARCHAR(3) not NULL,msg VARCHAR not NULL,cat VARCHAR(20),lvl INT not NUll)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Генератор таблицы дайджестов
     *
     * @param name имя таблицы
     */
    public void createTableDdst(String name) {
        try {
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE TABLE " + name + " (uid LONG not NULL)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Добавление в таблицу ОГЭ/ЕГЭ участника
     *
     * @param uid айди чата
     * @param sbt код предмета
     * @param oe  тип
     */
    public void addMemberOE(Long uid, String sbt, int oe) {
        String table = "errhandler";
        if (oe == 2) {
            table = "oge";
        }
        if (oe == 3) {
            table = "ege";
        }
        try {
            Statement s = connection.createStatement();
            if (isMemberOE(uid, sbt, oe)) {
                return;
            }
            s.execute("INSERT INTO " + table + " VALUES (" + uid + ",'" + sbt + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Удаление записи из таблицы ОГЭ/ЕГЭ
     *
     * @param uid айди чата
     * @param sbt код предмета
     * @param oe  уровень
     */
    public void rmMemberOE(Long uid, String sbt, int oe) {
        String table = "errhandler";
        if (oe == 2) {
            table = "oge";
        }
        if (oe == 3) {
            table = "ege";
        }
        try {
            Statement s = connection.createStatement();
            if (!isMemberOE(uid, sbt, oe)) {
                return;
            }
            s.execute("DELETE FROM " + table + " WHERE UID=" + uid + " AND sbt='" + sbt + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Получение списка участников
     *
     * @param sbt код предмета
     * @param oe  уровень
     * @return массив ID участников
     */
    public ArrayList<Long> getMembersOE(String sbt, int oe) {
        System.out.println("requested");
        String table = "errhandler";
        if (oe == 2) {
            table = "oge";
        }
        if (oe == 3) {
            table = "ege";
        }
        ArrayList<Long> rtn = new ArrayList<>();
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM " + table);
            while (rs.next()) {
                if (rs.getString("sbt").equalsIgnoreCase(sbt)) {
                    System.out.println(rs.getLong("uid"));
                    rtn.add(rs.getLong("uid"));
                }
            }
        } catch (SQLException ignored) {

        }
        return rtn;
    }

    /**
     * Проверка на наличие записи в таблице
     *
     * @param uid айди
     * @param sbt предмет
     * @param oe  кровень
     * @return true\false
     */
    public boolean isMemberOE(Long uid, String sbt, int oe) {
        String table = "errhandler";
        if (oe == 2) {
            table = "oge";
        }
        if (oe == 3) {
            table = "ege";
        }
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM " + table);
            while (rs.next()) {
                if (rs.getString("sbt").equalsIgnoreCase(sbt)) {
                    if (rs.getLong("uid") == uid) {
                        return true;
                    }
                }
            }
        } catch (SQLException ignored) {

        }
        return false;
    }

    /**
     * Получение записей про дайджест
     *
     * @return массив айди
     */
    public ArrayList<Long> getMembersDdst() {
        String table = "ddst";
        ArrayList<Long> rtn = new ArrayList<>();
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM " + table);
            while (rs.next()) {
                rtn.add(rs.getLong("uid"));
            }
        } catch (SQLException ignored) {
        }
        return rtn;
    }

    /**
     * Добавление айди в таблицу
     *
     * @param uid айди
     */
    public void addMemberDdst(Long uid) {
        String table = "ddst";
        try {
            Statement s = connection.createStatement();
            s.execute("INSERT INTO " + table + " VALUES (" + uid + ")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Удаоление айди из таблицы
     *
     * @param uid айди
     */
    public void rmMemberDdst(Long uid) {
        String table = "ddst";
        try {
            Statement s = connection.createStatement();

            s.execute("DELETE FROM " + table + " WHERE UID=" + uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Добавление записи про олимпиаду
     *
     * @param uid айди
     * @param lvl уровень
     * @param sbt предмет
     */
    public void addMemberOlimpiada(Long uid, String lvl, String sbt) {
        String table = "olp";
        try {
            Statement s = connection.createStatement();
            s.execute("INSERT INTO " + table + " VALUES (" + uid + "," + Integer.parseInt(lvl) + ",'" + sbt + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Запрос списка участников с требуемыми параметрами
     *
     * @param sbt предмет
     * @param lvl уровень
     * @return массив айди
     */
    public ArrayList<Long> getMemberOlimpiada(String sbt, int lvl) {
        ArrayList<Long> rtn = new ArrayList<>();
        Statement s;
        try {
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM olp");
            while (rs.next()) {
                if (rs.getString("sbt").equalsIgnoreCase(sbt) && rs.getLong("lvl") == lvl) {
                    System.out.println(rs.getLong("uid"));
                    rtn.add(rs.getLong("uid"));
                }
            }
            return rtn;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * Удаление записи из таблицы
     *
     * @param uid айди
     * @param lvl уровень
     * @param sbt предмет
     */
    public void rmMemberOlimpiada(Long uid, String lvl, String sbt) {
        String table = "olp";
        try {
            Statement s = connection.createStatement();

            s.execute("DELETE FROM " + table + " WHERE UID=" + uid + " AND lvl=" + Integer.parseInt(lvl) + " AND sbt='" + sbt + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * добавление задания в базу
     *
     * @param t задание\событие
     */
    public void addTask(Task t) {
        String table = "task";
        try {
            Statement s = connection.createStatement();
            long key = new java.util.Date().getTime();
            s.execute("INSERT INTO " + table + " VALUES (" + key + "," + t.eventTime.getTime() + ",'" + t.subject + "','" + t.message + "','" + t.eventCategory + "'," + t.level + ")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * удаление записи из таблицы
     *
     * @param t запись
     */
    public void rmTask(Task t) {

        String table = "task";
        try {
            Statement s = connection.createStatement();
            s.execute("DELETE FROM " + table + " WHERE dta=" + t.eventTime.getTime() + " and sbt='" + t.subject + "' and msg='" + t.message + "' and cat='" + t.eventCategory + "' and lvl=" + t.level);
            //uid LONG not NULL,dta Date not null, sbt VARCHAR(3) not NULL,msg VARCHAR(255) not NULL,cat VARCHAR(20),lvl INT not NUll)
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
