package com.sanvui.repository.custom;

import java.io.IOException;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 06/12/2021-9:11 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface CustomizeBaseRepository<T> {
    List<T> getAllBase() throws IOException;
}
