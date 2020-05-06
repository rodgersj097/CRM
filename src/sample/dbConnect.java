package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class dbConnect {
    private static String user = "root";
    private static String pass = "";

    public static Integer insertFields(Fields newField) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int lastInsertedId = 0;
        try {
            //1. Connect to database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user, pass);

            //2.Create a sql statement
            String sql = "INSERT INTO fields(fieldName, fieldType) values (?,?)";

            //3. create PreparedStatment
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //4. Bind parameters
            ps.setString(1, newField.getName());
            ps.setString(2, newField.getFieldType());

            //5. execute instert statemnt
            ps.executeUpdate();

            //get auto generated id from insesrt
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                lastInsertedId = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lastInsertedId;
    }


    public static Integer insertFieldsWithValue(Fields newField) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int lastInsertedId = 0;
        try {

            //1. Connect to database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user, pass);

            //2.Create a sql statement
            String sql = "INSERT INTO existingFields(tempFieldId,  fieldValue) values (?,?)";

            //3. create PreparedStatment
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //4. Bind parameters
            ps.setInt(1, newField.getfId());
            ps.setString(2, newField.getValue());

            //5. execute instert statemnt
            ps.executeUpdate();


            //get auto generated id from insesrt
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                lastInsertedId = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lastInsertedId;
    }
    public static void insertDocTemp(int fieldId, String docName) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            //1. Connect to database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user, "");

            //2.Create a sql statement
            String sql = "INSERT INTO docTemplates(docName, fieldId) values (?,?)";

            //3. create PreparedStatment
            ps = conn.prepareStatement(sql);

            //4. Bind parameters
            ps.setString(1, docName);
            ps.setInt(2, fieldId);


            //5. execute instert statemnt
            ps.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertExistingDoc(int fieldId, String docName) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            //1. Connect to database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user, "");

            //2.Create a sql statement
            String sql = "INSERT INTO existingDocuments(docName, fieldId) values (?,?)";

            //3. create PreparedStatment
            ps = conn.prepareStatement(sql);

            //4. Bind parameters
            ps.setString(1, docName);
            ps.setInt(2, fieldId);


            //5. execute instert statemnt
            ps.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static  void getDocumentTemplate() throws SQLException {
        System.out.println("Starting to get Documents");
        TreeMap<String, LinkedList<Fields> > docMap = new TreeMap<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //1. Connect to database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user, "");
        //create sql statement

        statement = conn.createStatement();
        resultSet = statement.executeQuery("SELECT doctemplates.docName, fields.id, fields.fieldName, fields.fieldType FROM doctemplates inner join fields on doctemplates.fieldId = fields.id ;");


        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String docName = resultSet.getString("docName");
            String fieldName = resultSet.getString("fieldName");
            String fieldType = resultSet.getString("fieldType");
            Fields field = new Fields(id,fieldName, fieldType);
            System.out.println(docName);
            documentController.createTemplates(docName, field);

        }
        System.out.println("Documents Fetched");

    }

    public static document getSingleDocumentTemplate(String docNameToGet) throws SQLException {
        System.out.println("Starting to get single Documents");
        TreeMap<String, LinkedList<Fields> > docMap = new TreeMap<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        document newDoc = null;
        //1. Connect to database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user, "");
        //create sql statement

        statement = conn.createStatement();
        System.out.println(docNameToGet);
        resultSet = statement.executeQuery("SELECT doctemplates.id, doctemplates.docName FROM doctemplates where doctemplates.docName = '"+ docNameToGet +"';");


        if(resultSet.next()) {

            Integer id = resultSet.getInt("id");
            String docName = resultSet.getString("docName");
            System.out.println("DBConect" +id);
             newDoc = new document(id, docName);

            System.out.println("Documents Fetched");
        }
        return newDoc;
    }

    public static  void getExistingDocs() throws SQLException {
        System.out.println("Starting to get Documents");
        TreeMap<String, LinkedList<Fields> > docMap = new TreeMap<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //1. Connect to database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user, "");
        //create sql statement

        statement = conn.createStatement();
        resultSet = statement.executeQuery("select existingdocuments.docName, existingdocuments.tempDocId, existingFields.fieldValue, fields.fieldName, fields.fieldType, fields.id from existingdocuments inner join existingFields on existingdocuments.fieldId = existingFields.id inner join fields on existingfields.tempFieldId = fields.id;");


        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String docName = resultSet.getString("docName");
            Integer docTempId = resultSet.getInt("tempDocId");
            String fieldValue = resultSet.getString("fieldValue");
            String fieldName = resultSet.getString("fieldName");
            String fieldType = resultSet.getString("fieldType");
            Fields field = new Fields(id,fieldName,  fieldType, fieldValue);

            documentController.addDocToTree(docName, field);

        }
        System.out.println("Documents Fetched");
    }


    public static List getTempDocMatcher() throws SQLException {
        List listOfTempDocId = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //1. Connect to database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user, "");
        //create sql statement

        statement = conn.createStatement();
        resultSet = statement.executeQuery("Select existingdocuments.docName as 'existingDocName', existingdocuments.id as 'existingDocId', docTemplates.docName, existingdocuments.tempDocId from existingdocuments inner join doctemplates on existingdocuments.tempDocId = doctemplates.id;");


        while (resultSet.next()) {
            Integer id = resultSet.getInt("tempDocId");
            Integer existingId = resultSet.getInt("existingDocId");
            String tempDocName = resultSet.getString("docName");
            String existingDocName = resultSet.getString("existingDocName");
            tempDocId tempDocId = new tempDocId(id, existingId, existingDocName,  tempDocName);
            listOfTempDocId.add(tempDocId);
        }
        return listOfTempDocId;
    }

    public static void deleteTemplate(int id) throws SQLException {

        List listOfTempDocId = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;


        //1. Connect to database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user, "");
        //create sql statement

        statement = conn.createStatement();
        statement.executeUpdate("Delete from doctemplates where id ="+ id + ";");



    }
    public static void deleteTemplateByName(String name) throws SQLException {


        String sql = "Delete from doctemplates where docName =?";
        try(
        //1. Connect to database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user, "");
        //create sql statement
        PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Template deleted");
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }


    }

    public static void deleteDocument(int id) throws SQLException {

        List listOfTempDocId = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;


        //1. Connect to database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user, "");
        //create sql statement

        statement = conn.createStatement();
        statement.executeQuery("Delete from existingdocuments where id ="+ id + ";");



    }
}