package expense_tracker.model;

import java.time.LocalDate;

public class Expense {
    private Long id;
    private String description;
    private Float amount;
    private LocalDate date;
    private static Long aux = 0L;

    public Expense (Long id, LocalDate date, String description, Float amount) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public Expense (String description, Float amount, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        id = aux + 1;
        aux = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Float getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
