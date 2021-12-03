package pl.damiankaplon.devcompany.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.damiankaplon.devcompany.dbutil.DbUtil;
import pl.damiankaplon.devcompany.model.Sale;
import pl.damiankaplon.devcompany.service.SaleService;

import javax.persistence.NoResultException;

@NoArgsConstructor
@Getter
@Setter
public class SaleController {

    SaleService saleService = new SaleService(DbUtil.sessionFactory);

    @FXML
    TextField saleNrField, nameField, surnameField, peselField, cityField, addressField, postalField, streetField, apartmentNr;
    @FXML
    TextArea saleTextArea;

    @FXML
    public void searchSale() {
        try{
            Sale sale = saleService.getSaleByIdentity(saleNrField.getText());
            this.saleNrField.setText(sale.getIdentity());
            this.nameField.setText(sale.getClient().getName());
            this.surnameField.setText(sale.getClient().getSurname());
            this.peselField.setText(sale.getClient().getPesel());
            this.cityField.setText(sale.getBuilding().getCity());
            this.postalField.setText(sale.getBuilding().getPostal());
            this.streetField.setText(sale.getBuilding().getStreet());
            this.addressField.setText(String.valueOf(sale.getBuilding().getAddress()));
            this.apartmentNr.setText(String.valueOf(sale.getFlat().get(0).getFlatNumber()));
        }
        catch(NoResultException ignored){
            this.saleTextArea.appendText(("No sales were found for certain sale identity\n"));
        }

    }
}
