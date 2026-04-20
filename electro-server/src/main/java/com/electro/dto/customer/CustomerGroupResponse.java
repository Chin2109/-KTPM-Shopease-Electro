package com.electro.dto.customer;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CustomerGroupResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String code;
    private String name;
    private String description;
    private String color;
    private Integer status;
    private Integer minRewardPoint;
    private BigDecimal discountPercent;
}
