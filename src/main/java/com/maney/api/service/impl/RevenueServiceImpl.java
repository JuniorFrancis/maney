package com.maney.api.service.impl;

import com.maney.api.model.Revenue;
import com.maney.api.repository.RevenueRepository;
import com.maney.api.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.maney.api.utils.Validator.*;


@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    RevenueRepository revenueRepository;

    @Override
    public Revenue create(Revenue revenue) {
        checkIsNotZero(revenue.getAmount());
        checkNotNull(revenue.getRevenueType());

        return revenueRepository.save(revenue);
    }

    @Override
    public Optional<Revenue> getRevenue(Long id) {
        checkIsNotZero(id);

        return revenueRepository.findById(id);
    }

    @Override
    public List<Revenue> getRevenues() {

        return revenueRepository.findAll();
    }
}
