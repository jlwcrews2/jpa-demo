package no.jlwcrews.jpademo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter @ToString
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(generator = "address_generator")
    @SequenceGenerator(name = "address_generator", sequenceName = "address_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "address_id")
    private Long addressId;

    public Address(String address) {
        this.address = address;
    }

    private String address;

    @ManyToMany(mappedBy = "addresses", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Customer> customers = new ArrayList<>();
}
