package fxControllers.closeContracts;

import crud.model.ContractsOpenBuy;
import crud.model.Material;
import crud.services.interfaces.ContractsOpenService;
import crud.services.interfaces.MaterialService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CloseContractControllerBuy {

    @FXML
    private Button refreshButton;

    @FXML
    private TableView<ContractsOpenBuy> closeContractTable;


    @FXML
    private TableColumn<ContractsOpenBuy, String> columnCustomerName;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnMaterialName;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnNrTruckContract;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnNrTruck;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnAmount;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnContractName;

    @FXML
    private ChoiceBox choiceMaterialContract;

    private ObservableList materialList = FXCollections.observableArrayList();
    private ObservableList<ContractsOpenBuy> data;
    private ContractsOpenService contractsOpenBuyService;
    private MaterialService materialService;

    public CloseContractControllerBuy(ContractsOpenService<ContractsOpenBuy> contractsOpenBuyService, MaterialService materialService) {
        this.contractsOpenBuyService = contractsOpenBuyService;
        this.materialService = materialService;
    }

    private List<ContractsOpenBuy> closedContracts;

    public void initialize() {
        closedContracts = contractsOpenBuyService.selectList();
        materialList.setAll(materialService.selectList());
        choiceMaterialContract.setItems(materialList);
    }

    private void selectMaterial(Material chosenMaterial) {
        List<ContractsOpenBuy> closedBuyContracts = closedContracts.stream()
                .filter(contractsOpenBuy -> contractsOpenBuy.getOpenClose()== 1 && contractsOpenBuy.getMaterial().equals(chosenMaterial))
                .collect(Collectors.toList());
        data = FXCollections.observableArrayList();
        data.setAll(closedBuyContracts);
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
        Material chosenMaterial = (Material) choiceMaterialContract.getValue();
        selectMaterial(chosenMaterial);
    }


}
