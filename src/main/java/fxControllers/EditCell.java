package fxControllers;

import crud.services.ViewService;
import entity.ContractsOpenBuy;
import javafx.scene.control.TableColumn;

public class EditCell {

    public void doChange(TableColumn.CellEditEvent<ContractsOpenBuy, String> tableStringCellEditEvent, ViewService viewService) {

        String newValue = tableStringCellEditEvent.getNewValue();
        int idOfRow = tableStringCellEditEvent.getRowValue().getId();
        String idOfColumn = tableStringCellEditEvent.getTableColumn().getId();
        idOfColumn = idOfColumn.substring(6);

        viewService.updateRecord(idOfColumn, newValue, idOfRow);
    }

    
}
