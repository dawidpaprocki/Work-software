package crud.controller.controllers;

import crud.controller.services.AllViewService;
import crud.controller.services.ViewService;
import crud.model.GenericDao;
import entity.AllView;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder(toBuilder = true)
public class DaoAllViewController implements AllViewService, ViewService {
    private String data;
    private String material;
    private String truck;
    private int amount;
    private int finalAmount;
    private String froms;
    private String tos;
    private String truckNr;
    private String transportOrder;
    private String vk;
    private String ek;
    private String amsDoc;
    private String color;

    private final GenericDao dao;

    private AllView allView;


    @Override
    public void add() {
        allView = new AllView();

        allView.setData(this.data);
        allView.setMaterial(this.material);
        allView.setTruck(this.truck);
        allView.setAmount(this.amount);
        allView.setFinalAmount(this.finalAmount);
        allView.setFroms(this.froms);
        allView.setAmount(this.amount);
        allView.setTos(this.tos);
        allView.setTruckNr(this.truckNr);
        allView.setTransportOrder(this.transportOrder);
        allView.setVk(this.vk);
        allView.setEk(this.ek);
        allView.setAmsDoc(this.amsDoc);
        allView.setColor(this.color);
        dao.insert(allView);

    }


    @Override
    public void updateRecord(String idOfColumn, String newValue, int idOfRow) {


        dao.query(idOfColumn,newValue,idOfRow);
    }


    @Override
    public void remove(int id) {
        allView = (AllView) dao.findById(id);
        dao.delete(allView);
    }

    @Override
    public Object findById(int id) {
        allView = (AllView) dao.findById(id);
        return allView;
    }

    @Override
    public List<AllView> selectList() {
        return (List<AllView>) dao.select();
    }
}
