package combo;

import connection.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectListOfThings {

   private ObservableList combolist = FXCollections.observableArrayList();






    public SelectListOfThings(String whatToDo, String columnLabel, ObservableList list){

        Connection conn = null;
        PreparedStatement preparedStatement = null;


        try{
            //get connection
            conn = DBconnection.getConnection();

            //create preparedStatement
            preparedStatement = conn.prepareStatement(whatToDo);

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

//        System.out.println(combolist+"działa?");
    }

    public ObservableList getCombolist() {
        return combolist;
    }

    public void setCombolist(ObservableList combolist) {
        this.combolist = combolist;
    }




}