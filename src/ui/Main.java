package ui;

import client.ClientManager;
import data.Car;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
        window.setScene(new Scene(root, 900, 600));
        window.show();
    }

    /**
     * Changes the scene to viewer's or manufacturer's page depending on the username
     */
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
        window.setScene(new Scene(root, 900, 600));
        window.show();
    }

    /**
     * Shows alert to confirm delete or buy command
     */
    public boolean showAlertForConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(message);
        alert.setHeaderText(message);
        if(message.equals("Delete")) {
            alert.setContentText("Do you really want to delete?");
        } else if(message.split(" ")[0].equals("Buy")) {
            alert.setContentText("Do you really want to " + message + "?");
        }

        // Adding uiStyles.css Alert box to add my own styles
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("uiStyles.css").toExternalForm());
        dialogPane.getStyleClass().add("myAlert");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Opens the addEditDialog and adds the Edit Car button
     * to set the dialog box for editing purpose
     */
    public void showEditDialog(Car car) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(window);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addEditDialog.fxml"));


        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load dialog");
            e.printStackTrace();
            return;
        }

        dialog.setTitle("Edit Car");
        AddEditDialogController controller = loader.getController();
        controller.setForEdit(car);
        // Adding buttons
        ButtonType editButton = new ButtonType("Edit Car", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(editButton, ButtonType.CANCEL);

        // Setting up data validation on edit car button
        Button editButtonInDialog = (Button)dialog.getDialogPane().lookupButton(editButton);
        editButtonInDialog.addEventFilter(ActionEvent.ACTION, event -> {
            if(!controller.validateInfo()) {
                event.consume();
            }
        });
        // Adding styleSheet
        dialog.getDialogPane().getScene().getStylesheets().add(
                getClass().getResource("uiStyles.css").toExternalForm());
        Optional<ButtonType> result = dialog.showAndWait();

        // Adding car
        if(result.isPresent() && result.get() == editButton) {
            // Sending Request to the server to edit new Car
            ClientManager.getInstance().sendRequest("EDT," + controller.makeCar());
        }
    }

    /**
     * Alerts if the login information are not valid
     */
    public void showAlertForInvalidLogin() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password did not match.");

        // Adding uiStyles.css Alert box to add my own styles
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
        getClass().getResource("uiStyles.css").toExternalForm());
        dialogPane.getStyleClass().add("myAlert");

        alert.showAndWait();
    }
    @Override
    public void stop() {
        ClientManager.getInstance().sendRequest("exit");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
