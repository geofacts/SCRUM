import javafx.application.Application;

import javafx.stage.Stage;

import javafx.scene.Scene;

import javafx.scene.layout.GridPane;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class Dashboard extends Application
{
    @Override
    public void start(Stage stage)
    { 
        Label titleLabel = new Label("Blue Finance");
        Label locationLabel = new Label("Dashboard");
        Label balanceLabel = new Label("Balance: $Infinity");
        
        titleLabel.setStyle("-fx-font-size: 22px; -fx-text-fill: blue;");
        locationLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
        balanceLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
        
        Button paycheckButton = new Button("Paychecks");
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
        
        grid.add(paycheckButton, 1, 1, 2, 1);
        grid.add(updateBalanceButton, 16, 1, 1, 1);
        grid.add(creditButton, 16, 8, 1, 1);
        
        Scene scene = new Scene(grid, 800, 450);
        
        stage.setTitle("Blue Finance - Dashboard");
        stage.setScene(scene);
        stage.show();
        
        paycheckButton.setOnAction(e -> {
            SecondWindow window = new SecondWindow();
            window.show();
    });
}
}
