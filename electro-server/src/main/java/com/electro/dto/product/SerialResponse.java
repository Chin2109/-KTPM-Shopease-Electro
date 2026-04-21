package com.electro.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SerialResponse {
    private Long id;
    private String serialCode;
    private Integer status;
}
