package fxControllers.msContracts;

import crud.services.interfaces.ContractsOpenService;
import crud.services.interfaces.MaterialService;
import crud.model.ContractsOpenSell;
import enums.MaterialTypes;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;
@Controller
public class OpenMsContractControllerSell {

    @FXML
    private Button refreshButton;
    @FXML
    private TableView<ContractsOpenSell> openMsContractTable;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnCustomerName;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnMaterialName;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnNrTruckContract;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnNrTruck;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnAmount;

    @FXML
    private TableColumn<ContractsOpenSell, String> columnContractName;


    private ObservableList<ContractsOpenSell> data;
    private ContractsOpenService<ContractsOpenSell> contractsOpenService;
    private MaterialService materialService;

    public OpenMsContractControllerSell(ContractsOpenService<ContractsOpenSell> contractsOpenService, MaterialService materialService) {
        this.contractsOpenService = contractsOpenService;
        this.materialService = materialService;
    }
    public void initialize() {
        data = FXCollections.observableArrayList();

        List<ContractsOpenSell> contractsOpenSells = contractsOpenService.selectList()
                .stream()
                .filter(e->e.getMaterial().getId()
                        .equals(MaterialTypes.BRASS.getId()))
                .collect(Collectors.toList());

        data.setAll(contractsOpenSells);

        columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        columnMaterialName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaterial().getName()));
        columnNrTruck.setCellValueFactory(new PropertyValueFactory<>("nrTruck"));
        columnNrTruckContract.setCellValueFactory(new PropertyValueFactory<>("nrTruckContract"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnContractName.setCellValueFactory(new PropertyValueFactory<>("contractName"));

        openMsContractTable.setItems(null);
        openMsContractTable.setItems(data);


    }



}
