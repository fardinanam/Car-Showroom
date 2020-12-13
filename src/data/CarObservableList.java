package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

public class CarObservableList {
    private static CarObservableList instance;
    private final ObservableList<Car> carList;

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

    public void addCar(Car car) {
        carList.add(car);
    }

    /**
     * Deletes the car from the observableList
     * @param reg Registration Number of the car to delete
     */
    public void deleteCar(String reg) {
        carList.removeIf(car -> reg.equalsIgnoreCase(car.getReg()));
    }
}
