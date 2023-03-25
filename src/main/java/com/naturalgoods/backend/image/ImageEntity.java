package com.naturalgoods.backend.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "images")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {
    @Id
    @Column(name = "record_id")
    private Long recordId;

    @Column(name = "link")
    private String link;
}
