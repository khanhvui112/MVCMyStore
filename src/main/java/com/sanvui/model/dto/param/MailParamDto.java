package com.sanvui.model.dto.param;

import lombok.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: VuiSK
 * @created: 04/12/2021-8:38 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailParamDto {
    private String[] mailTo;
    private String[] ccList;
    private String[] bccList;

    private String subject;
    private String content;

    private String fileName;

    private boolean isHtml = true;
}
