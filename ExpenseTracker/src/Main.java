import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Check if there are no arguments
        if (args.length == 0) {
            System.out.println("Please provide an input");
            showUsage();
            return;
        }

        ExpenseManager expenseManager = new ExpenseManager();

        // Get the command from the first argument
        String command = args[0];

        switch (command) {
            // Case to add a new expense
            case "add":
                try {
                    // Parse the arguments to a hashmap with description and amount as keys and their values as values
                    Map<String, String> addArguments = parseArguments(args);
                    String description = addArguments.get("--description");
                    String amountString = addArguments.get("--amount");

                    if (description == null || amountString == null) {
                        throw new IllegalArgumentException("Error, missing --description or --amount");
                    }

                    // Parse the amount to a double and add the expense
                    double amount = Double.parseDouble(amountString);
                    expenseManager.addExpense(description, amount);
                    expenseManager.saveTasks();

                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error, invalid input");
                }
                break;

            // Case to update an existing expense
            case "update":
                try {
                    Map<String, String> updateArguments = parseArguments(args);
                    String idStr = updateArguments.get("--id");
                    String description = updateArguments.get("--description");
                    String amountString = updateArguments.get("--amount");

                    if (idStr == null || description == null || amountString == null) {
                        throw new IllegalArgumentException("Error, missing --description or --amount");
                    }

                    // Parse the id and amount to update the expense
                    int id = Integer.parseInt(idStr);
                    double amount = Double.parseDouble(amountString);
                    expenseManager.updateExpense(id, description, amount);
                    expenseManager.saveTasks();

                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error, invalid input");
                }
                break;

            // Case to delete an existing expense
            case "delete":
                try {
                    // Check if the id is provided
                    if (args.length < 2) {
                        throw new IllegalArgumentException("Error, missing id");
                    } else {
                        // Parse the id to delete the expense
                        int id = Integer.parseInt(args[1]);
                        expenseManager.deleteExpense(id);
                    }
                    expenseManager.saveTasks();

                } catch (NumberFormatException e) {
                    System.out.println("Invalid id");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error, invalid input");
                }
                break;

            // Case to list all expenses
            case "list":
                expenseManager.listExpenses();
                break;

            // Case to show a summary of all expenses
            case "summary":
                if (args.length == 1) {
                    expenseManager.summary();
                } else {
                    try {
                        Map<String, String> summaryArguments = parseArguments(args);
                        String monthStr = summaryArguments.get("--month");
                        String yearString = summaryArguments.get("--year");

                        if (monthStr == null || yearString == null) {
                            throw new IllegalArgumentException("Error, missing --month or --year");
                        }

                        // Parse the month and year to show the summary
                        int month = monthStr.length() == 1 ? Integer.parseInt("0" + monthStr) : Integer.parseInt(monthStr);
                        int year = Integer.parseInt(yearString);
                        expenseManager.summaryMonth(month, year);

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid year");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error, invalid input");
                    }
                }
                break;
        }
    }

    private static Map<String, String> parseArguments(String[] argsArray) {
        // Create a hashmap to store the arguments
        Map<String, String> arguments = new HashMap<>();
        for (int i = 0; i < argsArray.length; i++) {
            // Check if the argument starts with "--" and assign it to a key
            if (argsArray[i].startsWith("--")) {
                String key = argsArray[i];
                // Check if the next argument is not another key and assign it to the key's value
                if (i + 1 < argsArray.length && !argsArray[i + 1].startsWith("--")) {
                    String value = argsArray[i + 1];
                    arguments.put(key, value);
                    i++;
                }
            }
        }
        return arguments;
    }

    // Show the usage of the program
    private static void showUsage() {
        System.out.println("Usage: <option> <arguments>");
        System.out.println(" add --description <description> --amount <amount> -> Add a new expense");
        System.out.println(" update --id <id> --description <description> --amount <amount> -> Update an existing expense");
        System.out.println(" delete --id <id> -> Delete an existing expense by id");
        System.out.println(" list -> List all expenses");
        System.out.println(" summary -> Show a summary of all expenses");
        System.out.println(" summary --month <month 1-12 > --year <year> -> Show a summary of all expenses for a specific month and year");
    }
}