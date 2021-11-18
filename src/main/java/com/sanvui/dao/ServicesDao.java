package com.sanvui.dao;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 05/11/2021-3:10 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface ServicesDao<T> {
    List<T> findAll();
    T findById(int id);
    boolean insert(List<T> list);
    boolean delete(int id);
    boolean update(List<T> list);
}
