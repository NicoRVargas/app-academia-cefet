module com.nico.cefet.academia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.nico.cefet.academia to javafx.fxml;
    exports com.nico.cefet.academia;
}