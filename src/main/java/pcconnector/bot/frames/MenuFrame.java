package pcconnector.bot.frames;

import javax.swing.*;

public class MenuFrame extends JPanel {

    public static JButton prka = new JButton("Приемная кампания");
    public static JButton tasks = new JButton("Задания");
    public static JButton edit = new JButton("Редактирование списков по предметам");

    public MenuFrame() {
        this.setLayout(null);
        this.add(prka).setBounds(100, 10, 300, 25);
        this.add(tasks).setBounds(100, 50, 300, 25);
        this.add(prka).setBounds(100, 100, 300, 25);
    }
}
