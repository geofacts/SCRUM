import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CreditsWindow
{
    public void show()
    {
        Stage stage = new Stage();

        Label label = new Label("This demo was created by:");
        Label kaden = new Label("Kaden Butler - SCRUM Master");
        Label nolan = new Label("Nolan Thurman - Product Owner");
        Label ethan = new Label("Ethan Su - Developer");

        VBox root = new VBox(5); // spacing of 5 pixels between labels
        root.getChildren().addAll(label, kaden, nolan, ethan);

        Scene scene = new Scene(root, 250, 150);

        stage.setTitle("Credits");
        stage.setScene(scene);
        stage.show();
    }
}
