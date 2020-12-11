package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditDialogController implements Initializable {
    @FXML
    private TextField regText;
    @FXML
    private TextField yearText;
    @FXML
    private TextField makeText;
    @FXML
    private TextField modelText;
    @FXML
    private TextField priceText;
    @FXML
    private TextField quantityText;
    @FXML
    private TextField color1Text;
    @FXML
    private TextField color2Text;
    @FXML
    private TextField color3Text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        yearText.setPromptText("Number Field");
        priceText.setPromptText("Number Field");
        quantityText.setPromptText("Number Field");
    }

    private static enum AlertTypes {EMPTYFIELDS, INVALIDNUMBER}

    public void validateInfo() {
        if(regText.getText().equals("") || yearText.getText().equals("") || makeText.getText().equals("") || color1Text.getText().equals("") ||
                modelText.getText().equals("") || priceText.getText().equals("") || quantityText.getText().equals("")) {
            showAlert(AlertTypes.EMPTYFIELDS);
        } else {
            try {
                Integer.parseInt(yearText.getText());
                Integer.parseInt(priceText.getText());
                Integer.parseInt(quantityText.getText());
            } catch (NumberFormatException e) {
                showAlert(AlertTypes.INVALIDNUMBER);
            }
        }
    }

    public void showAlert(AlertTypes alertType) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");

        if(alertType == AlertTypes.EMPTYFIELDS) {
            alert.setHeaderText("Empty Field/s");
            alert.setContentText("All fields except last two color fields can not be empty");
        } else if(alertType == AlertTypes.INVALIDNUMBER) {
            alert.setHeaderText("Text in Number fields");
            alert.setContentText("Year, price and quantity have to be Integers");
        }

        // Adding uiStyles.css Alert box to add my own styles
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("uiStyles.css").toExternalForm());
        dialogPane.getStyleClass().add("myAlert");

        alert.showAndWait();
    }
}
