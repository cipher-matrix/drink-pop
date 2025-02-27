package com.birimarung.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Drinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String drinkName;
    private boolean isDrinkPubliclyAvailable;
    @ManyToOne
    @JoinColumn(name = "drink_type_id", foreignKey = @ForeignKey(name = "fk_drink_type"))
    private DrinkType drinkType;
}
