package ui;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ManufacturerController {
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }
    // TODO
    public void handleAddCar(ActionEvent actionEvent) {
    }
    public void handleLogoutButton(ActionEvent actionEvent) {
        try {
            main.showLoginPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // TODO
    public void handleSearchButton(ActionEvent actionEvent) {
    }
}
