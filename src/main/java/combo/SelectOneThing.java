package combo;

import connection.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectOneThing {

   private ObservableList combolist = FXCollections.observableArrayList();


    private int anInt;
    private String anSrting;

    public SelectOneThing(String whatToDo, String columnLabel){

        Connection conn = null;
        PreparedStatement preparedStatement = null;



        try{
            //get connection
            conn = DBconnection.getConnection();

            //create preparedStatement
            preparedStatement = conn.prepareStatement(whatToDo);

            //execute query
            ResultSet rs = preparedStatement.executeQuery(whatToDo);
            while (rs.next()) {


                try{
                    Integer.parseInt(rs.getString(columnLabel));
                    anInt = rs.getInt(columnLabel);
                    // is an integer!
                } catch (NumberFormatException e) {
                    anSrting = rs.getString(columnLabel);
                    // not an integer! its String !
                }

            }
            //close connection
            preparedStatement.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

//        System.out.println(combolist+"działa?");
    }
    public int getId() {
        return anInt;
    }

    public String getSrting() {
        return anSrting;
    }

    public void setAnSrting(String anSrting) {
        this.anSrting = anSrting;
    }

    public ObservableList getCombolist() {
        return combolist;
    }

    public void setCombolist(ObservableList combolist) {
        this.combolist = combolist;
    }




}