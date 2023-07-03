package com.maney.api.services;

import com.maney.api.models.responses.AccountingResponse;
import com.maney.api.repositories.projects.ProjectTagAndAmount;
import jakarta.annotation.Nullable;

import java.util.List;

public interface AccountingService {

    AccountingResponse overview(String dateToQuery);

    List<ProjectTagAndAmount> expansiveTagsByPeriod(@Nullable String initialPeriod, @Nullable String finalPeriod);

    List<ProjectTagAndAmount> expansiveTags();
}
