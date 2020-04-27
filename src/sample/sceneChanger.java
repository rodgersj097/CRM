package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class sceneChanger {
    /**
     * this method accepts the title of the new scene, the .fxml file name for the new view and the acrionevent that triggered the change
     */
    public static void changeScenes(ActionEvent event, String viewName, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(new Object() {
        }.getClass().getResource(viewName));


        Parent root = loader.load();
        Scene stage = new Scene(root);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(stage);
        window.show();
    }
}
