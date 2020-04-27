package sample;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
public class mainViewController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }
    public void createDocumentButtonPushed(ActionEvent event) throws IOException{
        sceneChanger.changeScenes(event, "create.fxml", "Create a new Document");
    }
}
