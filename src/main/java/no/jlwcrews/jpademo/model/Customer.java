package no.jlwcrews.jpademo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "customer_generator")
    @SequenceGenerator(name = "customer_generator", sequenceName = "customer_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "customer_id")
    private Long customerId = 0L;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("customer")
    @JoinColumn(name = "customer_id")
    private List<Order> orders = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_address",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses = new ArrayList<>();

    public Customer(String customerName, String customerEmail) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }
}
