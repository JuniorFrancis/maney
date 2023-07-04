package com.maney.api.models.responses;

import com.maney.api.models.Spending;

import java.util.List;

public record SpendingResponse(
        int totalPages,
        long totalElements,
        List<Spending> content

    ) { }
