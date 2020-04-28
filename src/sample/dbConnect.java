package sample;

import com.mysql.cj.protocol.Resultset;
import com.sun.org.apache.regexp.internal.RESyntaxException;

import javax.swing.text.html.parser.Entity;
import java.sql.*;
import java.util.LinkedList;
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
        resultSet = statement.executeQuery("SELECT doctemplates.docName,  fields.fieldName, fields.fieldType FROM doctemplates inner join fields on doctemplates.fieldId = fields.id ;");


        while (resultSet.next()) {
            String docName = resultSet.getString("docName");
            String fieldName = resultSet.getString("fieldName");
            String fieldType = resultSet.getString("fieldType");
            Fields field = new Fields(fieldName, fieldType);
            System.out.println(docName);
            documentController.createDocTemplates(docName, field);

        }
        System.out.println("Documents Fetched");

    }

}