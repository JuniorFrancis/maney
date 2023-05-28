package com.maney.api.service;

import com.maney.api.model.responses.AccountingResponse;
import com.maney.api.repository.projects.ProjectTagAndAmount;
import jakarta.annotation.Nullable;

import java.time.LocalDate;
import java.util.List;

public interface AccountingService {

    AccountingResponse overview(LocalDate dateToQuery);

    List<ProjectTagAndAmount> expansiveTagsByPeriod(@Nullable String initialPeriod, @Nullable String finalPeriod);

    List<ProjectTagAndAmount> expansiveTags();
}
