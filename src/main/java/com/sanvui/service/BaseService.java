package com.sanvui.service;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 26/11/2021-3:20 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface BaseService<T> {
    List<T> findAll();

    T findById(Integer id);
}
