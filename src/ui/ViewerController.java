package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ViewerController {
    @FXML
    private ComboBox searchOptions;
    @FXML
    private TextField searchText;

    private Main main;

    public void init() {
        handleSearchOptions(null);
    }

    public void handleSearchButton() {

    }
    public void setMain(Main main) {
        this.main = main;
    }

    public void handleCancelButton(ActionEvent actionEvent) throws IOException {
        main.showLoginPage();
    }

    public void handleSearchOptions(ActionEvent actionEvent) {
        searchText.setPromptText("Search by " + searchOptions.getValue());
    }
}
