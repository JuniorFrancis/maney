package com.maney.api.service.impl;

import com.maney.api.exceptions.DefaultException;
import com.maney.api.handlers.UserHandler;
import com.maney.api.model.Revenue;
import com.maney.api.model.User;
import com.maney.api.repository.RevenueRepository;
import com.maney.api.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new IllegalArgumentException("PaymentDate is necessary to fixed payment");
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
