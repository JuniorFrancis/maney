package com.maney.api.controller;

import com.maney.api.model.responses.AccountingResponse;
import com.maney.api.repository.projects.ProjectTagAndAmount;
import com.maney.api.service.impl.AccountingServiceImpl;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

        return accountingService.overview(LocalDate.parse(period));
    }

    @GetMapping("/expansive-tags-by-period")
    @ResponseBody
    public List<ProjectTagAndAmount> expansiveTagsByPeriod(@Nullable @RequestParam String initialPeriod, @Nullable @RequestParam String finalPeriod) {

        return accountingService.expansiveTagsByPeriod(initialPeriod, finalPeriod);
    }

    @GetMapping("/expansive-tags")
    @ResponseBody
    public List<ProjectTagAndAmount> expansiveTags() {

        return accountingService.expansiveTags();
    }

}
