package crud.controller.services;

import java.util.List;

public interface AllViewService<T>{

    void add(String data, String material, String truck, int amount, int finalAmount, String froms, String tos, String truckNr,
             String transportOrder, String vk, String ek, String amsDoc, String color);
    void remove(int id);
    T findById(int id);
    List<String> selectList();
}
