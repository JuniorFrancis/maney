package com.maney.api.services;

import com.maney.api.models.Revenue;

import java.util.List;
import java.util.Optional;

public interface RevenueService {

    Revenue create(Revenue revenue);

    Optional<Revenue> getRevenue(Long id);

    List<Revenue> getRevenues();
}
