package com.sanvui.service;

import com.sanvui.model.entity.Footer;
import com.sanvui.repository.FooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 17/11/2021-2:27 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Service
public class FooterService {

    @Autowired
    private FooterRepository repository;

    public List<Footer> findAll(){
        return  (List<Footer>)repository.findAll();
    }
}
