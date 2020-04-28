package sample;

import java.util.List;

public class documentTemplate {

    String docName;
    Fields[] field;

    public documentTemplate(String DocName, List<Fields> field) {
        setDocName(docName);
        setField(field);


    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Fields[] getField() {
        return field;
    }

    public void setField(List< Fields > field) {
        this.field = field.toArray(new Fields[0]);
    }
}
