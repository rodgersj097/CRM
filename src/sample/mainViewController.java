package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class mainViewController implements Initializable {

    @FXML
    private Button addDocumentButton;

    @FXML
    private Label documentName;

    @FXML
    private Button createNewDocButton;

    @FXML
    private TreeView<document> docTreeView;

    @FXML
    private VBox fieldListVBOX;

    @FXML
    private Button deleteButton;


    private static TreeMap< document, LinkedList< document > > TemplateAndExistingTreeMap = new TreeMap< document, LinkedList< document >>(new existingDocComparator());
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<tempDocId> listOfTempDocIds = new ArrayList();
        //setup treemaps
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

        documentController.printTemplateTree();
        documentController.printTree();
        printTree();

        updateTreeView();


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
           List<Fields> listOfTemplateFields =  documentController.getTemplatesFromKeys(selectedItem.getValue().toString());
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

        deleteButton.setOnAction(e->{
           TreeItem itemToDelete =  docTreeView.getSelectionModel().getSelectedItem();
            try {
                System.out.println(itemToDelete);
                deleteItem(itemToDelete.getValue().toString());
                updateTreeView();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void updateTreeView() {
        //link templates to documents for treeview
        Set<String> keys = documentController.getTemplateKeys();
        ArrayList<TreeItem> docs = new ArrayList<>();
        TreeItem<document> rootItem = new TreeItem("Documents");
        document keyDoc = null;
        for(String key : keys){
            try {
                keyDoc = dbConnect.getSingleDocumentTemplate(key);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ArrayList<TreeItem> childTreeItems = setUpTreeViewChilds(keyDoc);
            TreeItem item = new TreeItem(keyDoc.getName());
            item.getChildren().addAll(childTreeItems);
            docs.add(item);
        };
        rootItem.getChildren().addAll((Collection) docs);
        docTreeView.setRoot(rootItem);
        rootItem.setExpanded(true);
    }

    private static void sortThroughDocs(tempDocId tempDocId){
        List<Fields> listOfFields = new ArrayList<>();
        listOfFields.addAll(documentController.getFieldsFromKeys(tempDocId.getTempDocName()));
        document newDoc = new document(tempDocId.getExistingDocId(), tempDocId.getTempdocId(), tempDocId.getExistingdocName(), listOfFields);
        document newTemp = new document(tempDocId.tempdocId, tempDocId.tempDocName);
        addDocToTree(newTemp, newDoc);
    }

    public static void addDocToTree(document docTemp , document doc){
        System.out.println("Adding docs to treemap ");
        if(!TemplateAndExistingTreeMap.keySet().contains(docTemp)){
            TemplateAndExistingTreeMap.put(docTemp, new LinkedList<>());
            TemplateAndExistingTreeMap.get(docTemp).add(doc);
        }else {
            TemplateAndExistingTreeMap.get(docTemp).add(doc);
        }
    }

    public static List< document > getDocsFromKeys(document key){
        List< document > docs =  new ArrayList();
        for(Map.Entry m : TemplateAndExistingTreeMap.entrySet()){
            if(m.getKey().toString().equals(key.toString())) {
                docs.addAll((Collection) m.getValue());
            }
        }
        return docs;
    }



    public static Set< document >  getKeys(){
        System.out.println("Returning keyset");
        return  TemplateAndExistingTreeMap.keySet();
    }

    public static void printTree() {
        for (Map.Entry< document, LinkedList< document > > entry : TemplateAndExistingTreeMap.entrySet()) {
            System.out.println("T and D Key: " + entry.getKey().toString() + ". Value: " + entry.getValue().toString());
        }
    }


    public static ArrayList<TreeItem> setUpTreeViewChilds(document key){
        ArrayList<TreeItem> docs = new ArrayList<TreeItem>();
        List< document > listOfDOcs = getDocsFromKeys(key);
        listOfDOcs.forEach(doc->{
            TreeItem<document> newItem = new TreeItem(doc.getName());
            docs.add(newItem);
        });
        return docs;
    }

    public static void deleteItem(String nameOfItemToDelete) throws SQLException {
        Set< document > tempKeys = getKeys();
        String correctKey = "" ;
        for (document key : tempKeys) {
            if(nameOfItemToDelete.equals(key.getName())){
                if(getDocsFromKeys(key).size() > 0){
                    messageDisplayer.displayErrorMessage("Template contains existing documents", "Please delete existing documents before deleting the template.");
                }else {
                    try {
                        System.out.println(key.getId());
                        dbConnect.deleteTemplate(key.id);
                    }catch (SQLException e){
                        messageDisplayer.displayErrorMessage("Error with deleting template", e.getMessage());
                    }
                }
            }
            else {
                try {
                    dbConnect.deleteTemplateByName(nameOfItemToDelete);
                    documentController.deleteTemplateFromTree(nameOfItemToDelete);
                } catch(SQLException e){
                    throw new SQLException(e.getMessage());
                }

            }
        }

    }



}
class existingDocComparator implements Comparator< document >{
    @Override
    public int compare(document o1, document o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
