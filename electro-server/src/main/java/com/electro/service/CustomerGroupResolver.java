package com.electro.service;

import com.electro.entity.customer.CustomerGroup;
import com.electro.repository.customer.CustomerGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerGroupResolver {
    @Autowired
    private CustomerGroupRepository customerGroupRepository;

    public CustomerGroup resolveGroupByPoint(int totalPoint) {
        return customerGroupRepository
                .findTopByMinRewardPointLessThanEqualOrderByMinRewardPointDesc(totalPoint);
    }


}
