package sample;


import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Fields
{
    private Integer id, fId;
    private String name, fieldType, value;

    public Fields( String name, String fieldType) {
        setName(name);
        setFieldType(fieldType);
    }
    public Fields( Integer id, String name, String fieldType) {
        setId(id);
        setName(name);
        setFieldType(fieldType);
    }

    public Fields( Integer fId, String name, String fieldType, String value) {
        setfId(fId);
        setName(name);
        setFieldType(fieldType);
        setValue(value);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if(id > 0) {
            this.id = id;
        }else {
            throw new IllegalArgumentException("id cannot be less than 0");
        }
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        if(fId > 0) {
            this.fId = fId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.isEmpty()) {
            this.name = name;
        }else {
            throw new IllegalArgumentException("Field Name cannot be emtpy");
        }
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        if(!fieldType.isEmpty()) {
            this.fieldType = fieldType;
        }else {
            throw new IllegalArgumentException("fieldType cannot be empty");

        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if(!value.isEmpty()) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Value cannot be empty");
        }
    }

    public TextField createFXTextField(Fields field) {
        TextField textField = new TextField();
        if (field.getFieldType() == "TextField") {
            textField.setFocusTraversable(false);
            textField.setId(field.getId().toString());
            textField.setPromptText(field.getName());
            textField.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); }");
        }
        return textField;
    }

    public TextArea createFXTextArea(Fields field){
        TextArea textArea = new TextArea();
        if (field.getFieldType() == "TextField") {
            textArea.setFocusTraversable(false);
            textArea.setPromptText(field.getName());

        }
        return textArea;
    }


}
