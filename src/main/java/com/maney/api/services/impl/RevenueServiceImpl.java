package com.maney.api.services.impl;

import com.maney.api.handlers.UserHandler;
import com.maney.api.models.Revenue;
import com.maney.api.repositories.RevenueRepository;
import com.maney.api.services.RevenueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Revenue getRevenue(Long id) {
        Long userId = userHandler.getCurrentUserId();
        return revenueRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new IllegalArgumentException("received revenue id not exists")
        );
    }

    @Override
    public Page<Revenue> getRevenues(int page, int size) {
        checkNotNull(page, "Page parameter cannot be null");
        checkNotNull(size, "Size parameter cannot be null");

        Long userId = userHandler.getCurrentUserId();
        PageRequest pageRequest = PageRequest.of(page, size);

        return revenueRepository.findByUserId(userId, pageRequest);
    }

    @Override
    public List<Revenue> getRevenues() {
        Long userId = userHandler.getCurrentUserId();

        return revenueRepository.findByUserId(userId);
    }
}
