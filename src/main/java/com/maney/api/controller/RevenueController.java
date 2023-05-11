package com.maney.api.controller;

import com.maney.api.exceptions.DefaultException;
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

        //TODO Validar se quando o valor recebido não for fixo, é obrigatório o paymentDate.
        //TODO Para isso, preciso colocar um response body na exception de error
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
