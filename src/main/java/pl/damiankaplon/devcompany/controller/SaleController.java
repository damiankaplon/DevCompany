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
    TextField saleNrFX, nameFX, surnameFX, peselFX, cityFX, addressFX, postalFX, streetFX, apartmentNrFX,
            valueFX, signDateFX, paymentDateFX;
    @FXML
    TextArea saleTextArea;

    @FXML
    public void searchSale() {
        try{
            Sale sale = saleService.getSaleByIdentity(saleNrFX.getText());

            this.saleNrFX.setText(sale.getIdentity());
            this.nameFX.setText(sale.getClient().getName());
            this.surnameFX.setText(sale.getClient().getSurname());
            this.peselFX.setText(sale.getClient().getPesel());
            this.cityFX.setText(sale.getBuilding().getCity());
            this.postalFX.setText(sale.getBuilding().getPostal());
            this.streetFX.setText(sale.getBuilding().getStreet());
            this.addressFX.setText(String.valueOf(sale.getBuilding().getAddress()));
            this.apartmentNrFX.setText(String.valueOf(sale.getFlat().get(0).getFlatNumber()));
            this.valueFX.setText(String.valueOf(sale.getSaleValue()));
            this.signDateFX.setText(String.valueOf(sale.getSignDate()));
            this.paymentDateFX.setText(String.valueOf(sale.getPaymentDate()));
        }
        catch(NoResultException ignored){
            this.saleTextArea.appendText(("No sales were found for certain sale identity\n"));
        }

    }

    @FXML
    public void addSale(){
//        saleService.save(this.saleNrField.getText(),
//        this.surnameField.getText(),
//        this.nameField.getText(),
//        this.peselField.getText(),
//        this.cityField.getText(),
//        this.postalField.getText(),
//        this.streetField.getText(),
//        this.addressField.getText(),
//        this.apartmentNr.getText());
    }
}
