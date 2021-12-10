import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.damiankaplon.devcompany.dbutil.DbUtil;
import pl.damiankaplon.devcompany.model.Sale;
import pl.damiankaplon.devcompany.service.SaleService;
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

}
