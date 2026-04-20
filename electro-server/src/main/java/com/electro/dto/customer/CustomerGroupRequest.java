package com.electro.dto.customer;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class CustomerGroupRequest {
    private String code;
    private String name;
    private String description;
    private String color;
    private Integer status;
    private Integer minRewardPoint;
    private BigDecimal discountPercent;
}
