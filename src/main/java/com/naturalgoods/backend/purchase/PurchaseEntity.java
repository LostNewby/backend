package com.naturalgoods.backend.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "purchase_info")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "costumer_id")
    private Long costumerId;

    @Column(name = "record_id")
    private Long recordId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "costumer_phone")
    private String costumerPhone;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;

    @Column(name = "creation_date")
    private Date creationDate=new Date();

    @Column(name = "completion_date")
    private Date completionDate;
}
