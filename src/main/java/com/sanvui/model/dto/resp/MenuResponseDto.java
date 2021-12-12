package com.sanvui.model.dto.resp;

import com.sanvui.model.entity.Menu;
import lombok.*;

/**
 * @author: VuiSK
 * @created: 05/12/2021-11:52 AM
 * @mailto: sanvankhanh@gmail.com
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MenuResponseDto {
    private int meId;
    private String meName;
    private String meLink;
    private Integer status;
}
