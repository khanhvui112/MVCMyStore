package com.sanvui.service;

import com.sanvui.model.entity.Images;

import java.awt.*;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 16/12/2021-5:57 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface ImagesService {
    List<Images> findByProductId(Integer productId);
}
