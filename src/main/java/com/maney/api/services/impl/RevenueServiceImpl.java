package com.maney.api.services.impl;

import com.maney.api.handlers.UserHandler;
import com.maney.api.models.Revenue;
import com.maney.api.repositories.RevenueRepository;
import com.maney.api.services.RevenueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.maney.api.handlers.ValidatorHandler.*;


@Service
public class RevenueServiceImpl implements RevenueService {

    public RevenueServiceImpl(RevenueRepository revenueRepository, UserHandler userHandler) {
        this.revenueRepository = revenueRepository;
        this.userHandler = userHandler;
    }

    private final UserHandler userHandler;

    private final RevenueRepository revenueRepository;


    @Override
    public Revenue create(Revenue revenue) {

        validateRevenueAmount(revenue.getAmount());

        if(revenue.getFixed().equals(Boolean.FALSE) && revenue.getPaymentDate() == null) {
            throw new IllegalArgumentException("Payment date is necessary to fixed payment");
        }

        revenue.setUser(userHandler.getCurrentUser());
        return revenueRepository.save(revenue);
    }

    @Override
    public Optional<Revenue> getRevenue(Long id) {
        Long userId = userHandler.getCurrentUserId();
        return revenueRepository.findByIdAndUserId(id, userId);
    }

    @Override
    public List<Revenue> getRevenues() {
        Long userId = userHandler.getCurrentUserId();
        return revenueRepository.findByUserId(userId);
    }
}
