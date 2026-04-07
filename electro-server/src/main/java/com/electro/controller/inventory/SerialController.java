package com.electro.controller.inventory;

import com.electro.dto.product.SerialResponse;
import com.electro.service.inventory.SerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/serials")
@RequiredArgsConstructor
public class SerialController {

    private final SerialService serialService;

    @GetMapping
    public ResponseEntity<List<SerialResponse>> getByVariant(
            @RequestParam Long variantId
    ) {
        return ResponseEntity.ok(serialService.getByVariantId(variantId));
    }

    @GetMapping("/by-order")
    public ResponseEntity<List<SerialResponse>> getByOrder(
            @RequestParam Long orderId
    ) {
        return ResponseEntity.ok(serialService.getByOrderId(orderId));
    }
}