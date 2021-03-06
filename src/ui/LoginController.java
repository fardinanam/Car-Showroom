package ui;

import client.ClientManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private Button loginButton;

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
    @FXML
    public void handleViewerButton(ActionEvent actionEvent) {
        try {
            main.showViewAndManageCarsPage("Viewer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Invokes to change scene to Manufacturer screen
     */
    @FXML
    public void handleManufacturerButton(ActionEvent actionEvent) {
        loginOptions.setVisible(true);
        loginButton.setDefaultButton(true);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Shows login form in the login screen
     */
    @FXML
    public void handleLoginButton(ActionEvent actionEvent) {
        String un = username.getText();
        String pass = password.getText();
        String request = "LIN," + un + "," + pass;

        if(un == null || pass == null || un.equals("") || pass.equals("")) {
            main.showAlertForInvalidLogin();
        } else {
            ClientManager.getInstance().sendRequest(request);
        }
        username.setText("");
        password.setText("");
    }

    /**
     * Hides login form
     */
    @FXML
    public void handleCancelButton(ActionEvent actionEvent) {
        loginOptions.setVisible(false);
    }
}
