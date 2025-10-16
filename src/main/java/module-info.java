module VietNamRailwayInterface {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;

    exports fourcore.Entity;
    exports fourcore.GiaoDien;

    opens fourcore.GiaoDien to javafx.fxml;
}
