package ui;

import client.ClientManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception {
        ClientManager.getInstance().setMain(this);
        window = primaryStage;
        showLoginPage();
    }
    /**
     * Changes the scene to Login page
     */
    public void showLoginPage() throws IOException {
        // Loading login.fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading controller of login.fxml and setting the Main to it
        LoginController controller = loader.getController();
        controller.init();
        controller.setMain(this);

        // Showing login page in the window
        window.setTitle("Login");
        window.setScene(new Scene(root, 850, 600));
        window.show();
    }

    public void showViewAndManageCarsPage(String username) throws IOException {
        // Loading viewAndManageCars.fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewAndManageCars.fxml"));
        Parent root = loader.load();

        // Loading controller of viewAndManageCars.fxml and setting the Main to it
        ViewAndManageCarsController controller = loader.getController();
        controller.init(username);
        controller.setMain(this);

        // Showing login page in the window
        window.setTitle(username);
        window.setScene(new Scene(root, 850, 600));
        window.show();
    }

    @Override
    public void stop() {
        ClientManager.getInstance().sendRequest("exit");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
