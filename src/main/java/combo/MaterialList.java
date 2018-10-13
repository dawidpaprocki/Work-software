//package combo;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.control.ChoiceBox;
//import connection.DBconnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//public class MaterialList {
//
//    ObservableList MaterialLIst = FXCollections.observableArrayList();
//
//    public  MaterialList(ChoiceBox choiceBox)
//
//    {
//
//
//
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;
//        String query = "SELECT NAME FROM Material";
////        System.out.println(query);
//        try{
//            //get connection
//            conn = DBconnection.getConnection();
//
//            //create preparedStatement
//            preparedStatement = conn.prepareStatement(query);
//
//            //execute query
//            ResultSet rs = preparedStatement.executeQuery(query);
//            while (rs.next()) {
//
//                MaterialLIst.add(rs.getString("Name"));
//
//            }
//            //close connection
//            preparedStatement.close();
//            conn.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        choiceBox.setItems(MaterialLIst);
//
//    }
//
//    public ObservableList getMaterialLIst() {
//        return MaterialLIst;
//    }
//}
