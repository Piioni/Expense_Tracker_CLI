import java.time.LocalDate;
import java.util.UUID;

public class Expense {
    private UUID id;
    private LocalDate date;
    private String description;
    private double amount;
    private ExpenseCategory category;

    public Expense(String description, double amount, ExpenseCategory category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setName(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }


}
