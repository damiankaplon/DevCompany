package pl.damiankaplon.devcompany.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL) private List<Sale> sales;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL) private List<Flat> clients;

    public Client(String name, String surname, String pesel){
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }

    public Client(long id, String name, String surname, String pesel){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return  name +
                surname +
                pesel;
    }
}
