package table;


import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class TableColor {

    private String color = "#fd6c6cff";


    public TableView<Table> color(TableView<Table> tables, TablePosition tablePosition , String color ) {

        tables.setRowFactory(new Callback<>() {
            @Override
            public TableRow<Table> call(TableView<Table> param) {
                final TableRow<Table> row = new TableRow<>() {
                    @Override
                    protected void updateItem(Table item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setStyle("");
                        } else if (getIndex() == tablePosition.getRow()) {

                            setStyle("-fx-control-inner-background: '" + color + "'  ;  -fx-border-color:  '" + color + "'; -fx-table-cell-border-color:'" + color + "';  -fx-border-width: 0px;  ");
                            item.setColor(color);

                            //errors to null and empty space need to check this expetion
                        } else if (color != null && !color.equals("null")) {
                            setStyle("-fx-control-inner-background: '" + item.getColor() + "' ;  -fx-border-color:  '" + item.getColor() + "'; -fx-table-cell-border-color:'" + item.getColor() + "';  -fx-border-width: 0px;");
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
