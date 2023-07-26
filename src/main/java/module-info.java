module io.droksty.shiftplannerapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.droksty.shiftplannerapp to javafx.fxml;
    exports io.droksty.shiftplannerapp;
}