module VietNamRailwayInterface {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    requires java.sql;

    requires com.google.gson;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires jakarta.mail;
	requires java.desktop;
	requires org.apache.poi.ooxml;
	requires org.apache.poi.poi;
	requires org.apache.xmlbeans;
	
	exports fourcore.Entity;
    exports fourcore.GiaoDien;
    exports fourcore.util;
    
    opens fourcore.Entity to javafx.base, javafx.graphics; 
    opens fourcore.GiaoDien to javafx.fxml, javafx.graphics;
}
