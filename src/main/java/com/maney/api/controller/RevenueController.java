package com.maney.api.controller;

import com.maney.api.model.Revenue;
import com.maney.api.service.impl.RevenueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

    @Autowired
    RevenueServiceImpl revenueService;

    @ResponseBody
    @PostMapping
    public Revenue create(@RequestBody Revenue revenue) {

        return revenueService.create(revenue);
    }

    @ResponseBody
    @PostMapping
    public Optional<Revenue> getRevenue(@RequestBody Long id) {

        return revenueService.getRevenue(id);
    }

    @ResponseBody
    @PostMapping
    public List<Revenue> getRevenues() {

        return revenueService.getRevenues();
    }
}
