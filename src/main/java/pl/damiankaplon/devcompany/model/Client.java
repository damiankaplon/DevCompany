package pl.damiankaplon.devcompany.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    private long id;

    @Column(name = "client_name") private String name;
    @Column(name = "client_surname") private String surname;
    @Column(name = "client_pesel") private String pesel;

    public Client(String name, String surname, String pesel){
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }
}
