package com.maney.api.controller;

import com.maney.api.model.responses.AccountingResponse;
import com.maney.api.service.impl.AccountingServiceImpl;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/accounting")
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

    @GetMapping("/expansive-tags")
    @ResponseBody
    public AccountingResponse expansiveTags(@Nullable @RequestParam String initialPeriod, @Nullable @RequestParam String finalPeriod) {

        return accountingService.expansiveTags(initialPeriod, finalPeriod);
    }

}
