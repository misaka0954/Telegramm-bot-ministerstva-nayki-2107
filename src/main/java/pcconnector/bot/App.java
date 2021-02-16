package pcconnector.bot;

import pcconnector.bot.frames.MainMenu;

import javax.swing.*;

public class App {
    public static JFrame window = new JFrame("BOT CONNECTOR");
    public static JPanel screen = new JPanel();

    public static MainMenu mm = new MainMenu();

    public static void run(){
        window.setSize(500,500);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
    }
    public static void main(String[] args){
        run();
        screen=mm;
        window.add(screen);
        window.show();
        screen=null;
    }
}
