package ui;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ViewerController {
    private Main main;

    public void handleSearchButton() {

    }
    public void setMain(Main main) {
        this.main = main;
    }

    public void handleCancelButton(ActionEvent actionEvent) throws IOException {
        main.showLoginPage();
    }
}
