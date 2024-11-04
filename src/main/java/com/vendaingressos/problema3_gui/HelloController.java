package com.vendaingressos.problema3_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class HelloController {

    // FXML fields for the UI elements
    @FXML
    private Label masterLabel;

    @FXML
    private Label viewLabel;

    @FXML
    private Label detailsLabel;

    @FXML
    private Label leftStatusLabel;

    @FXML
    private Label rightStatusLabel;

    @FXML
    private MenuItem newMenuItem;

    @FXML
    private MenuItem openMenuItem;

    // Methods to handle actions (for example, on menu item clicks)
    @FXML
    public void handleNewAction() {
        System.out.println("New menu item clicked");
    }

    @FXML
    public void handleOpenAction() {
        System.out.println("Open menu item clicked");
    }

    @FXML
    public void handleCloseAction() {
        System.out.println("Close menu item clicked");
    }

    // You can add additional methods to handle other actions
}
