package pl.damiankaplon.devcompany.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.damiankaplon.devcompany.dbutil.DbUtil;
import pl.damiankaplon.devcompany.model.Sale;
import pl.damiankaplon.devcompany.service.SaleService;

@NoArgsConstructor
@Getter
@Setter
public class SaleController {

    SaleService saleService = new SaleService(DbUtil.sessionFactory);

    @FXML
    TextField saleNrField, nameField, surnameField, cityField, addressField, postalField, streetField, peselField;

    @FXML
    public void searchSale() {
        Sale sale = saleService.getSaleByIdentity(saleNrField.getText());
        this.saleNrField.setText(sale.getIdentity());
        this.nameField.setText(sale.getClient().getName());
        this.surnameField.setText(sale.getClient().getSurname());
        this.peselField.setText(sale.getClient().getPesel());
        this.cityField.setText(sale.getBuilding().getCity());
        this.postalField.setText(sale.getBuilding().getPostal());
        this.streetField.setText(sale.getBuilding().getStreet());
        this.addressField.setText(String.valueOf(sale.getBuilding().getAddress()));

    }
}
