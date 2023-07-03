package com.maney.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maney.api.constants.RevenueType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="revenues")
public class Revenue {

    public Revenue(Long id, RevenueType revenueType, Integer amount, Boolean isFixed, LocalDate paymentDate, User user) {
        this.id = id;
        this.revenueType = revenueType;
        this.amount = amount;
        this.isFixed = isFixed;
        this.paymentDate = paymentDate;
        this.user = user;
    }

    public Revenue() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RevenueType revenueType;

    @Column(name="amount")
    private Integer amount;

    @Column(name="payment_date")
    private LocalDate paymentDate;

    @Column(name="is_fixed")
    private Boolean isFixed;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JsonIgnore
    private User user;
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

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
       this.paymentDate = paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = LocalDate.parse(paymentDate);
    }

    public Boolean getFixed() {
        return isFixed;
    }

    public void setFixed(Boolean isFixed) {
        this.isFixed = isFixed;
    }

    public void setFixed(String isFixed) {
        this.isFixed = Boolean.valueOf(isFixed);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setPaymentDate(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
