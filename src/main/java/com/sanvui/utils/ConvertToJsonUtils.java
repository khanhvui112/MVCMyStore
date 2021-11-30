package com.sanvui.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sanvui.model.dao.JsonDao;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 16/11/2021-2:02 PM
 * @mailto: sanvankhanh@gmail.com
 */


public class ConvertToJsonUtils<T> implements JsonDao<T> {
    private Class<T> classTyper;

    public ConvertToJsonUtils(Class<T> classTyper) {
        this.classTyper = classTyper;
    }

    @Override
    public String convertToJson(List<T> ts) throws JsonProcessingException {
        //            create objectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        //            convert to Json
        String arrayToJson = objectMapper.writeValueAsString(ts);
        return arrayToJson;
    }

}
