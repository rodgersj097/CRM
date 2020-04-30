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
            documentTemplateController.createDocTemplates(docName, field);

        }
        System.out.println("Documents Fetched");

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
        resultSet = statement.executeQuery("select existingdocuments.docName, existingdocuments.tempDocId, existingFields.fieldValue, fields.fieldName, fields.fieldType, fields.id from existingdocuments inner join existingFields on existingdocuments.id = existingFields.id inner join fields on existingFields.id = fields.id;");


        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String docName = resultSet.getString("docName");
            Integer docTempId = resultSet.getInt("tempDocId");
            String fieldValue = resultSet.getString("fieldValue");
            String fieldName = resultSet.getString("fieldName");
            String fieldType = resultSet.getString("fieldType");
            Fields field = new Fields(id,fieldName,  fieldType, fieldValue);

            existingDocumentController.addDocToTree(docName, field);

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
        resultSet = statement.executeQuery("Select existingdocuments.docName as 'existingDocName', docTemplates.docName, existingdocuments.tempDocId from existingdocuments inner join doctemplates on existingdocuments.tempDocId = doctemplates.id;");


        while (resultSet.next()) {
            Integer id = resultSet.getInt("tempDocId");
            String tempDocName = resultSet.getString("docName");
            String existingDocName = resultSet.getString("existingDocName");
            tempDocId tempDocId = new tempDocId(id, existingDocName,  tempDocName);
            listOfTempDocId.add(tempDocId);
        }
        return listOfTempDocId;
    }
}