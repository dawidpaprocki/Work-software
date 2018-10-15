//package combo;
//
//import connection.DBConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//
//public class EditTable {
//
//
//    public EditTable(String newEditableText, String idWhereFindText,String whatColumn){
//
//        String query = "UPDATE all_view SET " + whatColumn + "  =  '" + newEditableText + "' Where ID = '" + idWhereFindText + "'";
//
//
//        try {
//            //get connection
//            Connection  conn = DBConnection.getConnection();
//
//            //create preparedStatement
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//
//            //execute query
//            preparedStatement.execute(query);
//
//            //close connection
//            preparedStatement.close();
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
