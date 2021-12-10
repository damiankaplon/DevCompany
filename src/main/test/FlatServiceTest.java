import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.damiankaplon.devcompany.dbutil.DbUtil;
import pl.damiankaplon.devcompany.model.Building;
import pl.damiankaplon.devcompany.model.Flat;
import pl.damiankaplon.devcompany.service.FlatService;
import static org.assertj.core.api.Assertions.*;

/**
 * Made this test class because had NullPointerException while using <code>pl.damiankaplon.service.SaleService.getSaleByIdentity</code>
 in fact ic appeared that null pointer exception was caused by wrong fx:id for view SaleView.fxml */
public class FlatServiceTest {

    static SessionFactory sessionFactory;
    static FlatService service;

    @BeforeAll
    static void setUp(){
        DbUtil.initialize();
        sessionFactory = DbUtil.sessionFactory;
        service = new FlatService(sessionFactory);
    }
    @Test
    public void getSaleByIdentityTest() {
       //Given
        Flat testFlat = Flat.builder().flatNumber(30)
                .building(Building.builder()
                        .id(1L).build())
                .build();
        Building expectedBuilding = Building.builder()
                .id(1)
                .address(30)
                .street("Zwycięstwa")
                .postal("76-115")
                .city("Koszalin")
                .build();
        //When
        Flat resultFlat = service.getFlat(testFlat);
        //Then
        assertThat(resultFlat.getBuilding()).isEqualTo(expectedBuilding);
    }

}
