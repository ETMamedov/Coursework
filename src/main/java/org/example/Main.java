package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Main {
    public static final int port = 8089;
    public static LinkedList<ServerListener> serverList = new LinkedList<>();

    public static void main(String[] args) throws IOException {


        try (
                FileWriter writer = new FileWriter("src/main/resources/Settings.txt", false)) {
            writer.append(Integer.valueOf(port).toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ServerSocket serverSocket = new ServerSocket(port);


        while (true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.println("Server started");
            try {
                serverList.add(new ServerListener(clientSocket));
                out.println("Добро пожаловать в чат!");
            } catch (IOException e) {
                clientSocket.close();
            }
        }
    }
}