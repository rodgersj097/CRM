package sample;

import java.util.ArrayList;

public class EntityCreator
{
    private  Integer id;
    private  String name;
    private ArrayList<Fields> fields;

    public EntityCreator( String name) {
        setName(name);
    }

    public EntityCreator(Integer id, String name){
        setId(id);
        setName(name);

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
        if(!name.isEmpty())
        {
            this.name = name;
        }
    }

}
