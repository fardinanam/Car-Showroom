package ui;

import data.Car;
import data.CarObservableList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Optional;


public class ViewAndManageCarsController {
    @FXML
    private StackPane viewerContainer;
    @FXML
    private Button viewAllButton;
    @FXML
    private TableView<Car> tableView;
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
        // Showing initial prompt text in search bar
        handleSearchOptions(null);
    }
    public void setMain(Main main) {
        this.main = main;
    }

    public void handleAddCar(ActionEvent actionEvent) {
        showAddEditDialog();
    }

    @FXML
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

    @FXML
    public void handleSearchButton(ActionEvent actionEvent) {
        ObservableList<Car> list = FXCollections.observableArrayList();
        String search = searchText.getText();
        if(searchOptions.getValue().equals("Registration No.")) {
            for(Car car : CarObservableList.getInstance().getCarList()) {
                if(car.getReg().equalsIgnoreCase(search)) {
                    list.add(car);
                }
            }
        } else if(!search.contains(",")) {
            for(Car car : CarObservableList.getInstance().getCarList()) {
                if(car.getMake().equalsIgnoreCase(search) ||
                        car.getModel().equalsIgnoreCase(search)) {
                    list.add(car);
                }
            }
        } else if(search.split(",").length < 2) {
            // do nothing
        } else if(search.split(",")[1].equalsIgnoreCase("any")) {
            for(Car car : CarObservableList.getInstance().getCarList()) {
                if(car.getMake().equalsIgnoreCase(search.split(",")[0])) {
                    list.add(car);
                }
            }
        } else {
            String[] makeModel = search.split(",");
            for(Car car : CarObservableList.getInstance().getCarList()) {
                if(car.getMake().equalsIgnoreCase(makeModel[0])
                    && car.getModel().equalsIgnoreCase(makeModel[1])) {
                    list.add(car);
                }
            }
        }
        tableView.setItems(list);
        if(viewAllButton.isDisabled()) {
            viewAllButton.setDisable(false);
        }
    }

    @FXML
    public void handleViewAllButton() {
        tableView.setItems(CarObservableList.getInstance().getCarList());
        viewAllButton.setDisable(true);

    }

    @FXML
    public void handleSearchOptions(ActionEvent actionEvent) {
        searchText.setPromptText("Search by " + searchOptions.getValue());
    }

    /**
     * Loading the dialog when add or edit button is pressed
     */
    public void showAddEditDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(viewerContainer.getScene().getWindow());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addEditDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load dialog");
            e.printStackTrace();
            return;
        }

        // Adding buttons
//        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        // Adding styleSheet
        dialog.getDialogPane().getScene().getStylesheets().add(
                getClass().getResource("uiStyles.css").toExternalForm());
        dialog.showAndWait();
       /* Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            AddEditDialogController dialogController = loader.getController();
            dialogController.validateInfo();
            System.out.println("Okay pressed");
        } else {
            System.out.println("Cancel pressed");
        }*/
    }

    private void setTableView() {
        TableColumn<Car, String> regCol = new TableColumn<>("Registration No.");
        regCol.setMinWidth(120);
        regCol.setCellValueFactory(new PropertyValueFactory<>("reg"));

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
        quantityCol.setMinWidth(90);
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

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
        if(!viewAllButton.isDisabled()) {
            viewAllButton.setDisable(true);
        }
    }
}