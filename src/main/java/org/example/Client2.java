package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) {

        StringBuilder builder = new StringBuilder();
        Socket clientSocket;

        try (
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Settings.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String numbers = builder.toString();
        int port = Integer.parseInt(numbers);

        Scanner scanner = new Scanner(System.in);
        String host = "127.0.0.1";
        System.out.printf("Введите имя: ");
        String name = scanner.nextLine();

        try {
            clientSocket = new Socket(host, port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            ReadMessage.read(clientSocket);

            while (!clientSocket.isClosed()) {
                System.out.printf(name + " :");
                String text = scanner.nextLine();
                if (text.equals("exit")) {
                    System.out.println("Пользователь " + name + " вышел из чата");
                    clientSocket.shutdownInput();
                    clientSocket.shutdownOutput();
                    clientSocket.close();
                } else {
                    out.println(name + ": " + text);
                    Logger.logger(name + ": " + text);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
