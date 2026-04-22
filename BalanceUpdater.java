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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class BalanceUpdater
{
    protected boolean addMode;
    protected boolean removeMode;
    protected boolean setMode;
    
    public BalanceUpdater(boolean a, boolean r, boolean s)
    {
        addMode = a;
        removeMode = r;
        setMode = s;
    }
    
    public void openBalanceUpdater(Dashboard referer)
    {
        Stage dialog = new Stage();
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        
        // TextField idField = new TextField();
        TextField amountField = new TextField();

        // Button addButton = new Button("Add this amount (Unselected)");
        // Button removeButton = new Button("Remove this amount (Unselected)");
        // Button setButton = new Button("Set to this amount (Unselected)");
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);

        grid.setPadding(new Insets(5));
        
        // grid.add(new Label("ID:"), 0, 0);
        // grid.add(idField, 1, 0);

        grid.add(new Label("Amount:"), 0, 1);
        grid.add(amountField, 1, 1);
        
        // grid.add(addButton, 0, 2);
        // grid.add(removeButton, 0, 3);
        // grid.add(setButton, 0, 4);
        
        grid.add(saveButton, 0, 3);
        grid.add(cancelButton, 1, 3);
        
        Scene scene = new Scene(grid, 290, 100);
        
        dialog.setTitle("Balance Updater");
        if (addMode)
        {
            dialog.setTitle("Add to Balance");
        }
        if (removeMode)
        {
            dialog.setTitle("Remove from Balance");
        }
        if (setMode)
        {
            dialog.setTitle("Set new Balance");
        }
        
        dialog.setScene(scene);
        dialog.show();
        /*
        addButton.setOnAction(e ->
            {
                if (removeMode)
                {
                    removeMode = false;
                }
                
                if (setMode)
                {
                    setMode = false;
                }
                
                addMode = true;
                
                addButton.setText("Add this amount (Selected)");
                removeButton.setText("Remove this amount");
                setButton.setText("Set to this amount");
            }
        );
        
        removeButton.setOnAction(e ->
            {
                if (addMode)
                {
                    addMode = false;
                }
                
                if (setMode)
                {
                    setMode = false;
                }
                
                removeMode = true;
                
                addButton.setText("Add this amount");
                removeButton.setText("Remove this amount (Selected)");
                setButton.setText("Set to this amount");
            }
        );
        
        setButton.setOnAction(e ->
            {
                if (addMode)
                {
                    addMode = false;
                }
                
                if (removeMode)
                {
                    removeMode = false;
                }
                
                setMode = true;
                
                addButton.setText("Add this amount");
                removeButton.setText("Remove this amount");
                setButton.setText("Set to this amount (Selected)");
            }
        );
        */
        saveButton.setOnAction(e ->
            {
                try
                {
                    if (addMode || removeMode || setMode)
                    { 
                        referer.updateBalance(Double.parseDouble(amountField.getText().trim()), addMode, removeMode, setMode);
                        dialog.close();
                    }
                    else
                    {
                        saveButton.setText("Select a mode");
                    }
                }
                catch(Exception err)
                {
                    saveButton.setText("Save");
                    Alert alert = new Alert(AlertType.ERROR, "Please double check that you entered the amount correctly.", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        );
        
        cancelButton.setOnAction(e->
            dialog.close()
        );
    }   
}
