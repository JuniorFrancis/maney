package com.maney.api.services;

import com.maney.api.models.Revenue;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RevenueService {

    Revenue create(Revenue revenue);

    Revenue getRevenue(Long id);

    List<Revenue> getRevenues();

    Page<Revenue> getRevenues(int page, int size);
}
