package com.sanvui.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: VuiSK
 * @created: 23/11/2021-10:18 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("api.config")
public class ApiProperties {
    private String url;
    private Integer timeOut;
}
