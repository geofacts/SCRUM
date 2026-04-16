import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;

import javafx.application.Application;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import javafx.scene.control.TableView;

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
    public static PaycheckEntry openPaycheckTracker(PaycheckEntry existingEntry) 
    {
        Stage dialog = new Stage();
        
        dialog.initModality(Modality.APPLICATION_MODAL);

        dialog.setTitle(existingEntry == null ? "Add Entry" : "Edit Entry");
        
        TextField idField = new TextField();
        TextField amountField = new TextField();
        DatePicker dateField = new DatePicker();
        
        if (existingEntry != null) {
            idField.setText(existingEntry.getId());
            amountField.setText(Double.toString(existingEntry.getAmount()));
            dateField.setValue(existingEntry.getDateObtained());
        }
        
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        
        final PaycheckEntry[] result = {null};

        saveButton.setOnAction(
            e -> { 
                String inputId = idField.getText().trim();
                String inputAmount = amountField.getText().trim();
                LocalDate inputDate = dateField.getValue();
                //String verificationCode = verificationField.getText().trim();
                
                // make sure the fields are not empty
                if (inputId.isEmpty() || inputAmount.isEmpty() || inputDate == null)
                {
                    showAlert("Missing Info", "Please fill in ALL fields.");
                    return;
                }
                
                // try to get the double value. if it doesn't work, remind user to do so
                double retrievedAmount;
                try
                {
                    retrievedAmount = Double.parseDouble(inputAmount);
                }
                catch (NumberFormatException error)
                {
                    showAlert("Invalid amount", "Please enter a positive numeric amount.");
                    return;
                }
                
                // make sure the double value is not negative. this is a paycheck, you should not be losing money by earning them.
                if (retrievedAmount < 0)
                {
                    showAlert("Negative amount", "Please enter a positive numeric amount.");
                    return;
                }
                
                // make sure the date is not in the future
                if (inputDate.isAfter(LocalDate.now()))
                {
                    showAlert("Invalid date", "Input date cannot be in the future");
                    return;
                }
                
                try
                {
                    // String uid = generateRandomId(64);
                    result[0] = new PaycheckEntry(inputId, retrievedAmount, inputDate);
                    
                    dialog.close();
                }
                catch (Exception err)
                {
                    showAlert("Error", "Sorry, there was an error processing your message: " + err);
                }
            }
        );

        cancelButton.setOnAction(e -> dialog.close());
        
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
        dialog.showAndWait();
        
        return result[0];
    }
    
    private static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private static void showAlert(String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}