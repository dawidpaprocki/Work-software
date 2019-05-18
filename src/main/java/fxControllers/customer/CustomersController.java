package fxControllers.customer;

import crud.model.Customer;
import crud.services.interfaces.AllTablesUpdateRecordService;
import crud.services.interfaces.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomersController {
    @FXML
    private Button refreshButton;
    @FXML
    private TableColumn<Customer, String> countryColumn;
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    @FXML
    private TableColumn<Customer, String> addressColumn;
    @FXML
    private TableColumn<Customer, String> nipColumn;
    @FXML
    private TableView<Customer> customerTable;

    private ObservableList<Customer> data;
    private CustomerService customerService;
    private AllTablesUpdateRecordService allTablesUpdateRecordService;

    public CustomersController(CustomerService customerService, AllTablesUpdateRecordService allTablesUpdateRecordService) {
        this.customerService = customerService;
        this.allTablesUpdateRecordService = allTablesUpdateRecordService;
    }

    public void initialize() {
        data = FXCollections.observableArrayList();
        List<Customer> customerList = customerService.selectList();
        data.setAll(customerList);
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        nipColumn.setCellValueFactory(new PropertyValueFactory<>("nip"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        customerTable.setItems(null);
        customerTable.setItems(data);
    }
    /**
     * <h2> doChange Method </h2>
     * <p>
     * Getting parameter of wanted cell to Edit.
     * Updating cell by inserting new value
     * Updating Data Base with new value.
     */

    public void doChange(TableColumn.CellEditEvent<Customer, String> tableStringCellEditEvent) {
        String newValue = String.valueOf(tableStringCellEditEvent.getNewValue());
        Long idOfRow = tableStringCellEditEvent.getRowValue().getId();
        String idOfColumn = tableStringCellEditEvent.getTableColumn().getId();
        allTablesUpdateRecordService.updateRecord(Customer.class,idOfColumn,newValue,idOfRow);
    }

}
