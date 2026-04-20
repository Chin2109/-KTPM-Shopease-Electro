package com.electro.controller;

import com.electro.dto.PaymentDTO;
import com.electro.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/client-api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/vnpay")
    public ResponseEntity<PaymentDTO.VNPayResponse> pay(HttpServletRequest request,
                                                        @RequestParam("amount") long amount,
                                                        @RequestParam("orderCode") String orderCode) {
        return ResponseEntity.ok(paymentService.createVnPayPayment(request,amount,orderCode));
    }

    @GetMapping("/vn-pay-callback")
    public ResponseEntity<PaymentDTO.VNPayResponse> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            return ResponseEntity.ok(new PaymentDTO.VNPayResponse("00", "Success", ""));
        } else {
            return ResponseEntity.ok(new PaymentDTO.VNPayResponse("01", "Failed", ""));
        }
    }
}
