module com.example._20gameengine {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example._20gameengine to javafx.fxml;
    exports com.example._20gameengine;
}