package sample;

import java.util.List;

public class documentTemplate {

    String docName;
    Fields[] field;

    public documentTemplate(String docName, List<Fields> field) {
        setDocName(docName);
        setField(field);
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        if(!docName.isEmpty()) {
            this.docName = docName;
        }else {
            throw new IllegalArgumentException("Document Name cannot be empty");

        }
    }

    public Fields[] getField() {
        return field;
    }

    public void setField(List< Fields > field) {
        if (field.size() > 0) {
            this.field = field.toArray(new Fields[0]);
        } else {
            throw new IllegalArgumentException("There must be a field in the array ");
        }
    }
}
