package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        showLoginPage();
    }
    /**
     * Changes the scene to Login page
     */
    void showLoginPage() throws IOException {
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
        window.setScene(new Scene(root, 800, 600));
        window.show();
    }

    void showViewersPage() throws IOException {
        // Loading viewers.fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewer.fxml"));
        Parent root = loader.load();

        // Loading controller of viewer.fxml and setting the Main to it
        ViewerController controller = loader.getController();
        controller.setMain(this);

        // Showing login page in the window
        window.setTitle("Viewer");
        window.setScene(new Scene(root, 800, 600));
        window.show();
    }

    void showManufacturersPage() throws IOException {
        // Loading manufacturer.fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("manufacturer.fxml"));
        Parent root = loader.load();

        // Loading controller of manufacturer.fxml and setting the Main to it
        ManufacturerController controller = loader.getController();
        controller.setMain(this);

        // Showing login page in the window
        window.setTitle("Manufacturer");
        window.setScene(new Scene(root, 800, 600));
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
