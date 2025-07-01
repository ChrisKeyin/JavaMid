package models;

import java.time.LocalDate;

/**
 * Represents a medication in the pharma system.
 * Contains information about the medication's ID, name, dose, quantity in stock, and expiry date.
 */
public class Medication {
    private int id;
    private String name;
    private String dose;
    private int quantityInStock;
    private LocalDate expiryDate;

    /**
     * Constructs a Medication object.
     *
     * @param id              the medication ID
     * @param name            the name of the medication
     * @param dose            the dosage information
     * @param quantityInStock the quantity available in stock
     * @param expiryDate      the expiry date of the medication
     */
    public Medication(int id, String name, String dose, int quantityInStock, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the quantity of medication in stock.
     *
     * @return the quantity in stock
     */
    public int getQuantityInStock() {
        return quantityInStock;
    }

    /**
     * Sets the quantity of medication in stock.
     *
     * @param quantityInStock the new quantity in stock
     */
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    /**
     * Gets the name of the medication.
     *
     * @return the medication name
     */
    public String getName() {
        return name;
    }

    /**
     * Reduces the stock by the specified amount. If the resulting stock is negative, it is set to zero.
     *
     * @param amount the amount to reduce from stock
     */
    public void reduceStock(int amount) {
        this.quantityInStock -= amount;
        if (this.quantityInStock < 0) {
            this.quantityInStock = 0;
        }
    }

    /**
     * Gets the dosage information of the medication.
     *
     * @return the dose
     */
    public String getDose() {
        return dose;
    }

    /**
     * Gets the expiry date of the medication.
     *
     * @return the expiry date
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Gets the ID of the medication.
     *
     * @return the medication ID
     */
    public int getId() {
        return id;
    }


}
