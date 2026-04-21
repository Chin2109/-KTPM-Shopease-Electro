package com.electro.service.inventory;

import com.electro.dto.product.SerialResponse;
import com.electro.entity.order.Order;
import com.electro.entity.order.OrderVariant;
import com.electro.entity.product.Serial;
import com.electro.repository.product.SerialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SerialServiceImpl implements SerialService {

    private final SerialRepository serialRepository;

    @Override
    public List<SerialResponse> getByVariantId(Long variantId) {

        List<Serial> serials = serialRepository.findByVariantId(variantId);

        return serials.stream()
                .map(s -> new SerialResponse(
                        s.getId(),
                        s.getSerialCode(),
                        s.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void assignSerialToOrder(Order order) {

        for (OrderVariant ov : order.getOrderVariants()) {

            Long variantId = ov.getVariant().getId();
            int quantity = ov.getQuantity();

            List<Serial> serials = serialRepository
                    .findAvailableSerials(variantId, 0, quantity);

            // Không đủ hàng
            if (serials.size() < quantity) {
                throw new RuntimeException("Không đủ hàng cho variantId=" + variantId);
            }

            // Gán serial
            for (Serial serial : serials) {
                serial.setStatus(1); // sold
                serial.setOrder(order);
            }

            serialRepository.saveAll(serials);
        }
    }

    public List<SerialResponse> getByOrderId(Long orderId) {
        return serialRepository.findByOrderId(orderId)
                .stream()
                .map(s -> new SerialResponse(
                        s.getId(),
                        s.getSerialCode(),
                        s.getStatus()
                ))
                .collect(Collectors.toList()); // Sửa .toList() thành cái này
    }
}
