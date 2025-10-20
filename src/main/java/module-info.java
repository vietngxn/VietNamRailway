module VietNamRailwayInterface {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    requires java.sql;
    exports fourcore.Entity;
    exports fourcore.GiaoDien;

    opens fourcore.GiaoDien to javafx.fxml;
}
