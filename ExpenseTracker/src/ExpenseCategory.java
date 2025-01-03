public enum ExpenseCategory {
    FOOD("Food"),
    TRANSPORTATION ("Transportation"),
    ENTERTAINMENT ("Entertainment"),
    UTILITIES ("Utilities"),
    MISCELLANEOUS ("Miscellaneous");

    private String category;

    ExpenseCategory(String category) {
        this.category = category;
    }


}
