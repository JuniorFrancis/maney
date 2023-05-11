package com.maney.api.service;

import com.maney.api.model.Revenue;

import java.util.List;
import java.util.Optional;

public interface RevenueService {

    Revenue create(Revenue revenue);

    Optional<Revenue> getRevenue(Long id);

    List<Revenue> getRevenues();
}
