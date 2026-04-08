import javafx.application.Application;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.HashMap;
import javafx.scene.control.Alert;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.TableView;

import javafx.stage.Stage;

import javafx.scene.Scene;

import javafx.scene.layout.GridPane;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.time.LocalDate;


public class Dashboard extends Application
{ 
    // For paycheck tracker table
    private final HashMap<String, PaycheckEntry> entryMap = new HashMap<>();
    private final ObservableList<PaycheckEntry> entryList = FXCollections.observableArrayList();
    
    private final TableView<PaycheckEntry> table = new TableView<>();

    private int balance;
    
    @Override
    public void start(Stage stage)
    {   
        balance = 0;
        
        Label titleLabel = new Label("Blue Finance");
        Label locationLabel = new Label("Dashboard");
        Label balanceLabel = new Label("Balance: $" + balance);
        
        titleLabel.setStyle("-fx-font-size: 22px; -fx-text-fill: blue;");
        locationLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
        balanceLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
        
        Button updateBalanceButton = new Button("Update Balance");
        Button creditButton = new Button("Credits");
        
        GridPane grid = new GridPane();
        
        grid.setHgap(40);
        grid.setVgap(40);
        //grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);
        
        grid.add(titleLabel, 0, 0, 5, 1);
        grid.add(locationLabel, 8, 0, 4, 1);
        grid.add(balanceLabel, 15, 0, 2, 1);

        grid.add(loadPaycheckTracker(), 1, 1, 9, 6);
        grid.add(updateBalanceButton, 16, 1, 1, 1);
        grid.add(creditButton, 16, 8, 1, 1);
        
        Scene scene = new Scene(grid, 800, 450);
        
        stage.setTitle("Blue Finance - Dashboard");
        stage.setScene(scene);
        stage.show();

        updateBalanceButton.setOnAction(e -> 
            {
                BalanceUpdater updater = new BalanceUpdater();
                updater.openBalanceUpdater();
            }
        );
        
        creditButton.setOnAction(e -> 
            {
                CreditsWindow window = new CreditsWindow();
                window.show();
            }
        );
    }
    
    private BorderPane loadPaycheckTracker()
    {
        TableColumn<PaycheckEntry, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn<PaycheckEntry, String> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(
            data -> {
                PaycheckEntry entry = data.getValue();
                return new SimpleStringProperty(Double.toString(entry.getAmount()));
            }
        );
        
        TableColumn<PaycheckEntry, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(
            data -> {
                PaycheckEntry entry = data.getValue();
                return new SimpleStringProperty(entry.getDateObtained().toString());
            }
        );
        
        table.getColumns().addAll(idCol, amountCol, dateCol);
        table.setItems(entryList);
        
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        
        Button addButton = new Button("Add Entry");
        Button editButton = new Button("Edit Selected");
        Button removeButton = new Button("Remove Selected");
        
        addButton.setOnAction(
            e -> {
                PaycheckEntry newEntry = PaycheckTracker.openPaycheckTracker(null);
            
                if (newEntry != null)
                {
                    if (entryMap.containsKey(newEntry.getId()))
                    {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Duplicate ID");
                        alert.setHeaderText("An entry with this ID already exists.");
                        alert.setContentText("Would you like to replace the existing entry?");
                    
                        Optional<ButtonType> result = alert.showAndWait();
                    
                        if (result.isPresent() && result.get() == ButtonType.OK)
                        {
                            PaycheckEntry oldEntry = entryMap.get(newEntry.getId());
                        
                            entryList.remove(oldEntry);
                        
                            entryMap.put(newEntry.getId(), newEntry);
                        
                            entryList.add(newEntry);
                        }
                    }
                    else
                    {
                        entryMap.put(newEntry.getId(), newEntry);
                        entryList.add(newEntry);
                    }
                }
            }
        );
        
        editButton.setOnAction(
            e -> {
                PaycheckEntry selected = table.getSelectionModel().getSelectedItem();
                
                if (selected == null)
                {
                    showInfo("No Selection", "Please select an entry to edit.");
                    return;
                }
                
                String oldId = selected.getId();
                
                PaycheckEntry editedEntry = PaycheckTracker.openPaycheckTracker(selected);
                
                if (editedEntry != null)
                {
                    if (!oldId.equals(editedEntry.getId()) && entryMap.containsKey(editedEntry.getId()))
                    {
                        showInfo("Duplicate ID", "Another entry already has that ID.");
                        return;
                    }   
                
                    entryMap.remove(oldId);
                    
                    entryMap.put(editedEntry.getId(), editedEntry);
                
                    int index = entryList.indexOf(selected);
                    entryList.set(index, editedEntry);
                }
            }
        );
        
        removeButton.setOnAction(
            e -> {
                PaycheckEntry selected = table.getSelectionModel().getSelectedItem();
    
                if (selected == null) 
                {
                    showInfo("No Selection", "Please select an entry to remove.");
                    return;
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Remove Entry");
                alert.setHeaderText("Remove selected entry?");
                alert.setContentText("Entry Id " + selected.getId() + " will be removed.");

                Optional<ButtonType> result = alert.showAndWait();
    
                if (result.isPresent() && result.get() == ButtonType.OK) 
                {
                    entryMap.remove(selected.getId());
                    entryList.remove(selected);
                }
            }
        );
        
        HBox buttonBar = new HBox(10, addButton, editButton, removeButton);
        
        BorderPane root = new BorderPane();
        
        Label paycheckTrackerLabel = new Label("Paycheck Tracker");
        
        paycheckTrackerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
        
        root.setTop(paycheckTrackerLabel);
        root.setCenter(table);
        root.setBottom(buttonBar);
        
        return root;
    }
    
    private void showInfo(String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
