module VietNamRailwayInterface {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    exports fourcore.Entity;
    exports fourcore.GiaoDien;

    opens fourcore.GiaoDien to javafx.fxml;
}
