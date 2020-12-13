package data;

import client.ClientManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import ui.Main;


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

    private Main main;

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

        deleteButton.setOnAction(e -> {
            ClientManager.getInstance().sendRequest("DLT," + this.reg.get());
        });

        editButton.setOnAction(e -> {
            main.showEditDialog(this);
        });

        buyButton.setOnAction(e -> {
            if(main.showAlertForConfirmation("Buy " + getReg())) {
                ClientManager.getInstance().sendRequest("BUY," + this);
            }
        });
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getReg() {
        return reg.get();
    }

    public String getYear() {
        return year.get();
    }

    public String getColors() {
        return colors.get();
    }

    public String getMake() {
        return make.get();
    }

    public String getModel() {
        return model.get();
    }

    public String getPrice() {
        return price.get();
    }

    public String getQuantity() {
        return quantity.get();
    }

    public Button getEditButton() {
        return editButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getBuyButton() {
        return buyButton;
    }

    @Override
    public String toString() {
        return reg.get() + "," + year.get() + "," + colors.get()
                + "," + make.get() + "," + model.get()
                + "," + price.get() + "," + quantity.get();
    }
}
