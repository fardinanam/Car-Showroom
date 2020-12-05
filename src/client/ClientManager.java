package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManager extends Thread {
    private static ClientManager instance;
    private static final int PORT = 12121;

    private Socket socket;
    private BufferedReader inputFromServer;
    private PrintWriter requestToServer;

    private ClientManager() {
        try {
            socket = new Socket("localhost", PORT);
            inputFromServer = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            requestToServer = new PrintWriter(
                    socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String serverResponse = inputFromServer.readLine();
                System.out.println("Server message: " + serverResponse);
                // TODO: Handle all server responses
                if(serverResponse.equals("exit")) {
                    inputFromServer.close();
                    requestToServer.close();
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ClientManager getInstance() {
        if(instance == null) {
            instance = new ClientManager();
        }
        return instance;
    }

    public void sendRequest(String request) {
        requestToServer.println(request);
    }
}
