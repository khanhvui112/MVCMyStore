package com.sanvui.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author: VuiSK
 * @created: 16/11/2021-3:01 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private int meId;
    private String menuName;
    private String menuLink;

    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    private LocalDateTime createDate;
    private int empId;
    private int status;
}
