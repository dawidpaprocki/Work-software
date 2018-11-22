package crud.controller.controllers;

import crud.controller.services.AllViewService;
import crud.controller.services.ViewService;
import crud.model.GenericDao;
import entity.AllView;

import java.util.List;

public class DaoAllViewController implements AllViewService, ViewService {

    private final GenericDao dao;

    public DaoAllViewController(GenericDao dao) {
        this.dao = dao;
    }

    private AllView allView;


    @Override
    public void add(String data, String material, String truck, int amount, int finalAmount, String froms,
                    String tos, String truckNr, String transportOrder, String vk, String ek, String amsDoc, String color) {
        allView = new AllView();

        allView.setData(data);
        allView.setMaterial(material);
        allView.setTruck(truck);
        allView.setAmount(amount);
        allView.setFinalAmount(finalAmount);
        allView.setFroms(froms);
        allView.setAmount(amount);
        allView.setTos(tos);
        allView.setTruckNr(truckNr);
        allView.setTransportOrder(transportOrder);
        allView.setVk(vk);
        allView.setEk(ek);
        allView.setAmsDoc(amsDoc);
        allView.setColor(color);
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
