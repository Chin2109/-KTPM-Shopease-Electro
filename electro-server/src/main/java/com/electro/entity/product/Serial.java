package com.electro.entity.product;

import com.electro.entity.BaseEntity;
import com.electro.entity.order.Order;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Serial extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    @Column(name = "serial_code", nullable = false, unique = true)
    private String serialCode;

    @Column(name = "status", nullable = false)
    private Integer status;
    // 0: in_stock
    // 1: sold
    // 2: warranty

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}