package com.electro.repository.product;

import com.electro.entity.product.Property;
import com.electro.entity.product.Serial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SerialRepository extends JpaRepository<Serial, Long>, JpaSpecificationExecutor<Serial> {
    @Query(value = "SELECT * FROM serial " +
        "WHERE variant_id = :variantId AND status = :status " +
        "LIMIT :limit",
        nativeQuery = true)
    List<Serial> findTopNByVariantIdAndStatus(Long variantId, Integer status, int limit);

    Serial findBySerialCode(String serialCode);

    List<Serial> findByVariantId(Long variantId);

    @Query(value = "SELECT * FROM serial " +
            "WHERE variant_id = :variantId " +
            "AND status = :status " +
            "LIMIT :limit",
            nativeQuery = true)
    List<Serial> findAvailableSerials(
            @Param("variantId") Long variantId,
            @Param("status") Integer status,
            @Param("limit") int limit
    );

    List<Serial> findByOrderId(Long orderId);
}
