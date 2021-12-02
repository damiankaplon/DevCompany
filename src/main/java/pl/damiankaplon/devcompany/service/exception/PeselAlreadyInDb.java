package pl.damiankaplon.devcompany.service.exception;

import org.hibernate.Session;

public class PeselAlreadyInDb extends Throwable {
    public PeselAlreadyInDb(Session session) {
        session.close();
    }
}
