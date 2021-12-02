package pl.damiankaplon.devcompany.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pl.damiankaplon.devcompany.dbutil.DbUtil;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.service.ClientService;
import pl.damiankaplon.devcompany.service.exception.ManySamePeselsInDb;
import pl.damiankaplon.devcompany.service.exception.NoClientsFound;
import pl.damiankaplon.devcompany.service.exception.NotSpecifiedAllArguments;
import pl.damiankaplon.devcompany.service.exception.PeselAlreadyInDb;
import pl.damiankaplon.devcompany.service.exception.NotSpecifiedReqArgs;

import java.util.List;

public class ClientController{

    private final ClientService service = new ClientService(DbUtil.sessionFactory);

    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private TextField pesel;
    @FXML private TextArea clientTextArea;

    @FXML
    public void addClient(){
        try {
            service.saveClient(new Client(name.getText(), surname.getText(), pesel.getText()));
        } catch (PeselAlreadyInDb e) {
            this.clientTextArea.appendText("PESEL is already occupied \n");
        } catch (NotSpecifiedAllArguments notSpecifiedAllArguments) {
            this.clientTextArea.appendText("Please specify all values to add client \n");
        }
    }

    @FXML
    public void getClient(){
        List<Client> clients;
        try {
            clients = service.getClientByAllArgs(new Client(name.getText(), surname.getText(), pesel.getText()));
            clients.forEach(c -> this.clientTextArea.appendText(c.getName() +" " +c.getSurname()+" "+ c.getPesel()+"\n"));
        } catch (NoClientsFound e) {
            this.clientTextArea.appendText("No clients were found \n");
        }
    }
    @FXML
    public void updateClient() {
        try {
            service.updateClient(new Client(name.getText(), surname.getText(), pesel.getText()));
        } catch (ManySamePeselsInDb e) {
            this.clientTextArea.appendText("Many Clients with same pesel in database. Call IT!\n");
        } catch (NotSpecifiedReqArgs e) {
            this.clientTextArea.appendText("You have to specify Pesel to identify client you want to update\n");
        }
    }
}
