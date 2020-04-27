package sample;

public class Fields
{
    private Integer id;
    private String name, fieldType;

    public Fields( String name, String fieldType) {
        setName(name);
        setFieldType(fieldType);
    }
    public Fields( Integer id, String name, String fieldType) {
        setId(id);
        setName(name);
        setFieldType(fieldType);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
