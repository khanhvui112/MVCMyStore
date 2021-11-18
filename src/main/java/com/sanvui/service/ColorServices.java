package com.sanvui.service;

import com.sanvui.entity.Color;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 06/11/2021-9:00 AM
 * @mailto: sanvankhanh@gmail.com
 */

public class ColorServices {
    private static  final  BaseServices services = new BaseServices(Color.class);

    /*
    * get all data in table Color
    * return List Color
    * */
    public List<Color> findAll(){
        return services.findAll();
    }

    /*
    * INSERT List data to database
    * parameter: List color
    * request: if success return true  else return false
    * put data to insert
    * */
    public boolean insert(List<Color> colorList){
        return services.insert(colorList);
    }

    /*
     * Update List data to database
     * parameter: List color
     * request: if success return true  else return false
     * put data to insert
     * */
    public boolean update(List<Color> colorList){
        return services.update(colorList);
    }
}
