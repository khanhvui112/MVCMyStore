package com.sanvui.service;

import com.sanvui.model.dto.resp.ColorResponseDto;
import com.sanvui.model.entity.Color;
import com.sanvui.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 06/11/2021-9:00 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Service
@Transactional(rollbackFor = SQLException.class)
public class ColorServices {

    @Autowired
    private ColorRepository repository;

    public Color findById(Integer id){
        return repository.findById(id).get();
    }

    /*
     * get all data in table Color
     * return List Color
     * */
    public List<Color> findAll() {
        return repository.findAll();
    }

    @Cacheable("colors")
    public List<ColorResponseDto> findAllCustom() throws IOException {
        return repository.getAllBase();
    }

    /*
     * INSERT List data to database
     * parameter: List color
     * request: if success return true  else return false
     * put data to insert
     * */
    public boolean insert(List<Color> colorList) {
        return !(repository.saveAll(colorList).isEmpty());
    }

    /*
     * Update List data to database
     * parameter: List color
     * request: if success return true  else return false
     * put data to insert
     * */
    public boolean update(List<Color> colorList) {
        return !(repository.saveAll(colorList).isEmpty());
    }
}
