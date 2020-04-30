package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldsTest {
    private Fields field;
    @BeforeEach
    void setUp() {
        field = new Fields("TestFIeld", "textArea");
    }

    @Test
    void getId() {
    }

    @Test
    void setId() {
    }

    @Test
    void getName() {
    }

    @Test
    void setNameInvalid() {
        try {
            field.setName("");
            fail("empty string in setName should throw an excpetion");
        }catch(IllegalArgumentException e) {
            System.out.println("Properly caught empty string");
        } catch(Exception e ) {
            fail("Wrong excpetion thrown for setName with empty string" + e.getMessage());
        }
    }

    @Test
    void setNameValid(){
        field.setName("newName");
        String expResult = "newName";
        assertEquals(expResult, field.getName());

    }
    @Test
    void getFieldType() {
    }

    @Test
    void setFieldTypeEmpty() {

        try{
            field.setFieldType("");
        }catch (IllegalArgumentException e ) {
            System.out.println("Succesfully caught empty fieldName");
        } catch(Exception e ) {
            System.out.println("Exception thrown " + e.getMessage());

        }
    }
    @Test
    void setFieldTypeValid(){
            field.setFieldType("textField");
            String expResult= "textField";
            assertEquals(expResult, field.getFieldType());

    }

}

