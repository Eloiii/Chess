module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.testng;


    opens com.example.chess to javafx.fxml;
    exports com.example.chess;
}