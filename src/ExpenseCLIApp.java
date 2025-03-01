import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExpenseCLIApp {
    public static void main(String[] args) {

        // Check if there are no arguments
        if (args.length == 0) {
            System.out.println("Please provide an input");
            showUsage();
            return;
        }

        ExpenseManager expenseManager = new ExpenseManager();

        // Get the command from the first argument
        String command = args[0].toLowerCase();

        switch (command) {
            // Case to show the usage
            case "-h":
                showUsage();
                break;

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
                    System.out.println("Select a category:");
                    showCategories();
                    Scanner sc = new Scanner(System.in);
                    int category = sc.nextInt();
                    switch (category) {
                        case 1:
                            expenseManager.addExpense(description, amount, ExpenseCategory.FOOD);
                            break;
                        case 2:
                            expenseManager.addExpense(description, amount, ExpenseCategory.TRANSPORTATION);
                            break;
                        case 3:
                            expenseManager.addExpense(description, amount, ExpenseCategory.ENTERTAINMENT);
                            break;
                        case 4:
                            expenseManager.addExpense(description, amount, ExpenseCategory.UTILITIES);
                            break;
                        case 5:
                            expenseManager.addExpense(description, amount, ExpenseCategory.MISCELLANEOUS);
                            break;
                        default:
                            System.out.println("Invalid category");
                            break;
                    }
                    expenseManager.saveTasks();
                    sc.close();

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
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Do you want to change the category? (y/n)");
                    String changeCategory = sc.next();
                    // Check if the user wants to change the category
                    if (changeCategory.equals("y")) {
                        System.out.println("Select a category:");
                        showCategories();
                        int category = sc.nextInt();
                        switch (category) {
                            case 1:
                                expenseManager.updateExpense(id, description, amount, ExpenseCategory.FOOD);
                                break;
                            case 2:
                                expenseManager.updateExpense(id, description, amount, ExpenseCategory.TRANSPORTATION);
                                break;
                            case 3:
                                expenseManager.updateExpense(id, description, amount, ExpenseCategory.ENTERTAINMENT);
                                break;
                            case 4:
                                expenseManager.updateExpense(id, description, amount, ExpenseCategory.UTILITIES);
                                break;
                            case 5:
                                expenseManager.updateExpense(id, description, amount, ExpenseCategory.MISCELLANEOUS);
                                break;
                            default:
                                System.out.println("Invalid category");
                                break;
                        }
                    } else {
                        expenseManager.updateExpense(id, description, amount);
                    }
                    expenseManager.saveTasks();
                    sc.close();

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
                        int id = Integer.parseInt(args[2]);
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

            // Case to show a summary report
            case "summary":
                if (args.length < 2) {
                    System.out.println("Error, missing option");
                    showUsage();
                    return;
                }

                String optionSummary = args[1].toLowerCase();
                switch (optionSummary){
                    // Show the summary of all expenses
                    case "-all":
                        expenseManager.summary();
                        break;

                    case "--category":
                        try {
                            Map<String, String> summaryArguments = parseArguments(args);
                            String category = summaryArguments.get("--category");

                            if (category == null) {
                                throw new IllegalArgumentException("Error, missing --category");
                            }

                            expenseManager.summaryCategory(ExpenseCategory.valueOf(category.toUpperCase()));

                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Error, invalid input");
                        }
                        break;

                    case "--month":
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
                        break;

                    case "--year":
                        try {
                            Map<String, String> summaryArguments = parseArguments(args);
                            String yearString = summaryArguments.get("--year");

                            if (yearString == null) {
                                throw new IllegalArgumentException("Error, missing --year");
                            }

                            // Parse the year to show the summary
                            int year = Integer.parseInt(yearString);
                            expenseManager.summaryYear(year);

                        } catch (NumberFormatException e) {
                            System.out.println("Invalid year");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Error, invalid input");
                        }
                        break;

                    // por si el comando no es ninguno de los anteriores
                    default:
                        System.out.println("Invalid command");
                        showUsage();
                        break;
                }
        }
    }

    // Parse the arguments to a hashmap
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
        System.out.println(" add --d <description> --a <amount> -> Add a new expense");
        System.out.println(" update --id <id> --d <description> --a <amount> -> Update an existing expense");
        System.out.println(" delete --id <id> -> Delete an existing expense by id");
        System.out.println(" list -> List all expenses");
        System.out.println(" summary -all -> Show a summary of all expenses");
        System.out.println(" summary --month <month 1-12 > --year <year> -> Show a summary of all expenses for a specific month and year");
        System.out.println(" summary --year <year> -> Show a summary of all expenses for a specific year");
        System.out.println(" summary --category <category> -> Show a summary of all expenses for a specific category");
    }

    public static void showCategories() {
        System.out.println("""
                Categories:
                1. Food
                2. Transportation
                3. Entertainment
                4. Utilities
                5. Miscellaneous
                """);
    }
}