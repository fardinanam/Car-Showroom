package data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class Car {
    private final SimpleStringProperty reg;
    private final SimpleStringProperty year;
    private final SimpleStringProperty colors;
    private final SimpleStringProperty make;
    private final SimpleStringProperty model;
    private final SimpleStringProperty price;
    private final SimpleStringProperty quantity;
    private final Button editButton;
    private final Button deleteButton;
    private final Button buyButton;

    public Car(String reg, String year, String colors, String make, String model, String price, String quantity) {
        this.reg = new SimpleStringProperty(reg);
        this.year = new SimpleStringProperty(year);
        this.colors = new SimpleStringProperty(colors);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.price = new SimpleStringProperty(price);
        this.quantity = new SimpleStringProperty(quantity);
        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        buyButton = new Button("Buy");

        // TODO: make setOnAction for all the buttons
    }

    @Override
    public String toString() {
        StringBuilder carInfo = new StringBuilder();
        carInfo.append("Registration Number: " + reg + "\n");
        carInfo.append("Year Made: " + year + "\n");
        carInfo.append("Colors: ");
        carInfo.append(colors + "\n");
        carInfo.append("Make: " + make + "\n");
        carInfo.append("Model: " + model + "\n");
        carInfo.append("Price: " + price + "\n");

        return carInfo.toString();
    }

    public final String getReg() {
        return reg.get();
    }

    public final String getYear() {
        return year.get();
    }

    public final String getColors() {
        return colors.get();
    }

    public final SimpleStringProperty regProperty() {
        return reg;
    }

    public final SimpleStringProperty yearProperty() {
        return year;
    }

    public final SimpleStringProperty colorsProperty() {
        return colors;
    }

    public final SimpleStringProperty makeProperty() {
        return make;
    }

    public final SimpleStringProperty modelProperty() {
        return model;
    }

    public final SimpleStringProperty priceProperty() {
        return price;
    }

    public final SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public final String getMake() {
        return make.get();
    }

    public final String getModel() {
        return model.get();
    }

    public final String getPrice() {
        return price.get();
    }

    public final String getQuantity() {
        return quantity.get();
    }

    // TODO: allow edit options
}
