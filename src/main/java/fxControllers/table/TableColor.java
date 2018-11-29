package fxControllers.table;


import entity.AllView;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class TableColor {


    private String color = "#fd6c6cff";
    /**
     * <h1> Table color Class</h1>
     *
     * This Class is responsible for setting background color to row.
     *
     * @param tables passing specify tables in which we want make changes.
     * @param tablePosition passing position of selected item.
     * @param color passing color which we want to set to specify row.
     */

    public TableView<AllView> color(TableView<AllView> tables, TablePosition tablePosition , String color ) {

        tables.setRowFactory(new Callback<>() {
            @Override
            public TableRow<AllView> call(TableView<AllView> param) {
                final TableRow<AllView> row = new TableRow<>() {
                    @Override
                    protected void updateItem(AllView item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setStyle("");
                        } else if (getIndex() == tablePosition.getRow()) {

                            setStyle("-fx-control-inner-background: '" + color + "'  ;  -fx-border-color:  '" + color + "'; -fx-table-cell-border-color:'" + color + "';  -fx-border-width: 0px;  ");
                            item.setColor(color);

                            //errors to null and empty space need to check this exception
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
