package pl.damiankaplon.devcompany.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id", nullable = false)
    private long id;

    @OneToMany(mappedBy = "sale", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Flat> flat;

    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;

    @Column(name = "sale_value") private double saleValue;

    @Column(name = "sale_assign_date") private Date signDate;

    @Column(name = "sale_payment_date") private Date paymentDate;

    @Column(name = "sale_identity") private String identity;
}
