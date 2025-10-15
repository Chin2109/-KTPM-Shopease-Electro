package com.electro.config.payment.paypal;

import com.electro.dto.payment.AccessTokenResponse;
import com.electro.dto.payment.ClientTokenResponse;
import com.electro.dto.payment.PaypalRequest;
import com.electro.dto.payment.PaypalResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@AllArgsConstructor
@Slf4j
public class PayPalHttpClient {
}
