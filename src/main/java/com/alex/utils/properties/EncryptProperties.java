package com.alex.utils.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 自定义解析key
 * @Title: EncryptProperties
 * @Author: zjt
 * @CreateTime: 2022/6/19 11:53
 */
@ConfigurationProperties(prefix = "spring.alex.encrypt")
@Data
public class EncryptProperties {

    private final static String DEFAULT_KEY = "www.alex-hub.com";
    private String key = DEFAULT_KEY;
}
