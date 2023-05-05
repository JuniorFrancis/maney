package com.maney.api.model;

import com.maney.api.constants.Bank;
import com.maney.api.constants.Tag;
import com.maney.api.constants.Type;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="spending")
public class Spending {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date_spending")
    private LocalDate dateSpending;

    @Column(name="amount")
    private Integer amount;

    @Column(name="external_uuid", unique = true)
    private String uuid;

    @Column(name="description")
    private String description;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name="tag")
    @Enumerated(EnumType.STRING)
    private Tag tag;

    @Column(name="estableshiment")
    private String estableshiment;

    @ManyToOne
    private Card card;

    public Spending(Long id, LocalDate dateSpending, Integer amountInCents, String uuid, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Bank bank, Type type, Card card) {
        this.id = id;
        this.dateSpending = dateSpending;
        this.uuid = uuid;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.type = type;
        this.card = card;
    }

    public Spending() {

    }

    @Override
    public String toString() {
        return "Spending{" +
                "id=" + id +
                ", dateSpending=" + dateSpending +
                ", amountInCents=" + amount +
                ", uuid='" + uuid + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", type=" + type +
                ", card=" + card +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateSpending() {
        return dateSpending;
    }

    public void setDateSpending(LocalDate dateSpending) {
        this.dateSpending = dateSpending;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public static class Builder {

    }
}
