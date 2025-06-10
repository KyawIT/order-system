package at.htl.dtos;

import java.util.Date;

public class PostOrderDto {
    private Double price;
    private int ktmScore;
    private Long[] dishIds;
    private Long[] drinkIds;
    private Long customerId;
    private Date date;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getKtmScore() {
        return ktmScore;
    }

    public void setKtmScore(int ktmScore) {
        this.ktmScore = ktmScore;
    }

    public Long[] getDishIds() {
        return dishIds;
    }

    public void setDishIds(Long[] dishIds) {
        this.dishIds = dishIds;
    }

    public Long[] getDrinkIds() {
        return drinkIds;
    }

    public void setDrinkIds(Long[] drinkIds) {
        this.drinkIds = drinkIds;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
