module com.vendaingressos.problema3_gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.vendaingressos.problema3_gui to javafx.fxml;
    exports com.vendaingressos.problema3_gui;
}