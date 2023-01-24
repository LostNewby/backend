package com.naturalgoods.backend.account;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Account {
    @Id
    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
             
             //
    )
    private Long id;
    private String name;
    private String surname;

    public Account() {

    }
    public Account(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}

