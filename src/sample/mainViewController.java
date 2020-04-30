package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import sun.reflect.generics.tree.Tree;

public class mainViewController implements Initializable {

    @FXML
    private Button addDocumentButton;

    @FXML
    private Label documentName;

    @FXML
    private Button createNewDocButton;

    @FXML
    private TreeView docTreeView;

    @FXML
    private VBox fieldListVBOX;

    private static TreeMap<String , LinkedList<existingDocument> > TemplateAndExistingTreeMap = new TreeMap<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<tempDocId> listOfTempDocIds = new ArrayList();
        try {
            dbConnect.getDocumentTemplate();
            dbConnect.getExistingDocs();
            listOfTempDocIds.addAll(dbConnect.getTempDocMatcher());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        listOfTempDocIds.forEach(tempDoc->{
            sortThroughDocs(tempDoc);
        });

        Set<String> keys = documentTemplateController.getKeys();
        ArrayList<TreeItem> docs = new ArrayList<>();
        TreeItem rootItem = new TreeItem("Documents");
        keys.forEach(key->{
            ArrayList<TreeItem> childTreeItems = setUpTreeViewChilds(key);
            TreeItem item = new TreeItem(key);
            item.getChildren().addAll(childTreeItems);
            docs.add(item);
        });
        rootItem.getChildren().addAll(docs);
        docTreeView.setRoot(rootItem);
        rootItem.setExpanded(true);

        /*documentTemplateController.printTree();
        existingDocumentController.printTree();
        printTree();
        */

        addDocumentButton.setOnAction(e->{
            try {
                sceneChanger.changeScenes(e, "create.fxml", "Create a new Document");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        createNewDocButton.setOnAction(e->{
            fieldListVBOX.getChildren().clear();
            TreeItem selectedItem = (TreeItem) docTreeView.getSelectionModel().getSelectedItem();
            System.out.println(selectedItem.getValue().toString());
           List<Fields> listOfTemplateFields =  documentTemplateController.getTemplatesFromKeys(selectedItem.getValue().toString());
           documentName.setText(selectedItem.getValue() + " Template");
           for(Fields f : listOfTemplateFields){
                System.out.println(f.getFieldType() + "d");
                if(f.getFieldType().equals("InputField")) {
                    Label newLabel = new Label();
                    newLabel.setText(f.getName());
                    TextField newField = f.createFXTextField(f);
                    fieldListVBOX.getChildren().add(newLabel);
                    fieldListVBOX.getChildren().add(newField);
                } else if(f.getFieldType().equals("InputArea")) {
                    Label newLabel = new Label();
                    newLabel.setText(f.getName());
                    TextArea newField = f.createFXTextArea(f);
                    fieldListVBOX.getChildren().add(newLabel);
                    fieldListVBOX.getChildren().add(newField);
                }
           }
        });

    }

    private static void sortThroughDocs(tempDocId tempDocId){
        List<Fields> listOfFields = new ArrayList<>();
        listOfFields.addAll(existingDocumentController.getFieldsFromKeys(tempDocId.getTempDocName()));
        existingDocument newDoc = new existingDocument(tempDocId.getTempdocId(), tempDocId.getExistingdocName(), listOfFields);

        addDocToTree(tempDocId.tempDocName, newDoc);

    }

    public static void addDocToTree( String docName , existingDocument doc){
        System.out.println("Adding docs to treemap ");
        if(!TemplateAndExistingTreeMap.keySet().contains(docName)){
            TemplateAndExistingTreeMap.put(docName, new LinkedList<>());
            TemplateAndExistingTreeMap.get(docName).add(doc);
        }else {
            TemplateAndExistingTreeMap.get(docName).add(doc);
        }
    }

    public static List<existingDocument> getDocsFromKeys(String key){
        List<existingDocument> docs =  new ArrayList();
        for(Map.Entry m : TemplateAndExistingTreeMap.entrySet()){
            if(m.getKey().equals(key)){
                docs.addAll((Collection) m.getValue());
            }
        }
        return docs;
    }


    public static Set<String>  getKeys(){
        System.out.println("Returning keyset");
        return TemplateAndExistingTreeMap.keySet();
    }

    public static void printTree() {
        for (Map.Entry< String, LinkedList< existingDocument > > entry : TemplateAndExistingTreeMap.entrySet()) {
            System.out.println("T and D Key: " + entry.getKey().toString() + ". Value: " + entry.getValue().toString());
        }
    }


    public static ArrayList<TreeItem> setUpTreeViewChilds(String key){
        ArrayList<TreeItem> docs = new ArrayList<TreeItem>();
        List<existingDocument> listOfDOcs = getDocsFromKeys(key);
        listOfDOcs.forEach(doc->{
            TreeItem newItem = new TreeItem(doc.getName());
            docs.add(newItem);
        });
        return docs;
    }
}
