import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CreditsWindow
{
    public void show()
    {
        Stage stage = new Stage();

        Label label = new Label("From The Genius Minds Of:");
        Label kaden = new Label("The SCRUM Master - Kaden Butler");
        Label nolan = new Label("The Product Owner - Nolan Thurman");
        Label ethan = new Label("The Developer - Ethan Su");

        VBox root = new VBox(5); // spacing of 5 pixels between labels
        root.getChildren().addAll(label, kaden, nolan, ethan);

        Scene scene = new Scene(root, 250, 150);

        stage.setTitle("Credits");
        stage.setScene(scene);
        stage.show();
    }
}
