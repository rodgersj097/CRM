package sample;

import java.util.ArrayList;

public class EntityCreator
{
    private  Integer id;
    private  String name;
    private ArrayList<Fields> fields;

    public EntityCreator(Integer id, String name, ArrayList<Fields> fields) {
        setId(id);
        setName(name);
        setFields(fields);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if(id > 0)
        {
            this.id = id;
        }
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        if(!name.isEmpty())
        {
            this.name = name;
        }
    }

    public ArrayList<Fields> getFields() {
        return fields;
    }
    //TODO set validation
    public void setFields(ArrayList<Fields> fields) {
        this.fields = fields;
    }
}
