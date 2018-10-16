package combo;

import connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectListOfThings {

   private ObservableList comboList = FXCollections.observableArrayList();






    public SelectListOfThings(String whatToDo, String columnLabel, ObservableList list){

        try{
            //get connection
            Connection conn = DBConnection.getConnection();

            //create preparedStatement
            PreparedStatement preparedStatement = conn.prepareStatement(whatToDo);

            //execute query
            ResultSet rs = preparedStatement.executeQuery(whatToDo);
            while ( rs.next() )
            {
                list.add(rs.getString(columnLabel));
            }

            //close connection
            preparedStatement.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        comboList.setAll(list);

//        System.out.println(comboList+"działa?");
    }

    public ObservableList getComboList() {
        return comboList;
    }





}