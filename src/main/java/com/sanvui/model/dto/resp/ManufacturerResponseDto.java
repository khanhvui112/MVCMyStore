package com.sanvui.model.dto.resp;

import lombok.*;

import java.io.Serializable;

/**
 * @author: VuiSK
 * @created: 07/12/2021-9:07 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ManufacturerResponseDto implements Serializable {
    private int maId;
    private String maName;
}
