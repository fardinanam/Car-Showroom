package ui;

import client.ClientManager;
import data.Car;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditDialogController implements Initializable {
    @FXML
    private Button addCarButton;
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
        addCarButton.setDefaultButton(true);
    }

    @FXML
    public void handleAddCarButton(ActionEvent actionEvent) {
        if(validateInfo()) {
            String reg = regText.getText();
            String year = yearText.getText();
            String make = makeText.getText();
            String model = modelText.getText();
            String price = priceText.getText();
            String quantity = quantityText.getText();
            String colors = color1Text.getText() + "," + color2Text.getText() + "," +
                    color3Text.getText();

            // Sending Request to the server to add new Car
            Car newCar = new Car(reg, year, colors, make, model, price,quantity);
            ClientManager.getInstance().sendRequest("ADD," + newCar);

            // Clearing all the fields to add another
            regText.clear();
            yearText.clear();
            makeText.clear();
            modelText.clear();
            priceText.clear();
            quantityText.clear();
            color1Text.clear();
            color2Text.clear();
            color3Text.clear();
            addCarButton.setText("Add Another");
            addCarButton.setMinWidth(120);
        }
    }

    private enum AlertTypes {EMPTYFIELDS, INVALIDNUMBER}

    public boolean validateInfo() {
        if(regText.getText().equals("") || yearText.getText().equals("") || makeText.getText().equals("") || color1Text.getText().equals("") ||
                modelText.getText().equals("") || priceText.getText().equals("") || quantityText.getText().equals("")) {
            showAlert(AlertTypes.EMPTYFIELDS);
            return false;
        } else {
            try {
                Integer.parseInt(yearText.getText());
                Integer.parseInt(priceText.getText());
                Integer.parseInt(quantityText.getText());
            } catch (NumberFormatException e) {
                showAlert(AlertTypes.INVALIDNUMBER);
                return false;
            }
        }
        return true;
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
