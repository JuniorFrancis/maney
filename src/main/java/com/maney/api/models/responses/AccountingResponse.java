package com.maney.api.models.responses;

import java.time.Month;

public class AccountingResponse {

    public AccountingResponse() {
    }

    public AccountingResponse(Integer totalSpentAmount, Integer totalReceivedValue, Month period) {
        this.totalSpentAmount = totalSpentAmount;
        this.totalReceivedValue = totalReceivedValue;
        this.referenceMonth = period;
    }

    /*
     * Valor total gasto no periodo
     * */
    private Integer totalSpentAmount;

    /*
    * Valor total recebido no periodo
    * */
    private Integer totalReceivedValue;

    /*
     * periodo solicitado
     * */
    private Month referenceMonth; // periodo solicitado

    public Integer getTotalSpentAmount() {
        return totalSpentAmount;
    }

    public void setTotalSpentAmount(Integer totalSpentAmount) {
        this.totalSpentAmount = totalSpentAmount;
    }

    public Integer getTotalReceivedValue() {
        return totalReceivedValue;
    }

    public void setTotalReceivedValue(Integer totalReceivedValue) {
        this.totalReceivedValue = totalReceivedValue;
    }

    public Month getReferenceMonth() {
        return referenceMonth;
    }

    public void setReferenceMonth(Month referenceMonth) {
        this.referenceMonth = referenceMonth;
    }

    public static class Builder {

        private Integer totalSpentAmount;

        private Integer totalReceivedValue;

        private Month referenceMonth;

        public Builder withTotalSpentAmount(Integer totalSpentAmount) {
            this.totalSpentAmount = totalSpentAmount;
            return this;
        }

        public Builder withTotalReceivedValue(Integer totalReceivedValue) {
            this.totalReceivedValue = totalReceivedValue;
            return this;
        }

        public Builder withReferenceMonth(Month referenceMonth) {
            this.referenceMonth = referenceMonth;
            return this;
        }

        public AccountingResponse build() {
            return new AccountingResponse(totalSpentAmount, totalReceivedValue, referenceMonth);
        }

    }
}
