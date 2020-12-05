package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManager extends Thread {
    private static final int PORT = 12121;
    private Socket socket;
    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", PORT)) {
            BufferedReader inputFromServer = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter requestToServer = new PrintWriter(
                    socket.getOutputStream(), true);
            while (true) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
