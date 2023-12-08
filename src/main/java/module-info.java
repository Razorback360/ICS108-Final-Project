module com.uni.finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.uni.finalproject to javafx.fxml;
    exports com.uni.finalproject;
}