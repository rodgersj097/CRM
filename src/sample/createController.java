package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
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
    private Button cancelButton;

    @FXML
    private Button deleteFieldButton;

    @FXML
    private TableView<Fields> fieldTypeTableView;

    @FXML
    private TextField docNameTextField;

    private ArrayList<Integer> listofFieldIds = new ArrayList<>();

    private ArrayList<Fields> listOfFields = new ArrayList<Fields>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Setup Combo box
        List<String> fieldTypeOptions = new ArrayList<String>();
        fieldTypeOptions.add("InputField");
        fieldTypeOptions.add("InputArea");
        //TODO add more options
        ObservableList ftList = FXCollections.observableList(fieldTypeOptions);
        fieldTypeComboBox.setItems(ftList);

        //setup table columns
        fieldType.setCellValueFactory(new PropertyValueFactory<Fields, String>("FieldType"));
        name.setCellValueFactory(new PropertyValueFactory<Fields, String>("name"));

        //addFieldButtonPushed
        addFieldButton.setOnAction(e->{
            String fieldTypeChosen = fieldTypeComboBox.getSelectionModel().getSelectedItem();
            String nameOfChosenFieldType = nameTextField.getText();
            Fields field = new Fields(nameOfChosenFieldType, fieldTypeChosen);
            fieldTypeTableView.getItems().add(field);
            listOfFields.add(field);
        });

        //deleteButtonPushed
        deleteFieldButton.setOnAction(e->{
            Fields fieldToDelete = fieldTypeTableView.getSelectionModel().getSelectedItem();
            fieldTypeTableView.getItems().remove(fieldToDelete);
            listOfFields.removeIf(f -> f == fieldToDelete);
        });

        //cancelButtonPushed
        cancelButton.setOnAction(e->{
            try {
                sceneChanger.changeScenes(e, "sample.fxml", "");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //createDocumentPushed
        createDocumentButton.setOnAction(e->{
           listOfFields.forEach(field->{
               try {
                   listofFieldIds.add(dbConnect.insertFields(field));
               } catch (SQLException ex) {
                   ex.printStackTrace();
               }
           });
            //loop through ids and create templates
            listofFieldIds.forEach(id->{
                dbConnect.insertDocTemp(id,docNameTextField.getText());
            });
        });
    }
}