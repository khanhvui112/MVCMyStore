package com.sanvui.model.dto.resp;

import com.sanvui.model.entity.Color;
import lombok.*;

/**
 * @author: VuiSK
 * @created: 06/12/2021-9:22 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ColorResponseDto {

    private int colorId;

    private String colorName;
}
