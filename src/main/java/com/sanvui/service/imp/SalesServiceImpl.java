package com.sanvui.service.imp;

import com.sanvui.model.entity.Sales;
import com.sanvui.repository.SalesRepository;
import com.sanvui.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * @author: VuiSK
 * @created: 08/12/2021-6:28 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class SalesServiceImpl implements BaseService<Sales>{

    @Autowired
    private SalesRepository salesRepository;

    @Override
    public List<Sales> findAll() {
        return salesRepository.findAll();
    }

    @Override
    public Sales findById(Integer id) {
        Optional<Sales> sales = salesRepository.findById(id);
        return sales.orElse(null);
    }
}
