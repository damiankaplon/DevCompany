module pl.damiankaplon.devcompany {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires lombok;

    opens pl.damiankaplon.devcompany to javafx.fxml;
    exports pl.damiankaplon.devcompany;
    exports pl.damiankaplon.devcompany.controller;
    opens pl.damiankaplon.devcompany.controller to javafx.fxml;
}