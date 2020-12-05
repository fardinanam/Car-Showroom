package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class ManufacturerController {
    @FXML
    private ComboBox searchOptions;
    @FXML
    private TextField searchText;
    private Main main;

    public void init() {
        handleSearchOptions(null);
    }
    public void setMain(Main main) {
        this.main = main;
    }
    // TODO
    public void handleAddCar(ActionEvent actionEvent) {
    }
    public void handleLogoutButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Do you really want to logout?");

        // Adding stylesheet to Alert box to add my own styles
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("uiStyles.css").toExternalForm());
        dialogPane.getStyleClass().add("myAlert");

        alert.showAndWait().ifPresent(response -> {
            if(response == ButtonType.OK) {
                try {
                    main.showLoginPage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    // TODO
    public void handleSearchButton(ActionEvent actionEvent) {
    }

    public void handleSearchOptions(ActionEvent actionEvent) {
        searchText.setPromptText("Search by " + searchOptions.getValue());
    }
}
