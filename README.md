# Expense Tracker CLI
This is a simple command-line application to help users manage and track their daily expense,  It supports adding, updating, deleting, and listing expenses, as well as providing summary reports of expenses over a specified period. All expenses are stored as JSON objects in a file for persistence.

It is inspired from the [Expense Tracker](https://roadmap.sh/projects/expense-tracker) project from [roadmap.sh](https://roadmap.sh/).

# Features
- Add expenses: Easily add expenses with a description, amount, and category.
- Update an expense: Update the description, amount, or category of an expense.
- Delete an expense: Delete an expense by its ID.
- List all expenses: List all expenses in the system.
- Get expense summary for a specified month
- Get expense summary for the current year
- Get expense summary for all time
- Get expense summary for a specified category

# Installation
Ensure you have the Java Development Kit (JDK) Installed on your system, as well as Git.

1. clone the repository using the following command:
    ```bash
    git clone https://github.com/Piioni/Expense_Tracker_CLI.git 
    cd Expense_Tracker_CLI/src 
    ```
2. Compile the source code using the following command:
    ```bash
    javac ExpenseCLIApp.java Expense.java ExpenseManager.java ExpenseCategory.java
    ```
3. Run the application using the following command:
    ```bash
    java ExpenseCLIApp
    ```
# Usage
The application provides a simple command-line interface to interact with the expense tracker. The following commands are available:
* `add --d <description> --a <amount>`: Add a new expense with the specified description, amount, and category.
* `update --id <id> --d <description> --a <amount> `: Update the expense with the specified ID with the new description, amount, and category.
* `delete --id <id>`: Delete the expense with the specified ID.
* `list`: List all expenses in the system.
* `summary --all`: Get the expense summary for all time.
* `summary --month <month> --year <year>`: Get the expense summary for the specified month and year.
* `summary --year`: Get the expense summary for the current year.
* `summary --category <category>`: Get the expense summary for the specified category.

You need to type the \<option> and \<argument> in the command line to perform the operation.

# Example
   ```bash
    # Add two expenses
    ExpenseCLIApp add --description Lunch --amount 10;
    ExpenseCLIApp add --description Dinner --amount 20;
    
    # Update the first expense
    ExpenseCLIApp update --id 0 --description Lunch --amount 15;
    
    # List all expenses
    ExpenseCLIApp list;
    
    # Get the expense summary for all time
    ExpenseCLIApp summary --all;
    
    # Get the expense summary for the current month
    ExpenseCLIApp summary --month 3 --year 2024;
    
    # Get the expense summary for the current year
    ExpenseCLIApp summary --year 2024;
    
    # Get the expense summary for a specific category
    ExpenseCLIApp summary --category Food;
  
    
   ```

# Notes
- The application expenses are stored in `expenses.json` located in the project directory.
- If the JSON file is missing, the application creates one on startup.

# Contributing
If you'd like to contribute to this project, please fork the repository and submit a pull request with your changes.