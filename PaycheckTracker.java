import javafx.geometry.Insets;


import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.StackPane;

import java.time.LocalDate;

import javafx.stage.Modality;

public class PaycheckTracker
{
    public void openPaycheckTracker()//PaycheckEntry existingEntry) 
    {
        Stage dialog = new Stage();
        
        dialog.initModality(Modality.APPLICATION_MODAL);

        //dialog.setTitle(existingEntry == null ? "Add Entry" : "Edit Entry");
        
        TextField idField = new TextField();
        TextField amountField = new TextField();
        DatePicker dateField = new DatePicker();

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        GridPane grid = new GridPane();

        grid.setHgap(10);

        grid.setVgap(10);

        grid.setPadding(new Insets(15));
        
        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);

        grid.add(new Label("Amount:"), 0, 1);
        grid.add(amountField, 1, 1);
        
        grid.add(new Label("Date:"), 0, 2);
        grid.add(dateField, 1, 2);
        
        grid.add(saveButton, 0, 3);
        grid.add(cancelButton, 1, 3);
        
        Scene scene = new Scene(grid, 400, 250);
        
        dialog.setTitle("Paycheck Tracker");
        dialog.setScene(scene);
        dialog.show();
    }
}
