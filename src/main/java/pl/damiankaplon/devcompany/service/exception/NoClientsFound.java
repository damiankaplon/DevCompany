package pl.damiankaplon.devcompany.service.exception;

import org.hibernate.Session;

public class NoClientsFound extends Throwable {
    public NoClientsFound(Session session) {
        session.close();
    }
}
