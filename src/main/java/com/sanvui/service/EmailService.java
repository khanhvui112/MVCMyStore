package com.sanvui.service;


import com.sanvui.model.dto.param.MailParamDto;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * @author: VuiSK
 * @created: 03/12/2021-4:14 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface EmailService {

    void sendMail(MailParamDto mailParamDto) throws MessagingException, UnsupportedEncodingException;
}
