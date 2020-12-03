package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoginController {
    private Main main;

    @FXML
    private AnchorPane loginOptions;
    @FXML
    private VBox loginVbox;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void init() {
        // The following three lines help the vbox to align it's children to center
        // when loginOptions are not visible
        loginOptions.setManaged(false);
        loginOptions.managedProperty().bind(loginOptions.visibleProperty());
        loginOptions.setVisible(false);
    }

    /**
     * Invokes to change scene to viewers screen
     */
    public void handleViewerButton(ActionEvent actionEvent) {
        try {
            main.showViewersPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleManufacturerButton(ActionEvent actionEvent) {
        loginOptions.setVisible(true);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void handleLoginButton(ActionEvent actionEvent) {
        String un = username.getText();
        String pass = password.getText();

        if(un.equals("admin") && pass.equals("123")) {
            try {
                main.showManufacturersPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("The username and password did not match.");
            alert.showAndWait();
        }
    }

    public void handleCancelButton(ActionEvent actionEvent) {
        loginOptions.setVisible(false);
    }
}
