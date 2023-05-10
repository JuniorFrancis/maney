package com.maney.api.model;

import com.maney.api.constants.RevenueType;
import jakarta.persistence.*;

@Entity
@Table(name = "revenues")
public class Revenue {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RevenueType revenueType;

    @Column(name="amount")
    private Integer amount;

    @Column(name="is_fixed")
    private Boolean isFixed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RevenueType getRevenueType() {
        return revenueType;
    }

    public void setRevenueType(RevenueType revenueType) {
        this.revenueType = revenueType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getFixed() {
        return isFixed;
    }

    public void setFixed(Boolean fixed) {
        isFixed = fixed;
    }
}
