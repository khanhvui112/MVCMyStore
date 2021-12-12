package com.sanvui.repository.custom;

import com.sanvui.model.dto.resp.MenuResponseDto;

import java.io.IOException;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 05/12/2021-11:55 AM
 * @mailto: sanvankhanh@gmail.com
 */

public interface CustomizeMenuRepository {
    List<MenuResponseDto> getAllMenuResp() throws IOException;
}
