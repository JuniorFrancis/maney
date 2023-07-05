package com.maney.api.controllers;

import com.maney.api.models.Revenue;
import com.maney.api.services.impl.RevenueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/revenue")
public class RevenueController {

    @Autowired
    RevenueServiceImpl revenueService;

    @ResponseBody
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Revenue revenue) {

        revenueService.create(revenue);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Revenue getRevenue(@PathVariable("id") Long id) {

        return revenueService.getRevenue(id);
    }

    @ResponseBody
    @GetMapping
    public Page<Revenue> getRevenues(@RequestParam int page, @RequestParam int size) {

        return revenueService.getRevenues(page, size);
    }

    @ResponseBody
    @GetMapping("/period")
    public Page<Revenue> getRevenues(@RequestParam String period, @RequestParam int page, @RequestParam int size) {

        return revenueService.getRevenuesByPeriod(period, page, size);
    }
}
