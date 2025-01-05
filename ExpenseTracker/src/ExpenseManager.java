import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    private final Path FILE_PATH = Path.of("expenses.json");

    public ExpenseManager() {
        expenses = loadTasks();
    }

    public void saveTasks() {
        // use a StringBuilder to create a string with all the tasks
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        int index = 0;
        int size = expenses.size();
        for (Expense expense : expenses) {
            sb.append("{\n");
            sb.append("  \"id\": ").append(expense.getId()).append(",\n");
            sb.append("  \"date\": \"").append(expense.getDate()).append("\",\n");
            sb.append("  \"description\": \"").append(expense.getDescription()).append("\",\n");
            sb.append("  \"amount\": ").append(expense.getAmount()).append(",\n");
            sb.append("  \"category\": \"").append(expense.getCategory()).append("\"\n");
            sb.append("}\n");
            if (index < size - 1) {
                sb.append(",\n");
            } index++;

        }
        sb.append("]");
        String tasks = sb.toString();
        // save the string to a file
        try {
            Files.writeString(FILE_PATH, tasks);
            System.out.println("Tasks saved to file");

        } catch (IOException e) {
            System.out.println("Error saving tasks to file");
        }
    }

    private List<Expense> loadTasks() {
        // load the tasks from a file
        if (!Files.exists(FILE_PATH)) {
            return new ArrayList<>();
        }
        String JsonExpenses = "";
        try {
            JsonExpenses = Files.readString(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error loading tasks from file");
        }
        // check if the file is empty
        if (JsonExpenses.equals("[]") || JsonExpenses.isEmpty()) return new ArrayList<>();

        // parse the string to create the tasks
        List<Expense> stored_expenses = new ArrayList<>();
        JsonExpenses = JsonExpenses.replace("[", "")
                .replace("]", "");
        String[] expenses = JsonExpenses.split("},");
        System.out.println(Arrays.toString(expenses));
        for (String expense : expenses) {
            expense = expense.replace("{", "");
            expense = expense.replace("}", "");
            String[] fields = expense.split(",");
            int id = Integer.parseInt(fields[0].strip().split(":")[1].strip());
            String date = fields[1].strip().split(":")[1].strip();
            LocalDate localDate = LocalDate.parse(date);
            String description = fields[2].strip().split(":")[1].strip();
            double amount = Double.parseDouble(fields[3].strip().split(":")[1].strip());
            String category = fields[4].strip().split(":")[1].strip();

            Expense storedExpense= new Expense(id, localDate, description, amount, ExpenseCategory.valueOf(category));
            stored_expenses.add(storedExpense);
        }
        return stored_expenses;
    }

    // metodo para agregar una expense con determinada description y monto
    public void addExpense(String description, double amount) {
        Expense expense = new Expense(description, amount, null);
        expenses.add(expense);
        System.out.println("Expense added successfully, ID: " + expense.getId());
    }

    public void updateExpense(int id, String description, double amount) {
        // buscar si existe la expense con él, id dado
        Expense expense = getExpenseById(id);
        if (expense == null) {
            System.out.println("Error, expense not found");
        } else {
            expense.setDescription(description);
            expense.setAmount(amount);
            System.out.println("Expense updated successfully");
        }
    }

    public void deleteExpense(int id) {
        // buscar si existe la expense con él, id dado
        Expense expense = getExpenseById(id);
        if (expense == null) {
            System.out.println("Error, expense not found");
        } else {
            expenses.remove(expense);
            System.out.println("Expense deleted successfully");
        }
    }

    public void listExpenses() {
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
    public void summary() {
        double total = 0;
        // Recorrer la lista de expenses y sumar los montos
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        System.out.println("Total expenses: " + total);
    }

    // imprimir el total de dinero gastado en un mes específico
    public void summaryMonth(int month, int year) {
        double total = 0;
        for (Expense expense : expenses) {
            // Verificar si la fecha de la expense coincide con el mes y año dados
            if (expense.getDate().getYear() == year && expense.getDate().getMonthValue() == month) {
                total += expense.getAmount();
            }
        }
        System.out.println("Total expenses for " + month + ": " + total);
    }

    // metodo para obtener una expense por su id
    private Expense getExpenseById(int id) {
        // buscar la expense con el id dado en la lista de expenses
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                return expense;
            }
        }
        return null;
    }
}