module com.nico.cefet.academia {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;


    opens com.nico.cefet.academia to javafx.fxml;
    exports com.nico.cefet.academia;
    exports com.nico.cefet.academia.controller;
    opens com.nico.cefet.academia.controller to javafx.fxml;
}