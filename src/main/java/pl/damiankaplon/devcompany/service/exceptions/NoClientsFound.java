package pl.damiankaplon.devcompany.service.exceptions;

import org.hibernate.Session;

public class NoClientsFound extends Throwable {
    public NoClientsFound(Session session) {
        session.close();
    }
}
