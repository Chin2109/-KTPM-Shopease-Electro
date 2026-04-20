package com.electro.service;

import com.electro.entity.customer.Customer;
import com.electro.entity.customer.CustomerGroup;
import com.electro.repository.customer.CustomerRepository;
import com.electro.repository.reward.RewardLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerGroupService {

    @Autowired
    private final RewardLogRepository rewardLogRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final CustomerGroupResolver resolver;

    @Transactional
    public void updateCustomerGroup(String username) {
        int totalScore = rewardLogRepository.sumScoreByUsername(username);

        Customer customer = customerRepository.findByUserUsername(username);

        CustomerGroup newGroup = resolver.resolveGroupByPoint(totalScore);

        if (newGroup != null && !newGroup.equals(customer.getCustomerGroup())) {
            customer.setCustomerGroup(newGroup);
            customerRepository.save(customer);
        }
    }
}
