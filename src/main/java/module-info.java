module com.example.a7 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.a7for to javafx.fxml;
    exports com.example.a7for;
    opens model to javafx.base;
    opens statements to javafx.base;
}