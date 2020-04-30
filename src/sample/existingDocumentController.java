package sample;

import java.util.*;

public class existingDocumentController {
    private static TreeMap<String , LinkedList<Fields> > existingDocumentTreeMap = new TreeMap<>();

    public static void addDocToTree( String docName , Fields field){
        System.out.println("Adding docs to treemap ");
        if(!existingDocumentTreeMap.keySet().contains(docName)){
            existingDocumentTreeMap.put(docName, new LinkedList<>());
            existingDocumentTreeMap.get(docName).add(field);
        }else {
            existingDocumentTreeMap.get(docName).add(field);
        }
    }

    public static List<Fields> getFieldsFromKeys(String key){
        List<Fields> templates =  new ArrayList();
        for(Map.Entry m : existingDocumentTreeMap.entrySet()){
            if(m.getKey().equals(key)){
                templates.addAll((Collection) m.getValue());
            }
        }
        return templates;
    }
    public static Set<String>  getKeys(){
        System.out.println("Returning keyset");
        return existingDocumentTreeMap.keySet();
    }

    public static void printTree() {
        for (Map.Entry< String, LinkedList< Fields > > entry : existingDocumentTreeMap.entrySet()) {
            System.out.println("Existing Key: " + entry.getKey().toString() + ". Value: " + entry.getValue().toString());
        }
    }
    
}
