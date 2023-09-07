import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class UTest {

    int port = 8089;

    @Test
    public void clientTest() throws IOException {

        Socket clientSocket = new Socket("127.0.0.1", port);

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String result = in.readLine();

        Assertions.assertEquals("Добро пожаловать в чат!", result);
    }

}
