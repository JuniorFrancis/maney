package com.maney.api.model;

import com.maney.api.constants.Bank;
import com.maney.api.constants.Brand;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="cards")
public class Card {

    public Card(){

    }

    public Card(Long id){
        this.id= id;
    }
    public Card(Long id, Bank bank, Brand brand, String lastDigits) {
        this.id = id;
        this.bank = bank;
        this.brand = brand;
        this.lastDigits = lastDigits;

        LocalDateTime currentDate = LocalDateTime.now();

        this.createdAt = currentDate;
        this.updatedAt = currentDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="bank")
    @Enumerated(EnumType.STRING)
    private Bank bank;

    @Column(name="brand")
    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="due_day")
    private Integer dueDay;

    @Column(name="last_digits")
    private String lastDigits;

    public Integer getDueDay() {
        return dueDay;
    }

    public void setDueDay(Integer dueDay) {
        this.dueDay = dueDay;
    }

    public String getLastDigits() {
        return lastDigits;
    }

    public void setLastDigits(String lastDigits) {
        this.lastDigits = lastDigits;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "id=" + id +
                ", bank=" + bank +
                ", brand=" + brand +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", lastDigits='" + lastDigits + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
