import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class SecondWindow {

    public void show() {
        Stage stage = new Stage();

        Label label = new Label("Hello from second window!");

        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 250, 150);

        stage.setTitle("Second Window");
        stage.setScene(scene);
        stage.show();
    }
}