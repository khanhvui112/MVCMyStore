package com.sanvui.model.dao;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 16/11/2021-2:09 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface JsonDao<T> {
    String convertToJson(List<T> ts) throws JsonProcessingException;
}
