package game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class EndGame implements Initializable {
    @FXML
    private AnchorPane rootPane;

    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void endGame() {
        Alert end = new Alert(Alert.AlertType.INFORMATION);
        end.setTitle("Game Over");
        end.setHeaderText(null);
        end.setContentText("Great Game!");
        end.showAndWait();
    }
}
