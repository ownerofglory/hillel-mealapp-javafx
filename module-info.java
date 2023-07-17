module ua.ithillel.mealapp {
    requires javafx.fxml;
    requires javafx.controls;
    opens ua.ithillel.mealapp to javafx.graphics;
    exports ua.ithillel.mealapp;
}
