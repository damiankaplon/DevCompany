package pl.damiankaplon.devcompany.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.damiankaplon.devcompany.MainApplication;
import pl.damiankaplon.devcompany.dbutil.DbUtil;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.service.ClientService;
import pl.damiankaplon.devcompany.service.exception.ManySamePeselsInDb;
import pl.damiankaplon.devcompany.service.exception.NoClientsFound;
import pl.damiankaplon.devcompany.service.exception.NotSpecifiedAllArguments;
import pl.damiankaplon.devcompany.service.exception.PeselAlreadyInDb;
import pl.damiankaplon.devcompany.service.exception.NotSpecifiedReqArgs;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    private final ClientService service = new ClientService(DbUtil.sessionFactory);

    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private TextField pesel;
    @FXML private TextArea clientTextArea;
    @FXML private TableColumn<Client, String> nameColumn, surnameColumn, peselColumn;
    @FXML private TableView<Client> clientsTable;
    ObservableList<Client> clientsFX;
    ObservableList<Client> selectedItems;
    TableView.TableViewSelectionModel<Client> selectionModel;


    @FXML
    public void addClient(){
        MainApplication.logger.debug("addClient invoked");
        try {
            service.saveClient(new Client(name.getText(), surname.getText(), pesel.getText()));
            MainApplication.logger.debug("addClient method success");
        } catch (PeselAlreadyInDb e) {
            this.clientTextArea.appendText("PESEL is already occupied \n");
            MainApplication.logger.debug("addClient method failed");
        } catch (NotSpecifiedAllArguments notSpecifiedAllArguments) {
            this.clientTextArea.appendText("Please specify all values to add client \n");
            MainApplication.logger.debug("addClient method failed");
        } finally {
            this.refreshClientGrid();
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
            this.clientTextArea.appendText("Client updated!\n");
        } catch (ManySamePeselsInDb e) {
            this.clientTextArea.appendText("Many Clients with same pesel in database. Call IT!\n");
        } catch (NotSpecifiedReqArgs e) {
            this.clientTextArea.appendText("You have to specify Pesel, Name and Surname to identify client you want to update\n");
        } finally {
            this.refreshClientGrid();
        }
    }

    private void refreshClientGrid() {
        List<Client> clients = service.getAllClients();
        this.clientsFX = FXCollections.observableList(clients);
        this.clientsTable.setItems(this.clientsFX);
        this.clientsTable.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        this.peselColumn.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        this.refreshClientGrid();
        this.selectionModel = this.clientsTable.getSelectionModel();
        this.selectionModel.setSelectionMode(SelectionMode.SINGLE);
        this.selectedItems = this.selectionModel.getSelectedItems();
        addListenerToTable();
    }

    private void addListenerToTable(){
        this.selectedItems.addListener((ListChangeListener<Client>) change -> {
            if (!selectedItems.isEmpty()) {
                ObservableList<? extends Client> clients = change.getList();
                Client client = clients.get(0);
                name.setText(client.getName());
                surname.setText(client.getSurname());
                pesel.setText(client.getPesel());
            }
        });
    }
}
