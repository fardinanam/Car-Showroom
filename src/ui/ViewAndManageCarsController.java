package ui;

import data.Car;
import data.CarObservableList;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewAndManageCarsController {
    @FXML
    private TableView<Car> tableView;
    private ObservableList<Car> data;
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
        setTableView();
        if(username.equals("Viewer")) {
            addCarButton.setManaged(false);
            addCarButton.managedProperty().bind(addCarButton.visibleProperty());
            addCarButton.setVisible(false);
        }
        // TODO: Call search options properly
        handleSearchOptions(null);
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
    // TODO: implement search option
    public void handleSearchButton(ActionEvent actionEvent) {
        /* CAN BE IMPLEMENTED THIS WAY
        Car car = CarObservableList.getInstance().getCarList().get(2);
        ObservableList<Car> list = FXCollections.observableArrayList();
        list.add(car);
        tableView.setItems(list);
        */
    }

    public void handleSearchOptions(ActionEvent actionEvent) {
        searchText.setPromptText("Search by " + searchOptions.getValue());
    }

    private void setTableView() {
        TableColumn<Car, String> regCol = new TableColumn<>("Registration No.");
        regCol.setMinWidth(120);
        regCol.setCellValueFactory(new PropertyValueFactory<>("reg"));

        /*regCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Car, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Car, String> c) {
                if(c.getValue() != null && c.getValue().getReg() != null) {
                    return new SimpleStringProperty(c.getValue().getReg());
                } else {
                    return new SimpleStringProperty("NULL");
                }
            }
        });*/
        TableColumn<Car, String> yearCol = new TableColumn<>("Year");
        yearCol.setMinWidth(90);
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        /*yearCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Car, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Car, String> c) {
                if(c.getValue() != null && c.getValue().getYear() != null) {
                    return new SimpleStringProperty(c.getValue().getYear());
                } else {
                    return new SimpleStringProperty("NULL");
                }
            }
        });*/

        TableColumn<Car, String> colorsCol = new TableColumn<>("Colors");
        colorsCol.setMinWidth(90);
        colorsCol.setCellValueFactory(new PropertyValueFactory<>("colors"));

        /*colorsCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Car, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Car, String> c) {
                if(c.getValue() != null && c.getValue().getColors() != null) {
                    return new SimpleStringProperty(c.getValue().getColors());
                } else {
                    return new SimpleStringProperty("NULL");
                }
            }
        });*/

        TableColumn<Car, String> makeCol = new TableColumn<>("Make");
        makeCol.setMinWidth(90);
        makeCol.setCellValueFactory(new PropertyValueFactory<>("make"));

        /*makeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Car, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Car, String> c) {
                if(c.getValue() != null && c.getValue().getMake() != null) {
                    return new SimpleStringProperty(c.getValue().getMake());
                } else {
                    return new SimpleStringProperty("NULL");
                }
            }
        });*/

        TableColumn<Car, String> modelCol = new TableColumn<>("Model");
        modelCol.setMinWidth(90);
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));

        /*modelCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Car, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Car, String> c) {
                if(c.getValue() != null && c.getValue().getModel() != null) {
                    return new SimpleStringProperty(c.getValue().getModel());
                } else {
                    return new SimpleStringProperty("NULL");
                }
            }
        });*/

        TableColumn<Car, String> priceCol = new TableColumn<>("Price");
        priceCol.setMinWidth(90);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        /*priceCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Car, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Car, String> c) {
                if(c.getValue() != null && c.getValue().getPrice() != null) {
                    return new SimpleStringProperty(c.getValue().getPrice());
                } else {
                    return new SimpleStringProperty("NULL");
                }
            }
        });*/

        TableColumn<Car, String> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setMinWidth(90);
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        /*quantityCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Car, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Car, String> c) {
                if(c.getValue() != null && c.getValue().getQuantity() != null) {
                    return new SimpleStringProperty(c.getValue().getQuantity());
                } else {
                    return new SimpleStringProperty("NULL");
                }
            }
        });*/

        tableView.getColumns().addAll(regCol, yearCol, colorsCol,
                makeCol, modelCol, priceCol, quantityCol);
        if(!username.equals("Viewer")) {
            TableColumn<Car, Button> editButtonCol = new TableColumn<>("Edit");
            editButtonCol.setMinWidth(90);
            editButtonCol.setCellValueFactory(new PropertyValueFactory<>("editButton"));

            TableColumn<Car, Button> deleteButtonCol = new TableColumn<>("Delete");
            deleteButtonCol.setMinWidth(90);
            deleteButtonCol.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

            tableView.getColumns().addAll(editButtonCol, deleteButtonCol);
        } else {
            TableColumn<Car, Button> buyButtonCol = new TableColumn<>("Buy");
            buyButtonCol.setMinWidth(90);
            buyButtonCol.setCellValueFactory(new PropertyValueFactory<>("buyButton"));

            tableView.getColumns().add(buyButtonCol);
        }
        tableView.setEditable(true);
        tableView.setItems(CarObservableList.getInstance().getCarList());
        /*data = FXCollections.observableArrayList(
                new Car("1234", "2020", "RED", "BMW", "M16", "20000", "1")
        );
        tableView.setItems(data);*/
    }
}