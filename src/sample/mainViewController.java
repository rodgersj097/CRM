package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

public class mainViewController< TitlePane > implements Initializable {

    @FXML
    private Button addDocumentButton;

    @FXML
    private Label documentName;


    @FXML
    private Accordion docAccordion;

    ArrayList<TitlePane> listOfTitlePanes = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            dbConnect.getDocumentTemplate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        documentController.printTree();
        //setup accordion
        System.out.println("Starting to add docs to accordion");
        Set<String> docNames = new HashSet<String>();
        docNames = documentController.getKeys();
        for(String name : docNames) {
            TitledPane tp = new TitledPane();

            tp.setText(name);
            System.out.println(tp);
            docAccordion.getPanes().add(tp);
        }

    addDocumentButton.setOnAction(e->{
        try {
            sceneChanger.changeScenes(e, "create.fxml", "Create a new Document");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });

    }

}
