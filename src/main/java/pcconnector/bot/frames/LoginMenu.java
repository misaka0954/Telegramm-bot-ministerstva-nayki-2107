package pcconnector.bot.frames;

import pcconnector.bot.App;
import pcconnector.bot.connect.SocketClient;

import javax.swing.*;
import java.io.IOException;

public class LoginMenu extends JPanel {
    public static JButton executor = new JButton("Подключиться");
    public static JTextField executedField = new JTextField("127.0.0.1");
    public static JTextField passwordField = new JTextField();
    public static JLabel info1 = new JLabel("Адрес");
    public static JLabel info2 = new JLabel("Пароль");

    public LoginMenu() {
        executor.addActionListener(e -> {
            try {
                SocketClient.netClient = new SocketClient(executedField.getText(), passwordField.getText());
            } catch (IOException ioException) {
                JOptionPane.showConfirmDialog(this, "Ошибка подключения, повторите попытку позже");
            } catch (SocketClient.IncorrectData incorrectData) {
                JOptionPane.showConfirmDialog(this, "Ошибка при вводе пароля");
            }
            App.screen = new MenuFrame();
        });

        this.setLayout(null);
        this.add(executor).setBounds(100, 10, 300, 25);
        this.add(info1).setBounds(100, 40, 300, 10);
        this.add(executedField).setBounds(100, 55, 300, 25);
        this.add(info2).setBounds(100, 90, 300, 10);
        this.add(passwordField).setBounds(100, 110, 300, 25);
    }
}
