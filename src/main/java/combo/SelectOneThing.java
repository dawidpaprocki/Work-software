package combo;

import connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectOneThing {

   private ObservableList comboList = FXCollections.observableArrayList();


    private int anInt;
    private String anString;

    public SelectOneThing(String whatToDo, String columnLabel){

        try{
            //get connection
            Connection conn = DBConnection.getConnection();

            //create preparedStatement
            PreparedStatement  preparedStatement = conn.prepareStatement(whatToDo);

            //execute query
            ResultSet rs = preparedStatement.executeQuery(whatToDo);
            while (rs.next()) {


                try{
                    Integer.parseInt(rs.getString(columnLabel));
                    anInt = rs.getInt(columnLabel);
                    // is an integer!
                } catch (NumberFormatException e) {
                    anString = rs.getString(columnLabel);
                    // not an integer! its String !
                }

            }
            //close connection
            preparedStatement.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

//        System.out.println(comboList+"działa?");
    }
    public int getId() {
        return anInt;
    }

    public String getString() {
        return anString;
    }

    public void setAnString(String anString) {
        this.anString = anString;
    }

    public ObservableList getComboList() {
        return comboList;
    }

    public void setComboList(ObservableList comboList) {
        this.comboList = comboList;
    }




}