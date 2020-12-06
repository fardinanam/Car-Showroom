package ui;

import data.Car;
import data.CarObservableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ViewAndManageCarsController {
    @FXML
    private TableView tableView;

    @FXML
    private Button addCarButton;
    @FXML
    private ComboBox searchOptions;
    @FXML
    private TextField searchText;
    private String username;
    private Main main;

    public void init(String username) {
        this.username = username;
        if(username.equals("Viewer")) {
            addCarButton.setManaged(false);
            addCarButton.managedProperty().bind(addCarButton.visibleProperty());
            addCarButton.setVisible(false);
        }
        handleSearchOptions(null);
        setTableView();
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

    private void setTableView() {
        TableColumn<Car, String> regNoCol = new TableColumn<>("Registration No.");
        regNoCol.setMinWidth(120);
        regNoCol.setCellValueFactory(new PropertyValueFactory<>("reg"));

        TableColumn<Car, String> yearCol = new TableColumn<>("Year");
        yearCol.setMinWidth(90);
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Car, String> colorsCol = new TableColumn<>("Colors");
        colorsCol.setMinWidth(90);
        colorsCol.setCellValueFactory(new PropertyValueFactory<>("colors"));

        TableColumn<Car, String> makeCol = new TableColumn<>("Make");
        makeCol.setMinWidth(90);
        makeCol.setCellValueFactory(new PropertyValueFactory<>("make"));

        TableColumn<Car, String> modelCol = new TableColumn<>("Model");
        modelCol.setMinWidth(90);
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Car, String> priceCol = new TableColumn<>("Price");
        priceCol.setMinWidth(90);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Car, String> quantityCol = new TableColumn<>("Quantity");
        priceCol.setMinWidth(90);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableView.getColumns().addAll(regNoCol, yearCol, colorsCol,
                makeCol, modelCol, priceCol, quantityCol);
        if(!username.equals("Viewer")) {
            TableColumn<Car, String> editButtonCol = new TableColumn<>("Edit");
            editButtonCol.setMinWidth(90);
            editButtonCol.setCellValueFactory(new PropertyValueFactory<>("editButton"));

            TableColumn<Car, String> deleteButtonCol = new TableColumn<>("Delete");
            deleteButtonCol.setMinWidth(90);
            deleteButtonCol.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

            tableView.getColumns().addAll(editButtonCol, deleteButtonCol);
        } else {
            TableColumn<Car, String> buyButtonCol = new TableColumn<>("Buy");
            buyButtonCol.setMinWidth(90);
            buyButtonCol.setCellValueFactory(new PropertyValueFactory<>("buyButton"));
            tableView.getColumns().add(buyButtonCol);
        }
        tableView.setEditable(true);
        tableView.setItems(CarObservableList.getInstance().getCarList());
    }
}