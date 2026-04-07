package com.electro.repository.product;

import com.electro.entity.product.WarrantyTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WarrantyTicketRepository extends JpaRepository<WarrantyTicket, Long>, JpaSpecificationExecutor<WarrantyTicket> {
}
