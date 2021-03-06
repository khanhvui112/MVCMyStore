package com.sanvui.service;

import com.sanvui.model.dto.resp.ManufacturerResponseDto;
import com.sanvui.model.entity.Manufacturer;
import com.sanvui.repository.ManufacturerRepository;
import javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 06/11/2021-10:00 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Cacheable("manufacturers")
    public List<ManufacturerResponseDto> findAllCustom() throws IOException {
        return manufacturerRepository.getAllBase();
    }

/*    @Cacheable("manufacturers")
    public List<Manufacturer> findAll(){
        return manufacturerRepository.findAll(Sort.by("maId").ascending());
    }*/

}
