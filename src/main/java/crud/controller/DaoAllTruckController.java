package crud.controller;

import crud.services.AllTruckService;
import crud.services.ViewService;
import crud.model.GenericDao;
import entity.AllTruck;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class DaoAllTruckController implements AllTruckService, ViewService {
    private String date;
    private String material;
    private String truckNumber;
    private int amount;
    private int finalAmount;
    private String seller;
    private String buyer;
    private String truckNr;
    private String transportOrder;
    private String salesContractNumber;
    private String purchaseContractNumber;
    private String documentName;
    private String color;

    private final GenericDao dao;

    private AllTruck allTruck;


    @Override
    public void add() {
        allTruck = new AllTruck();

        allTruck.setDate(this.date);
        allTruck.setMaterial(this.material);
        allTruck.setTruckNumber(this.truckNumber);
        allTruck.setAmount(this.amount);
        allTruck.setFinalAmount(this.finalAmount);
        allTruck.setSeller(this.seller);
        allTruck.setAmount(this.amount);
        allTruck.setBuyer(this.buyer);
        allTruck.setTruckNr(this.truckNr);
        allTruck.setTransportOrder(this.transportOrder);
        allTruck.setSalesContractNumber(this.salesContractNumber);
        allTruck.setPurchaseContractNumber(this.purchaseContractNumber);
        allTruck.setDocumentName(this.documentName);
        allTruck.setColor(this.color);
        dao.insert(allTruck);

    }


    @Override
    public void updateRecord(String idOfColumn, String newValue, int idOfRow) {
        dao.query(idOfColumn, newValue, idOfRow);
    }


    @Override
    public void remove(int id) {
        allTruck = (AllTruck) dao.findById(id);
        dao.delete(allTruck);
    }

    @Override
    public Object findById(int id) {
        allTruck = (AllTruck) dao.findById(id);
        return allTruck;
    }

    @Override
    public List<AllTruck> selectList() {
        return (List<AllTruck>) dao.select();
    }
}
