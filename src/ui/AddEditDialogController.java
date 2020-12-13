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

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditDialogController implements Initializable {
    @FXML
    private Button addCar;
    @FXML
    private Button editCar;
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
        // When it opens as addCar dialog, edit button has to be hidden
        editCar.setVisible(false);

        yearText.setPromptText("Number Field");
        priceText.setPromptText("Number Field");
        quantityText.setPromptText("Number Field");
        addCar.setDefaultButton(true);
    }

    private Car makeCar() {
        String reg = regText.getText();
        String year = yearText.getText();
        String make = makeText.getText();
        String model = modelText.getText();
        String price = priceText.getText();
        String quantity = quantityText.getText();
        String colors = color1Text.getText() + "," + color2Text.getText() + "," +
                color3Text.getText();

        return new Car(reg, year, colors, make, model, price,quantity);
    }

    @FXML
    public void handleAddCarButton(ActionEvent actionEvent) {
        if(validateInfo()) {
            // Sending Request to the server to add new Car
            ClientManager.getInstance().sendRequest("ADD," + makeCar());
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
            addCar.setText("Add Another");
            addCar.setMinWidth(120);
        }
    }

    @FXML
    private void handleEditCarButton(ActionEvent event) {
        if(validateInfo()) {
            ClientManager.getInstance().sendRequest("EDT," + makeCar());
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
        alert.setTitle("Invalid Input");

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

    /**
     * Fills the fields of addEditDialog with the car info that is to be edited
     */
    public void setForEdit(Car car) {
        // Hiding addCar button
        addCar.setDefaultButton(false);
        addCar.setManaged(false);
        addCar.managedProperty().bind(addCar.visibleProperty());
        addCar.setVisible(false);
        // Edit button is initialized to hidden, So making it visible
        editCar.setVisible(true);
        editCar.setDefaultButton(true);

        regText.setText(car.getReg());
        yearText.setText(car.getYear());
        makeText.setText(car.getMake());
        modelText.setText(car.getModel());
        priceText.setText(car.getPrice());
        quantityText.setText(car.getQuantity());
        String[] colors = car.getColors().split(",");
        color1Text.setText(colors[0]);
        try {
            color2Text.setText(colors[1]);
            color2Text.setText(colors[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Nothing happens
        }
    }
}
