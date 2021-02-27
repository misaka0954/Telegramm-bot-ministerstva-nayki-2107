package pcconnector.bot.connect;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    public static SocketClient netClient;
    public Socket socket;
    BufferedReader in;
    BufferedWriter out;

    public SocketClient(String ip, String password) throws IOException, IncorrectData {
        socket = new Socket(ip, 7777);
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.write(password + "\n");
        if (!in.readLine().equals("yes")) {
            throw new IncorrectData();
        }
        new Reader().run();
    }

    public void write(String s) {
        try {
            out.write(s + "\n");
            out.flush();
        } catch (IOException ignored) {
        }
    }

    public static class IncorrectData extends Throwable {
    }

    public class Reader extends Thread {
        @Override
        public void run() {
            super.run();
        }
    }
}
