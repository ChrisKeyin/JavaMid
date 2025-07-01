package models;

import java.time.LocalDate;

public class Medication {
    private int id;
    private String name;
    private String dose;
    private int quantityInStock;
    private LocalDate expiryDate;

    public Medication(int id, String name, String dose, int quantityInStock, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getName() {
        return name;
    }

    public void reduceStock(int amount) {
        this.quantityInStock -= amount;
            if (this.quantityInStock < 0) {
                this.quantityInStock = 0;
            }
    }

    public String getDose() {
        return dose;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public int getId() {
        return id;
    }


}
