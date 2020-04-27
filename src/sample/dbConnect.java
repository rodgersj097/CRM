package sample;

import com.sun.org.apache.regexp.internal.RESyntaxException;

import javax.swing.text.html.parser.Entity;
import java.sql.*;

public class dbConnect {
    private static String user = "root";
    private static String pass = "";

    public static Integer insertFields(Fields newField) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null ;
        ResultSet rs = null;
        int lastInsertedId = 0;
        try {

            //1. Connect to database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user , pass);

            //2.Create a sql statement
            String sql = "INSERT INTO fields(name, fieldType) values (?,?)";

            //3. create PreparedStatment
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //4. Bind parameters
            ps.setString(1, newField.getName());
            ps.setString(2, newField.getFieldType());

            //5. execute instert statemnt
             ps.executeUpdate();


             //get auto generated id from insesrt
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                 lastInsertedId = rs.getInt(1);
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return lastInsertedId;
    }

    public static int insertEntity(EntityCreator newEntity) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int lastInsertedId = 0;
        try {

            //1. Connect to database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user , pass);

            //2.Create a sql statement
            String sql = "INSERT INTO entitys(name) values (?)";

            //3. create PreparedStatment
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //4. Bind parameters
            ps.setString(1, newEntity.getName());


            //5. execute instert statemnt
            ps.executeUpdate();

            //get auto generated id from insesrt
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                lastInsertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lastInsertedId;
    }


    public static void insertEntity_Fields(int fieldId, int entityId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            //1. Connect to database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", user ,""  );

            //2.Create a sql statement
            String sql = "INSERT INTO Entity_fields(fieldId, EntityId) values (?,?)";

            //3. create PreparedStatment
            ps = conn.prepareStatement(sql);

            //4. Bind parameters
            ps.setInt(1, fieldId );
            ps.setInt(1, entityId);


            //5. execute instert statemnt
            ps.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
