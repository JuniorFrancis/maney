package com.maney.api.controllers;

import com.maney.api.models.Spending;
import com.maney.api.models.responses.SpendingResponse;
import com.maney.api.services.impl.SpendingServiceImpl;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/spending")
public class SpendingController {

    @Autowired
    SpendingServiceImpl spendingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody List<Spending> spending) {
        spendingService.register(spending);
    }

    @GetMapping
    @ResponseBody
    public Page<Spending> spending(@Nonnull int page, @Nonnull int size) {

        return spendingService.getSpending(page, size);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Spending spent(@PathVariable String id) {

        return spendingService.getSpent(Long.valueOf(id));
    }

    @ResponseBody
    @GetMapping("/period")
    public SpendingResponse byPeriod(
            @Nonnull @RequestParam String period,
            @Nonnull @RequestParam List<String> brand,
            @RequestParam int page,
            @RequestParam int size) {

        return spendingService.byPeriod(LocalDate.parse(period), brand, page, size);
    }
}
