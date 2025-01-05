public enum ExpenseCategory {
    FOOD("Food"),
    TRANSPORTATION ("Transportation"),
    ENTERTAINMENT ("Entertainment"),
    UTILITIES ("Utilities"),
    MISCELLANEOUS ("Miscellaneous");

    private final String category;

    ExpenseCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
