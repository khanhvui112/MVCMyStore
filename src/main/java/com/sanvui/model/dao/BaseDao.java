package com.sanvui.model.dao;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 05/11/2021-11:33 AM
 * @mailto: sanvankhanh@gmail.com
 */

public interface BaseDao<T> {
    //    find all
    List<T> findAll();

    /*
    * find by id
    * param: int id
    * request: a object
    * */
    T findById(int id);

    /*
    * insert to database
    * param: List<Object>
    * if success return true else return false
    */
    boolean insert(List<T> list);

    /*
    * delete object by id
    * if success return true else return false
    *
    * */
    boolean delete(int id);

    /*
     * update object
     * param: a object
     * if success return true else return false
     * */
    boolean update(List<T> list);

    boolean deleteMultiple(List<Integer> ids);
}
