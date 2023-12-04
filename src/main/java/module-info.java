 module com.example.javafxloginvideo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
     requires mysql.connector.j;

     opens com.example.javafxloginvideo to javafx.fxml;
    exports com.example.javafxloginvideo;
}