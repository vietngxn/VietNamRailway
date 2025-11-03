module VietNamRailwayInterface {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    requires java.sql;

    requires com.google.gson;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires jakarta.mail;
    requires kernel;
    requires layout;
    requires io;
    requires java.desktop;
    exports fourcore.Entity;
    exports fourcore.GiaoDien;
    opens fourcore.Entity to javafx.base, javafx.graphics; 
    opens fourcore.GiaoDien to javafx.fxml, javafx.graphics;
}
