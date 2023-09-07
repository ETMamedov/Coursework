package org.example;

import java.io.*;
import java.net.Socket;


public class ServerListener extends Thread {
    private Socket clientSocket;
    public BufferedReader in;
    private BufferedWriter out;

    public ServerListener(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        start();
        Logger.logger("New connection accepted" + clientSocket.getPort());
    }

    @Override
    public void run() {
        String text;
        try {
            while (true) {
                text = in.readLine();
                out.write(text + "\n");
                if (text.equals("exit")) break;
                for (ServerListener l : Main.serverList) {
                    if (l.equals(this)) {
                        continue;
                    } else
                        l.send(text);
                }
            }
        } catch (IOException e) {
            System.out.println("Пользователь вышел из чата");
            e.printStackTrace();
        }
    }

    public void send(String message) throws IOException {
        out.write(message + "\n");
        out.flush();
    }
}
