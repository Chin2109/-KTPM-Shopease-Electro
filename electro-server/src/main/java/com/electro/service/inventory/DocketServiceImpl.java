package com.electro.service.inventory;

import com.electro.constant.FieldName;
import com.electro.constant.ResourceName;
import com.electro.constant.SearchFields;
import com.electro.dto.ListResponse;
import com.electro.dto.inventory.DocketRequest;
import com.electro.dto.inventory.DocketResponse;
import com.electro.entity.client.Preorder;
import com.electro.entity.general.Notification;
import com.electro.entity.general.NotificationType;
import com.electro.entity.inventory.Docket;
import com.electro.entity.product.Serial;
import com.electro.entity.product.Variant;
import com.electro.exception.ResourceNotFoundException;
import com.electro.mapper.general.NotificationMapper;
import com.electro.mapper.inventory.DocketMapper;
import com.electro.repository.client.PreorderRepository;
import com.electro.repository.general.NotificationRepository;
import com.electro.repository.inventory.DocketRepository;
import com.electro.repository.product.SerialRepository;
import com.electro.service.general.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DocketServiceImpl implements DocketService {

    private DocketRepository docketRepository;

    private DocketMapper docketMapper;

    private PreorderRepository preorderRepository;

    private NotificationRepository notificationRepository;

    private NotificationService notificationService;

    private NotificationMapper notificationMapper;

    private SerialRepository serialRepository;

    @Override
    public ListResponse<DocketResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        return defaultFindAll(page, size, sort, filter, search, all, SearchFields.DOCKET, docketRepository, docketMapper);
    }

    @Override
    public DocketResponse findById(Long id) {
        return defaultFindById(id, docketRepository, docketMapper, ResourceName.DOCKET);
    }

    @Override
    public DocketResponse save(DocketRequest request) {
        Docket docket = docketMapper.requestToEntity(request);

        // ✅ FIX ở đây
        if (docket.getCode() == null) {
            docket.setCode(generateDocketCode());
        }

        docket = docketRepository.save(docket);

        afterCreateOrUpdateCallback(docket);

        return docketMapper.entityToResponse(docket);
    }

    @Override
    public DocketResponse save(Long id, DocketRequest request) {
        Docket docket = docketRepository.findById(id)
                .map(existingEntity -> docketMapper.partialUpdate(existingEntity, request))
                .map(docketRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.DOCKET, FieldName.ID, id));
        afterCreateOrUpdateCallback(docket);
        return docketMapper.entityToResponse(docket);
    }

    private void afterCreateOrUpdateCallback(Docket docket) {
        // Docket nhập (1) có trạng thái Hoàn thành (3)
        if (docket.getType().equals(1) && docket.getStatus().equals(3)) {
            List<Long> productIds = docket.getDocketVariants().stream()
                    .map(docketVariant -> docketVariant.getVariant().getProduct().getId())
                    .distinct()
                    .collect(Collectors.toList());

            List<Preorder> preorders = preorderRepository.findByProduct_IdInAndStatus(productIds, 1);

            log.warn("RUN CALLBACK");
            docket.getDocketVariants().forEach(dv -> {
                log.warn("VARIANT ID: {}, QTY: {}", dv.getVariant().getId(), dv.getQuantity());
                Variant variant = dv.getVariant();
                int quantity = dv.getQuantity();

                for (int i = 0; i < quantity; i++) {
                    Serial serial = new Serial();
                    serial.setVariant(variant);
                    serial.setSerialCode(generateSerialCode());
                    serial.setStatus(0); // in_stock

                    serialRepository.save(serial);
                    log.warn("CREATED SERIAL: {}", serial.getSerialCode());
                }
            });

            List<Notification> notifications = preorders.stream()
                    .map(preorder -> new Notification()
                            .setUser(preorder.getUser())
                            .setType(NotificationType.PREORDER)
                            .setMessage(String.format("Sản phẩm %s đã có hàng. Vui lòng kiểm tra.", preorder.getProduct().getName()))
                            .setAnchor("/product/" + preorder.getProduct().getSlug())
                            .setStatus(1))
                    .collect(Collectors.toList());

            notificationRepository.saveAll(notifications);

            notifications.forEach(notification -> notificationService
                    .pushNotification(notification.getUser().getUsername(), notificationMapper.entityToResponse(notification)));

            preorders.forEach(preorder -> {
                preorder.setUpdatedAt(Instant.now());
                preorder.setStatus(2);
            });

            preorderRepository.saveAll(preorders);

            List<String> usernames = notifications.stream()
                    .map(notification -> notification.getUser().getUsername())
                    .collect(Collectors.toList());

            log.info("Push notifications for users: " + usernames);
        }
    }

    private String generateSerialCode() {
        return "SR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private String generateDocketCode() {
        return "DK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Override
    public void delete(Long id) {
        docketRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        docketRepository.deleteAllById(ids);
    }

}
