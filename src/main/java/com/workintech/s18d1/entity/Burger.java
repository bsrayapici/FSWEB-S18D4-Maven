package com.workintech.s18d1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;


    private String name;
    private Double price;
    private boolean isVegan;

    @Enumerated(EnumType.STRING)
    private BreadType breadType;

    private String contents;

    public boolean getIsVegan() {
        return isVegan;
    }

    // Manuel setter
    public void setIsVegan(boolean isVegan) {
        this.isVegan = isVegan;
    }

}
