import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    private static final List<Expense> expenses = new ArrayList<>();

    public static void saveTasks(){
        // save the tasks to a file
    }

    public static void loadTasks(){
        // load the tasks from a file
    }

    // metodo para agregar una expense con determinada description y monto
    public static void addExpense(String description, double amount) {
        Expense expense = new Expense(description, amount, null);
        expenses.add(expense);
        System.out.println("Expense added successfully, ID: " + expense.getId());
    }

    public static void updateExpense(int id, String description, double amount) {
        // buscar si existe la expense con él, id dado
        Expense expense = getExpenseById(id);
        if (expense == null) {
            System.out.println("Error, expense not found");
            return;
        } else {
            expense.setName(description);
            expense.setAmount(amount);
            System.out.println("Expense updated successfully");
        }
    }

    public static void deleteExpense(int id) {
        // buscar si existe la expense con él, id dado
        Expense expense = getExpenseById(id);
        if (expense == null) {
            System.out.println("Error, expense not found");
            return;
        } else {
            expenses.remove(expense);
            System.out.println("Expense deleted successfully");
        }
    }

    public static void listExpenses() {
        // imprimir la lista de expenses con un formato específico
        System.out.printf("%-10s | %-15s | %-30s  | %10s | %-15s%n", "ID", "Date", "Description", "Amount", "Category");
        for (Expense expense : expenses) {
            System.out.printf("%-10s | %-15s | %-30s  | %10.2f | %-15s%n",
                    expense.getId(),
                    expense.getDate(),
                    expense.getDescription(),
                    expense.getAmount(),
                    expense.getCategory());
        }
    }

    // imprimir el total de dinero gastado
    public static void summary() {
        double total = 0;
        // Recorrer la lista de expenses y sumar los montos
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        System.out.println("Total expenses: " + total);
    }

    // imprimir el total de dinero gastado en un mes específico
    public static void summaryMonth(Month month, int year) {
        double total = 0;
        for (Expense expense : expenses) {
            // Verificar si la fecha de la expense coincide con el mes y año dados
            if (expense.getDate().getYear() == year && expense.getDate().getMonth() == month) {
                total += expense.getAmount();
            }
        }
        System.out.println("Total expenses for " + month + ": " + total);
    }

    // metodo para obtener una expense por su id
    private static Expense getExpenseById(int id) {
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                return expense;
            }
        }
        return null;
    }




}
