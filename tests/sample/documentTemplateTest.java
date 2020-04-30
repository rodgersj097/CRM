package sample;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class documentTemplateTest {
    documentTemplate doc;
    ArrayList<Fields> AOF = new ArrayList<>();
    @BeforeEach
    void setUp() {

        Fields field = new Fields("d1", "textField");
        Fields field1 = new Fields("d2", "textField");
        Fields field2 = new Fields("d3", "textArea");
        AOF.add(field);
        AOF.add(field1);
        AOF.add(field2);
        doc = new documentTemplate("docTemp", AOF);
    }

    @Test
    void getDocName() {
    }

    @Test
    void setDocNameInvalid() {
        try{
            doc.setDocName("");
            fail("Failed to catch empty string");
        }catch(IllegalArgumentException e ){
            System.out.println("properly caught empty string");
        }catch(Exception e ) {
            fail("There was an unexpected error thrown: " + e.getMessage());
        }
    }
    @Test
    void setDocNameValid(){
        doc.setDocName("Name");
        String expResult = "Name";
        assertEquals(expResult, doc.getDocName());
    }

    @Test
    void getField() {

    }



}