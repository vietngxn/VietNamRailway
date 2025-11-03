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
  	requires java.desktop;
	requires org.apache.poi.ooxml;
	requires org.apache.poi.poi;
	requires org.apache.xmlbeans;
    exports fourcore.Entity;
      exports fourcore.util;
    exports fourcore.GiaoDien;
    opens fourcore.Entity to javafx.base;
    opens fourcore.GiaoDien to javafx.fxml;
}




