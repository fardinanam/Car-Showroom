package client;

import ui.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManager extends Thread {
    private static ClientManager instance;
    private static final int PORT = 12121;
    private Main main;

    private Socket socket;
    private BufferedReader responseFromServer;
    private PrintWriter requestToServer;

    private ClientManager() {
        try {
            socket = new Socket("localhost", PORT);
            responseFromServer = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            requestToServer = new PrintWriter(
                    socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        start();
    }

    @Override
    public void run() {
        try {
            /* Any server response is processed from here */
            while (true) {
                String serverResponse = responseFromServer.readLine();
                System.out.println("Server message: " + serverResponse);
                // TODO: Handle all server responses
                if(serverResponse.equals("exit")) {
                    break;
                }
                handleServerResponse(serverResponse);
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }

    private void handleServerResponse(String serverResponse) throws IOException {
        if(serverResponse.contains("login successful")) {
            String username = serverResponse.split(",")[1];
            main.showViewAndManageCarsPage(username);
        }
    }

    public static ClientManager getInstance() {
        if (instance == null) {
            instance = new ClientManager();
        }
        return instance;
    }

    public void sendRequest(String request) {
        requestToServer.println(request);
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
