module com.nico.cefet.cardapio {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.nico.cefet.cardapio to javafx.fxml;
    exports com.nico.cefet.cardapio;
}