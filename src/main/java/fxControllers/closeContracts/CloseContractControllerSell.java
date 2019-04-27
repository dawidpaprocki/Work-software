package fxControllers.closeContracts;

import crud.model.ContractsClose;
import crud.model.ContractsOpenSell;
import crud.model.Material;
import crud.services.ContractsCloseService;
import crud.services.ContractsOpenService;
import crud.services.MaterialService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CloseContractControllerSell {

    @FXML
    private Button refreshButton;

    @FXML
    private TableView<ContractsOpenSell> closeContractTable;


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

    @FXML
    private ComboBox choiceMaterialContract;

    private ObservableList materialList = FXCollections.observableArrayList();
    private ObservableList<ContractsOpenSell> data;
    private ContractsCloseService contractsCloseService ;
    private ContractsOpenService<ContractsOpenSell> contractsOpenServiceSell;
    private MaterialService materialService;

    public CloseContractControllerSell(ContractsCloseService contractsCloseService, ContractsOpenService<ContractsOpenSell> contractsOpenServiceSell, MaterialService materialService) {
        this.contractsCloseService = contractsCloseService;
        this.contractsOpenServiceSell = contractsOpenServiceSell;
        this.materialService = materialService;
    }

    private List<ContractsClose> allClosedContracts;

    public void initialize() {
        allClosedContracts = contractsCloseService.findAll();
        materialList.setAll(materialService.selectList());
        choiceMaterialContract.setItems(materialList);
    }

    private void selectMaterial(Long materialId) {
        List<ContractsOpenSell> closedSellContracts = allClosedContracts.stream()
                .map(ContractsClose::getContractsOpenSell)
                .filter(contractsOpenSell -> contractsOpenSell.getMaterialId().equals(materialId))
                .collect(Collectors.toList());
        data = FXCollections.observableArrayList();
        data.setAll(closedSellContracts);
        columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        columnMaterialName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaterial().getName()));
        columnNrTruck.setCellValueFactory(new PropertyValueFactory<>("nrTruck"));
        columnNrTruckContract.setCellValueFactory(new PropertyValueFactory<>("nrTruckContract"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnContractName.setCellValueFactory(new PropertyValueFactory<>("contractName"));
        closeContractTable.setItems(null);
        closeContractTable.setItems(data);
    }


    public void MaterialContractList(ActionEvent actionEvent) {
        Material chosenMaterial =(Material) choiceMaterialContract.getValue();
        selectMaterial(chosenMaterial.getId());
    }
}
