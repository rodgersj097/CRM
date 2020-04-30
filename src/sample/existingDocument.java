package sample;

import java.util.List;

public class existingDocument {
    int tempDocId;
    String name;
    Fields[] field;

    public existingDocument(int tempDocId, String name, List< Fields > field) {
        setTempDocId(tempDocId);
        setName(name);
        setField(field);
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
}
