package sample;

import java.util.*;

public class documentController {
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


    private static TreeMap<String , LinkedList<Fields>> templateTreeMap = new TreeMap<>();

    public static void createTemplates(String docName , Fields field){
        System.out.println("Adding docs to treemap ");
        if(!templateTreeMap.keySet().contains(docName)){
            templateTreeMap.put(docName, new LinkedList<>());
            templateTreeMap.get(docName).add(field);
        }else {
            templateTreeMap.get(docName).add(field);
        }
    }

    public static List<Fields> getTemplatesFromKeys(String key){
        List<Fields> templates =  new ArrayList();
        for(Map.Entry m : templateTreeMap.entrySet()){
            if(m.getKey().equals(key)){
                templates.addAll((Collection) m.getValue());
            }
        }
        return templates;
    }
    public static Set<String>  getTemplateKeys(){
        return templateTreeMap.keySet();
    }

    public static void printTemplateTree() {
        for (Map.Entry< String, LinkedList< Fields > > entry : templateTreeMap.entrySet()) {
            System.out.println("Key: " + entry.getKey().toString() + ". Value: " + entry.getValue().toString());
        }
    }

    public static void deleteTemplateFromTree(String key){
        templateTreeMap.remove(key);
    }


}
