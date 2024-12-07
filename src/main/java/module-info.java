module com.vendaingressos.problema3_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;

    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens com.vendaingressos.problema3_gui to javafx.fxml;
    opens com.vendaingressos.problema3_gui.models to com.google.gson;
    exports com.vendaingressos.problema3_gui;
    exports com.vendaingressos.problema3_gui.repositories;
    exports com.vendaingressos.problema3_gui.models;
    exports com.vendaingressos.problema3_gui.Enum;
    exports com.vendaingressos.problema3_gui.abstracts;
    exports com.vendaingressos.problema3_gui.controllers;
    exports com.vendaingressos.problema3_gui.interfaces;
    opens com.vendaingressos.problema3_gui.controllers to javafx.fxml;
    exports com.vendaingressos.problema3_gui.GUI;
    opens com.vendaingressos.problema3_gui.GUI to javafx.fxml;
}