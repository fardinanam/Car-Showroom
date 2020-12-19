package ui;

import client.ClientManager;
import data.Car;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditDialogController implements Initializable {
    @FXML
    private Label topLabel;
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

    private enum AlertTypes {EMPTYFIELDS, INVALIDNUMBER}

    /**
     * Initializes the addEdit dialog with all necessary information for adding a car
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        yearText.setPromptText("Number Field");
        priceText.setPromptText("Number Field");
        quantityText.setPromptText("Number Field");
    }

    /**
     * Creates a data.Car object from all the information taken from the text fields
     * of the dialog box
     */
    public Car makeCar() {
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

    /**
     * Clears all the fields and changes the text of the button to add another
     * if provided information are valid.
     */
   /* @FXML
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
    }*/

    /**
     * Clears all the fields
     */
    public void clear() {
        regText.clear();
        yearText.clear();
        makeText.clear();
        modelText.clear();
        priceText.clear();
        quantityText.clear();
        color1Text.clear();
        color2Text.clear();
        color3Text.clear();
    }

    /**
     * Requests the server to edit a car if provided data are valid
     */
    @FXML
    private void handleEditCarButton(ActionEvent event) {
        if(validateInfo()) {
            ClientManager.getInstance().sendRequest("EDT," + makeCar());
        }
    }

    /**
     * Checks if all the information that are provided are valid or not.
     * If not, it invokes an alert.
     */
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

    /**
     * Alert box to show the kind of invalid data input
     */
    public void showAlert(AlertTypes alertType) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");

        if(alertType == AlertTypes.EMPTYFIELDS) {
            alert.setHeaderText("Empty Field/s");
            alert.setContentText("Any field except last two color fields can not be empty");
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
     * Fills the fields of addEditDialog with the car info that is to be edited.
     * Hides the Add Button.
     */
    public void setForEdit(Car car) {
        topLabel.setText("Edit Car Information");
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
