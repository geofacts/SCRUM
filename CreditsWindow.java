import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CreditsWindow {

    public void show() {
        Stage stage = new Stage();

        Label label = new Label("From the genius minds of:");
        Label kaden = new Label("Kaden Butler (the scrum master)");
        Label nolan = new Label("Nolan Thurman (the product owner)");
        Label ethan = new Label("Ethan Su (the developer)");

        VBox root = new VBox(5); // spacing of 5 pixels between labels
        root.getChildren().addAll(label, kaden, nolan, ethan);

        Scene scene = new Scene(root, 250, 150);

        stage.setTitle("Credits");
        stage.setScene(scene);
        stage.show();
    }
}