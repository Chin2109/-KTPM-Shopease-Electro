package com.electro.entity.product;

import com.electro.entity.BaseEntity;
import com.electro.entity.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "warranty_ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarrantyTicket extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serial_id", nullable = false)
    private Serial serial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "status", nullable = false)
    private Integer status;
    // 0: created
    // 1: processing
    // 2: done
    // 3: rejected

    @Column(name = "note")
    private String note;
}
