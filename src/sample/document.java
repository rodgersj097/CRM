package sample;

import java.util.List;

public class document {
    int id;
    int tempDocId;
    String name;
    Fields[] field;

    public document(int id, int tempDocId, String name, List< Fields > field) {
        setId(id);
        setTempDocId(tempDocId);
        setName(name);
        setField(field);
    }

    public document(String name, Fields[] field) {
        this.name = name;
        this.field = field;
    }

    public document(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTempDocId() {
        return tempDocId;
    }

    public void setTempDocId(int tempDocId) {
        this.tempDocId = tempDocId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fields[] getField() {
        return field;
    }

    public void setField(List< Fields > field) {
        this.field = field.toArray(new Fields[0]);
    }

    @Override
    public String toString(){
        return "Document:"+ this.getName() + "";
    }
}
