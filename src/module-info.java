module Car.Showroom {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    opens ui to javafx.graphics, javafx.fxml;
}