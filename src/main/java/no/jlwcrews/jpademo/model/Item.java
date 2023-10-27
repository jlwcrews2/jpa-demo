package no.jlwcrews.jpademo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(generator = "item_generator")
    @SequenceGenerator(name = "item_generator", sequenceName = "item_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "item_id")
    private Long itemId = 0L;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_qty")
    private Integer itemQuantity;

    public Item(String itemName, Integer itemQuantity) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }
}
