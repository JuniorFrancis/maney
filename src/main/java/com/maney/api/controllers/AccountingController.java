package com.maney.api.controllers;

import com.maney.api.models.responses.AccountingResponse;
import com.maney.api.repositories.projects.ProjectTagAndAmount;
import com.maney.api.services.impl.AccountingServiceImpl;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounting")
public class AccountingController {

    @Autowired
    public AccountingController(AccountingServiceImpl accountingService) {
        this.accountingService = accountingService;
    }

    private final AccountingServiceImpl accountingService;

    @GetMapping
    @ResponseBody
    public AccountingResponse overview(@Nonnull @RequestParam String period) {

        return accountingService.overview(period);
    }

    @ResponseBody
    @GetMapping("/expansive-tags-by-period")
    public List<ProjectTagAndAmount> expansiveTagsByPeriod(@Nullable @RequestParam String initialPeriod, @Nullable @RequestParam String finalPeriod) {

        return accountingService.expansiveTagsByPeriod(initialPeriod, finalPeriod);
    }

    @ResponseBody
    @GetMapping("/expansive-tags")
    public List<ProjectTagAndAmount> expansiveTags() {

        return accountingService.expansiveTags();
    }

}
