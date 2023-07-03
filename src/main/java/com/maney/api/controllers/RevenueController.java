package com.maney.api.controllers;

import com.maney.api.models.Revenue;
import com.maney.api.services.impl.RevenueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/revenue")
public class RevenueController {

    @Autowired
    RevenueServiceImpl revenueService;

    @ResponseBody
    @PostMapping
    public Revenue create(@RequestBody Revenue revenue) {

        return revenueService.create(revenue);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Optional<Revenue> getRevenue(@PathVariable("id") Long id) {

        return revenueService.getRevenue(id);
    }

    @ResponseBody
    @GetMapping
    public List<Revenue> getRevenues() {

        return revenueService.getRevenues();
    }
}
