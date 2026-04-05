
/**
 * Write a description of class DashBoard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class LoginForm extends Application
{
@Override
public void start(Stage stage) {
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setAlignment(Pos.CENTER);
    
    Label nameLabel = new Label ("Username:");
    TextField nameField = new TextField();
    
    Label passLabel = new Label("Password:");
    PasswordField passField = new PasswordField();
    
    CheckBox remember = new CheckBox("Remember Me");
    
    Button loginButton = new Button("Login");
    
    grid.add(nameLabel, 0, 0);
    grid.add(nameField, 1, 0);
    
    grid.add(passLabel, 0, 1);
    grid.add(passField, 1, 1);
    
    grid.add(remember, 1, 2);
    grid.add(loginButton, 1, 3);
    
    loginButton.setOnAction(e -> {
        System.out.println("Username: " + nameField.getText());
        System.out.println("Password: " + passField.getText());
        System.out.println("Remember Me : " + remember.isSelected());
    });
    
    Scene scene = new Scene(grid, 350, 250);
    
    stage.setTitle("Login Form");
    stage.setScene(scene);
    stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}

