package com.electro.service;

import com.electro.config.payment.paypal.VNPayConfig;
import com.electro.config.payment.paypal.VNPayUtil;
import com.electro.dto.PaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final VNPayConfig vnPayConfig;

    public PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request,
                                                       long amount,
                                                       String orderCode) {

        // ❗ dùng amount từ param method
        long vnpAmount = amount * 100L;

        String bankCode = request.getParameter("bankCode");

        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();

        vnpParamsMap.put("vnp_Amount", String.valueOf(vnpAmount));

        if (bankCode != null && !bankCode.isEmpty()) {
            vnpParamsMap.put("vnp_BankCode", bankCode);
        }

        // 🔥 GẮN ORDER (2 param cần bổ sung)
        if (orderCode != null && !orderCode.isEmpty()) {
            vnpParamsMap.put("vnp_TxnRef", orderCode);                 // map transaction ↔ order
            vnpParamsMap.put("vnp_OrderInfo", "orderCode=" + orderCode); // để trace/log
        }

        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));

        // build query url (giữ nguyên)
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);

        String vnpSecureHash = VNPayUtil.hmacSHA512(
                vnPayConfig.getSecretKey(),
                hashData
        );

        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;

        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;

        return PaymentDTO.VNPayResponse.builder()
                .code("ok")
                .message("success")
                .paymentUrl(paymentUrl)
                .build();
    }
}