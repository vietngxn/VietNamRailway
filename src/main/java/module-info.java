module VietNamRailwayInterface {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    requires java.sql;

    requires com.google.gson;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires jakarta.mail;
    exports fourcore.GiaoDien;
    opens fourcore.Entity to javafx.base;
    opens fourcore.GiaoDien to javafx.fxml;
}
