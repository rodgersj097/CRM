package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class createController implements Initializable {
    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<String> fieldTypeComboBox;

    @FXML
    private Button addFieldButton;

    @FXML
    private TableColumn<Fields, String> fieldType;

    @FXML
    private TableColumn<Fields, String> name;

    @FXML
    private Button createDocumentButton;

    @FXML
    private TableView<Fields> fieldTypeTableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Setup Combo box
        List<String> fieldTypeOptions = new ArrayList<String>();
        fieldTypeOptions.add("InputField");
        fieldTypeOptions.add("InputArea");
        //TODO add more options
        ObservableList ftList = FXCollections.observableList(fieldTypeOptions);
        fieldTypeComboBox.setItems(ftList);

        //setup tablecolumns
        fieldType.setCellValueFactory(new PropertyValueFactory<Fields, String>("FieldType"));
        name.setCellValueFactory(new PropertyValueFactory<Fields, String>("name"));

    }

    public void addFieldButtonPushed(ActionEvent event){
        String fieldTypeChosen = fieldTypeComboBox.getSelectionModel().getSelectedItem();
        String nameOfChosenFieldType = nameTextField.getText();
        Fields field = new Fields(nameOfChosenFieldType, fieldTypeChosen);
        fieldTypeTableView.getItems().add(field);

    }


    public void cancelButtonPushed(ActionEvent event) throws IOException {
        sceneChanger.changeScenes(event, "sample.fxml", "");
    }
}
