package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CarObservableList {
    private static CarObservableList instance;
    private ObservableList<Car> carList;

    private CarObservableList() {
        carList = FXCollections.observableArrayList();
    }

    public ObservableList<Car> getCarList() {
        return carList;
    }

    public static CarObservableList getInstance() {
        if(instance == null) {
            instance = new CarObservableList();
        }
        return instance;
    }
}
