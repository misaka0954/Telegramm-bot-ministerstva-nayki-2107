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
            System.out.print("\nПодключение к субд прошло успешно");

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
            createTable("ege");
            System.out.println("Таблица ЕГЭ сгенерирована");

        }
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM oge");
            System.out.println("Таблица ОГЭ существует");

        }catch(SQLException e){
            createTable("oge");
            System.out.println("Таблица ОГЭ сгенерирована");

        }
    }
    public void createTable(String name){
        try {
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE TABLE "+name+" (id INTEGER not NULL AUTO_INCREMENT, ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
