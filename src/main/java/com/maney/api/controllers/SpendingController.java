package com.maney.api.controllers;

import com.maney.api.models.Spending;
import com.maney.api.services.impl.SpendingServiceImpl;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Spending> spending() {

        return spendingService.getSpending();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Spending spent(@PathVariable String id) {

        return spendingService.getSpent(Long.valueOf(id));
    }

    @ResponseBody
    @GetMapping("/period")
    public List<Spending> byPeriod(@Nonnull @RequestParam String period, @Nonnull @RequestParam List<String> brand) {

        return spendingService.byPeriod(LocalDate.parse(period), brand);
    }
}
