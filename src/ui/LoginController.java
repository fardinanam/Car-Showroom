package ui;

import client.ClientManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
            main.showViewAndManageCarsPage("Viewer");
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

    public void handleLoginButton(ActionEvent actionEvent) throws InterruptedException {
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

    public void handleCancelButton(ActionEvent actionEvent) {
        loginOptions.setVisible(false);
    }
}
