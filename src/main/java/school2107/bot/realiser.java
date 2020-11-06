package school2107.bot;

import java.sql.*;

public class realiser {
  public static final String DB_URL="jdbc:h2:./t";
  public static final String DB_Driver="org.h2.Driver";
  public Connection connection;
    public realiser(){
        try{
           Class.forName(DB_Driver);
           connection=DriverManager.getConnection(DB_URL);
            System.out.print("\nПодключение к субд прошло успешно\n");

        }catch(ClassNotFoundException e){
            System.out.print("\ncnf");
        }catch(SQLException e){
            System.out.print("\nsql");
            e.printStackTrace();

        }
        assertTables();
    }
    public void assertTables(){
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM ege");
            System.out.println("Таблица ЕГЭ существует");

        }catch(SQLException e){
            createTableOE("ege");
            System.out.println("Таблица ЕГЭ сгенерирована");

        }
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM oge");
            System.out.println("Таблица ОГЭ существует");

        }catch(SQLException e){
            createTableOE("oge");
            System.out.println("Таблица ОГЭ сгенерирована");

        }
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM errhandler");
            System.out.println("Таблица errhandler существует");

        }catch(SQLException e){
            createTableOE("errhandler");
            System.out.println("Таблица errhander сгенерирована");

        }
        addMemberOE(1l,"tst",0);
        rmMemberOE(1l,"tst",0);
    }
    public void createTableOE(String name){
        try {
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE TABLE "+name+" (uid LONG not NULL, sbt VARCHAR(3) not NULL)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void addMemberOE(Long uid, String sbt,int oe){
        String table="errhandler";
        if(oe==2){table="oge";}
        if(oe==3){table="ege";}
        try {
            Statement s = connection.createStatement();
            s.execute("INSERT INTO "+table+" VALUES ("+uid+",'"+sbt+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void rmMemberOE(Long uid, String sbt,int oe){
        String table="errhandler";
        if(oe==2){table="oge";}
        if(oe==3){table="ege";}
        try {
            Statement s = connection.createStatement();
            s.execute("DELETE FROM "+table+" WHERE UID="+uid+" AND sbt='"+sbt+"'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
