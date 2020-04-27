package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class mainViewController implements Initializable {

    @FXML
    private Button addDocumentButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    addDocumentButton.setOnAction(e->{
        try {
            sceneChanger.changeScenes(e, "create.fxml", "Create a new Document");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });

    }

}
