package table;

import combo.EditTable;
import javafx.event.ActionEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class tableColor {
    private EditTable editTable;
    private String color = "#fd6c6cff";


    public TableView<table> color(TableView<table> tables, TablePosition tablePosition , String color ) {

        tables.setRowFactory(new Callback<TableView<table>, TableRow<table>>() {
            @Override
            public TableRow<table> call(TableView<table> param) {
                final TableRow<table> row = new TableRow<table>() {
                    @Override
                    protected void updateItem(table item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setStyle("");
                        } else if (getIndex() == tablePosition.getRow()) {

                            setStyle("-fx-control-inner-background: '" + color + "'  ;  -fx-border-color:  '" + color + "'; -fx-table-cell-border-color:'" + color + "';  -fx-border-width: 0px;  ");
                            item.setColor(color);


                        } else {
                            setStyle("-fx-control-inner-background: '" + item.Color() + "' ;  -fx-border-color:  '" + item.Color() + "'; -fx-table-cell-border-color:'" + item.Color() + "';  -fx-border-width: 0px;");
//
                        }
                    }
                };
                return row;
            }
        });


        return tables;
    }


}
