package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public void handleViewerButton(ActionEvent actionEvent) throws IOException {
        main.showViewersPage();
    }

    public void handleManufacturerButton(ActionEvent actionEvent) {
        loginOptions.setVisible(true);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void handleLoginButton(ActionEvent actionEvent) {
    }

    public void handleCancelButton(ActionEvent actionEvent) {
        loginOptions.setVisible(false);
    }
}
