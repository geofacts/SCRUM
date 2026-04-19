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

public class BalanceUpdater
{
    protected boolean addMode;
    protected boolean removeMode;
    protected boolean setMode;
    
    public void openBalanceUpdater(Dashboard referer)
    {
        addMode = false;
        removeMode = false;
        setMode = false;
        
        Stage dialog = new Stage();
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        
        TextField idField = new TextField();
        TextField amountField = new TextField();

        Button addButton = new Button("Add this amount (Unselected)");
        Button removeButton = new Button("Remove this amount (Unselected)");
        Button setButton = new Button("Set to this amount (Unselected)");
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
        
        grid.add(addButton, 0, 2);
        grid.add(removeButton, 0, 3);
        grid.add(setButton, 0, 4);
        
        grid.add(saveButton, 0, 5);
        grid.add(cancelButton, 1, 5);
        
        Scene scene = new Scene(grid, 400, 250);
        
        dialog.setTitle("Balance Updater");
        dialog.setScene(scene);
        dialog.show();
        
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
        
        saveButton.setOnAction(e ->
            {
                try
                {
                    if (addMode || removeMode || setMode)
                    { 
                        referer.updateBalance(Double.parseDouble(amountField.getText().trim()));
                        dialog.close();
                    }
                    else
                    {
                        saveButton.setText("Select a mode");
                    }
                }
                catch(Exception err)
                {
                    saveButton.setText("Please enter a valid number");
                }
            }
        );
        
        cancelButton.setOnAction(e->
            dialog.close()
        );
    }   
}
