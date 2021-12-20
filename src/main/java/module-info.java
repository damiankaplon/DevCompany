module pl.damiankaplon.devcompany {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.naming;
    requires java.sql;
    requires java.xml.bind;
    requires java.persistence;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires lombok;
    requires org.postgresql.jdbc;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires org.apache.logging.log4j;

    opens pl.damiankaplon.devcompany to javafx.fxml;
    opens pl.damiankaplon.devcompany.model to org.hibernate.orm.core;
    exports pl.damiankaplon.devcompany;
    exports pl.damiankaplon.devcompany.model;
    exports pl.damiankaplon.devcompany.controller;
    exports pl.damiankaplon.devcompany.dbutil;
    exports pl.damiankaplon.devcompany.service;
    exports pl.damiankaplon.devcompany.service.exception;
    opens pl.damiankaplon.devcompany.controller to javafx.fxml;
}