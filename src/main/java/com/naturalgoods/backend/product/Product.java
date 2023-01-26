package com.naturalgoods.backend.product;


import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table
public class Product {

    // https://stackoverflow.com/a/52278595/13673510
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String imageLink; // S3 key

    public Product() {
    }

    public Product(String name, String imageLink) {
        this.name = name;
        this.imageLink = imageLink;
    }
}
