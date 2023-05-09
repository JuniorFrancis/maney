package com.maney.api.controller;

import com.maney.api.constants.Brand;
import com.maney.api.model.Spending;
import com.maney.api.service.impl.SpendingServiceImpl;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spending")
public class SpendingController {

    //TODO Adicionar log4j e logar eventos.

    @Autowired
    SpendingServiceImpl spendingService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody List<Spending> spending) {

        spending.forEach( spent -> {
            spendingService.register(spent);
        });

        return ResponseEntity.ok().body("Gasto registrado!");
    }

    @ResponseBody
    @GetMapping
    public List<Spending> spending() {

        return spendingService.getSpending();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Spending> spent(@PathVariable String id) {

        return spendingService.getSpent(Long.valueOf(id));
    }

    @GetMapping("/period")
    @ResponseBody
    public List<Spending> byPeriod(@Nonnull @RequestParam String period, @Nonnull @RequestParam List<String> brand) {

        return spendingService.byPeriod(LocalDate.parse(period), brand);
    }
}
