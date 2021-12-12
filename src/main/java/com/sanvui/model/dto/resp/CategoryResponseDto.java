package com.sanvui.model.dto.resp;

import lombok.*;

/**
 * @author: VuiSK
 * @created: 05/12/2021-12:34 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryResponseDto {
    private int caId;
    private String caName;
    private String caLink;
}
