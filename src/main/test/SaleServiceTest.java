import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.damiankaplon.devcompany.dbutil.DbUtil;
import pl.damiankaplon.devcompany.model.Building;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.model.Flat;
import pl.damiankaplon.devcompany.model.Sale;
import pl.damiankaplon.devcompany.service.SaleService;
import pl.damiankaplon.devcompany.service.exception.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.*;

/**
 * Made this test class because had NullPointerException while using <code>pl.damiankaplon.service.SaleService.getSaleByIdentity</code>
in fact ic appeared that null pointer exception was caused by wrong fx:id for view SaleView.fxml */
public class SaleServiceTest {

    static SessionFactory sessionFactory;
    static SaleService service;

    @BeforeAll
    static void setUp(){
        DbUtil.initialize();
        sessionFactory = DbUtil.sessionFactory;
        service = new SaleService(sessionFactory);
    }
    @Test
    public void getSaleByIdentityTest() {
        Sale sale = service.getSaleByIdentity("02/12/2021/0001");
        String actualCity = sale.getBuilding().getCity();
        assertThat(actualCity).isEqualTo("Koszalin");
    }

    @Test
    public void saveTest() throws SaleAlreadyExists, NoSuchBuilding, ParseException, NoSuchFlat, NoClientsFound, WrongSaleIdentity, FlatAlreadySoldException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        java.util.Date signDate = simpleDateFormat.parse("20/20/2020");
        java.util.Date paymentDate = simpleDateFormat.parse("20/20/2020");

        Building building = Building.builder().city("Koszalin")
                .street("Zwycięstwa")
                .postal("76-115")
                .address(30)
                .build();
        Sale sale = Sale.builder()
                .flat(List.of(Flat.builder()
                        .flatNumber(30)
                        .building(building)
                        .build()))
                .building(building)
                .client(Client.builder().pesel("99010604291").build())
                .saleValue(500000)
                .signDate(new java.sql.Date(Objects.requireNonNull(signDate).getTime()))
                .paymentDate(new java.sql.Date(Objects.requireNonNull(paymentDate).getTime()))
                .identity("20/20/2021")
                .build();
        service.save(sale);
        //assertThatExceptionOfType(SaleAlreadyExists.class).isThrownBy(()->service.save(sale));
    }

    @Test
    public void validateSaleIdentityTest(){
        boolean result = service.validateRegexSaleIdentity("12/12/20da/0001");
        boolean result2 = service.validateRegexSaleIdentity("12/12/2020/0001");
        assertThat(result).isFalse();
        assertThat(result2).isTrue();
    }

}
