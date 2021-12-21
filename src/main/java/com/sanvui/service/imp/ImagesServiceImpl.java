package com.sanvui.service.imp;

import com.sanvui.model.entity.Images;
import com.sanvui.repository.ImagesRepository;
import com.sanvui.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 16/12/2021-5:58 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    private ImagesRepository imagesRepository;

    @Override
    public List<Images> findByProductId(Integer productId) {
        return imagesRepository.findByProductId(productId);
    }
}
