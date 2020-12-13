package client;

import data.Car;
import data.CarObservableList;
import javafx.application.Platform;
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

    // TODO: Handle all server responses
    private void handleServerResponse(String serverResponse) throws IOException {
        String code = serverResponse.substring(0, 3);
        String data = serverResponse.substring(4);
        if(code.equals("LIN")) {
            handleLogin(serverResponse);
        } else if (code.equals("car")) {
            handleAddCar(data);
        } else if(code.equals("DLT")) {
            handleDeleteCar(data);
        }
    }

    /**
     * @param serverResponse contains comma separated information where first word = LIN
     * second word contains login approval and third word is the username of the user
     */
    private void handleLogin(String serverResponse) {
        String[] responses = serverResponse.split(",");
        if(responses[1].equals("login successful")) {
            String username = responses[2];
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        main.showViewAndManageCarsPage(username);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    main.showAlertForInvalidLogin();
                }
            });
        }
    }

    private void handleAddCar(String carInfoString) {
        String[] carInfo = carInfoString.split(",");
        String colors = carInfo[2] + "," + carInfo[3] + "," + carInfo[4];
        Car car = new Car(carInfo[0], carInfo[1], colors,
                carInfo[5], carInfo[6], carInfo[7], carInfo[8]);
        car.setMain(main);
//        System.out.println(car);
        CarObservableList.getInstance().addCar(car);
    }

    private void handleDeleteCar(String reg) {
        CarObservableList.getInstance().deleteCar(reg);
    }

    public static ClientManager getInstance() {
        if (instance == null) {
            instance = new ClientManager();
        }
        return instance;
    }

    public void sendRequest(String request) {
        String code = request.substring(0, 3);
        if(code.equals("DLT")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if(main.showAlertForConfirmation("Delete")) {
                        requestToServer.println(request);
                    }
                }
            });
        } else if(code.equals("EDT")) {
            String carInfo = request.substring(4);
            requestToServer.println("DLT," + carInfo.split(",")[0]);
            requestToServer.println("ADD," + carInfo);
        } else {
            requestToServer.println(request);
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
