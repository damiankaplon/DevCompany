package pl.damiankaplon.devcompany.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.service.ClientService;

public class ClientController{

    private final ClientService service = new ClientService();

    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private TextField pesel;

    @FXML
    public void addClient(){
        service.saveClient(new Client(name.getText(), surname.getText(), pesel.getText()));
    }

    //not working
    @FXML
    public void removeClient(){
        service.removeClient(new Client(name.getText(), surname.getText(), pesel.getText()));
    }
}
