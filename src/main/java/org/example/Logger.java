package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    static String time = DateTimeFormatter.ofPattern("HH:mm:ss")
            .format(LocalDateTime.now());

        public static void logger(String text){
        try (FileWriter writer = new FileWriter("/Users/eugen/Documents/Games/file.log", true)) {
            writer.write(time +" "+text+"\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
