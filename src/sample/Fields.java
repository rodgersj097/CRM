package sample;

public class Fields
{
    private Integer id;
    private String name, fieldType;

    public Fields( String name, String fieldType) {

        setName(name);
        setFieldType(fieldType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}
