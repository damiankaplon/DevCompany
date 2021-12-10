import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.damiankaplon.devcompany.dbutil.DbUtil;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.model.Sale;
import pl.damiankaplon.devcompany.service.ClientService;
import pl.damiankaplon.devcompany.service.SaleService;
import pl.damiankaplon.devcompany.service.exception.NoClientsFound;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientServiceTest {

    static SessionFactory sessionFactory;
    static ClientService service;

    @BeforeAll
    static void setUp(){
        DbUtil.initialize();
        sessionFactory = DbUtil.sessionFactory;
        service = new ClientService(sessionFactory);
    }

    @Test
    public void getClientByNameTest() {
        try {
            List<Client> clients = service.getClientByAllArgs(Client.builder().pesel("99010604391").name("Damian").build());
            assertThat(clients).hasSize(1);
        } catch (NoClientsFound ignored) {
        }
    }

    @Test
    public void getAllClientsTest(){
        List<Client> clients = service.getAllClients();
        assertThat(clients).isNotEmpty();
    }
}
