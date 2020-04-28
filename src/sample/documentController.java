package sample;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.*;

public class documentController  {
    private static TreeMap<String , LinkedList<Fields>> documentTreeMap = new TreeMap<>();



    public static void createDocTemplates(String docName , Fields field){
        System.out.println("Adding docs to treemap ");
        if(!documentTreeMap.keySet().contains(docName)){
            documentTreeMap.put(docName, new LinkedList<>());
            documentTreeMap.get(docName).add(field);
        }else {
            documentTreeMap.get(docName).add(field);
        }
    }

    public static Set<String>  getKeys(){
        System.out.println("Returning keyset");
        return documentTreeMap.keySet();
    }

    public static void printTree() {
        for (Map.Entry< String, LinkedList< Fields > > entry : documentTreeMap.entrySet()) {
            System.out.println("Key: " + entry.getKey().toString() + ". Value: " + entry.getValue().toString());
        }
    }


}
