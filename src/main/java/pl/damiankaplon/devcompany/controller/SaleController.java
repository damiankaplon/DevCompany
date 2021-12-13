package pl.damiankaplon.devcompany.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.damiankaplon.devcompany.dbutil.DbUtil;
import pl.damiankaplon.devcompany.model.Building;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.model.Flat;
import pl.damiankaplon.devcompany.model.Sale;
import pl.damiankaplon.devcompany.service.SaleService;
import pl.damiankaplon.devcompany.service.exception.*;

import javax.persistence.NoResultException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class SaleController {

    SaleService saleService = new SaleService(DbUtil.sessionFactory);

    @FXML
    TextField saleNrFX, nameFX, surnameFX, peselFX, cityFX, addressFX, postalFX, streetFX, apartmentNrFX,
            valueFX, signDateFX, paymentDateFX;
    @FXML
    TextArea textAreaFX;

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
            this.textAreaFX.appendText(("No sales were found for certain sale identity\n"));
        }

    }

    @FXML
    public void addSale() {
        try {
            addNewSale();
            this.textAreaFX.appendText("Successfully added new Sale! \n");
        } catch (NotSpecifiedReqArgs e) {
            this.textAreaFX.appendText("Please specify all required arguments \n");
        } catch (ParseException e) {
            this.textAreaFX.appendText("Please specify correct date format MM/DD/YYYY \n");
        }
    }

    private void addNewSale() throws NotSpecifiedReqArgs, ParseException {
        if (
                this.saleNrFX.getText().isBlank() ||
                        this.nameFX.getText().isBlank() ||
                        this.surnameFX.getText().isBlank() ||
                        this.peselFX.getText().isBlank() ||
                        this.cityFX.getText().isBlank() ||
                        this.postalFX.getText().isBlank() ||
                        this.streetFX.getText().isBlank() ||
                        this.addressFX.getText().isBlank() ||
                        this.apartmentNrFX.getText().isBlank() ||
                        this.valueFX.getText().isBlank() ||
                        this.signDateFX.getText().isBlank()
        ) throw new NotSpecifiedReqArgs();

        Date signDate = parseStringDateToSqlDateObj(this.signDateFX.getText());
        Date paymentDate = parseStringDateToSqlDateObj(this.paymentDateFX.getText());

        Building building = Building.builder().city(this.cityFX.getText())
                .street(this.streetFX.getText())
                .postal(this.postalFX.getText()).address(Integer.parseInt(this.addressFX.getText()))
                .build();

        Sale sale = Sale.builder()
                .flat(List.of(Flat.builder()
                        .flatNumber(Integer.parseInt(this.apartmentNrFX.getText()))
                        .building(building)
                        .build()))
                .building(building)
                .client(Client.builder().pesel(this.peselFX.getText()).build())
                .saleValue(Double.parseDouble(this.valueFX.getText()))
                .signDate(signDate)
                .paymentDate(paymentDate)
                .identity(this.saleNrFX.getText())
                .build();

        try {
            saleService.save(sale);
        } catch (SaleAlreadyExists e) {
            this.textAreaFX.appendText("Sale with that identity already exists \n");
        } catch (NoSuchBuilding e) {
            this.textAreaFX.appendText("There is no such building \n");
        } catch (NoSuchFlat e) {
            this.textAreaFX.appendText("There is no such flat \n");
        } catch (NoClientsFound e) {
            this.textAreaFX.appendText("There is no such client \n");
        } catch (WrongSaleIdentity e) {
            this.textAreaFX.appendText("Wrong sale identity. The correct is XX/XX/XXXX/XXXX\n");
        }
    }

    public java.sql.Date parseStringDateToSqlDateObj(String stringDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date utilDate = simpleDateFormat.parse(stringDate);
        return new java.sql.Date(Objects.requireNonNull(utilDate).getTime());
    }
}
