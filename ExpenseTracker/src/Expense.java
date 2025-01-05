import java.time.LocalDate;

public class Expense {
    private final int id;
    private LocalDate date;
    private String description;
    private double amount;
    private ExpenseCategory category;
    private static int idCounter = 0;

    public Expense(String description, double amount, ExpenseCategory category) {
        this.id = idCounter++;
        this.date = LocalDate.now();
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public Expense(int id, LocalDate date, String description, double amount, ExpenseCategory category) {
        this.id = id;
        this.date = date;
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

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Expense.idCounter = idCounter;
    }
}
