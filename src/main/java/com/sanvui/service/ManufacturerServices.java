package com.sanvui.service;

import com.sanvui.model.entity.Manufacturer;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 06/11/2021-10:00 AM
 * @mailto: sanvankhanh@gmail.com
 */

public class ManufacturerServices {
    private static  final  BaseServices services = new BaseServices(Manufacturer.class);
    /*
     * get all data in table Manufacturer
     * return List Color
     * */
    public List<Manufacturer> findAll(){
        return services.findAll();
    }

    /*
     * INSERT List data to database
     * parameter: List Manufacturer
     * request: if success return true  else return false
     * put data to insert
     * */
    public boolean insert(List<Manufacturer> manufacturerList){
        return services.insert(manufacturerList);
    }

    /*
     * Update List data to database
     * parameter: List Manufacturer
     * request: if success return true  else return false
     * put data to insert
     * */
    public boolean update(List<Manufacturer> manufacturerList){
        return services.update(manufacturerList);
    }
}
