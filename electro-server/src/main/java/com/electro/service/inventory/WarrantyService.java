package com.electro.service.inventory;

import com.electro.entity.customer.Customer;
import com.electro.entity.product.Serial;
import com.electro.entity.product.WarrantyTicket;
import com.electro.repository.product.SerialRepository;
import com.electro.repository.product.WarrantyTicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WarrantyService {
    @Autowired
    private WarrantyTicketRepository warrantyTicketRepository;

    @Autowired
    private SerialRepository serialRepository;

    public void createWarranty(String serialCode, Long customerId) {
        Serial serial = serialRepository.findBySerialCode(serialCode);

        serial.setStatus(2); // warranty

        WarrantyTicket ticket = new WarrantyTicket();
        ticket.setSerial(serial);
        Customer customer = new Customer();
        customer.setId(customerId);
        ticket.setCustomer(customer);
        ticket.setStatus(0);

        warrantyTicketRepository.save(ticket);
    }
}
