package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

public class messageDisplayer {

    public static void displayErrorMessage(String error, String errorContext) {
        if (!error.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            alert.setTitle("Error");
            alert.setHeaderText(error);
            alert.setContentText(errorContext);
            alert.showAndWait();

        }

    }


}

