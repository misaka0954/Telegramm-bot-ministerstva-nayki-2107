package pcconnector.bot.frames;

import javax.swing.*;

public class MainMenu extends JPanel {
    public static JButton executor = new JButton("Подключиться");
    public static JTextField executedField = new JTextField("127.0.0.1");
    public MainMenu(){
        this.add(executor);
        this.add(executedField);
    }
}
