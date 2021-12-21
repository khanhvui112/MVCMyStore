package com.sanvui.model.dto.resp;

import lombok.*;


/**
 * @author: VuiSK
 * @created: 16/12/2021-1:27 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImagesResponseDto {
    private int imageId;
    private int product_id;
    private String imageName;
    private String imageType;
}
