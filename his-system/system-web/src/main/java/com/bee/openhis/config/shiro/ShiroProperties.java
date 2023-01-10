package com.bee.openhis.config.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "shiro") //EnableConfigurationProperties
public class ShiroProperties {
    /**
     * 密码加密方式
     */
    private String hashAlgorithmName = "md5";
    /**
     * 密码散列次数
     */
    private Integer hashIterations = 2;

    /**
     * 不拦击的路径
     */
    private String[] anonUrls;

    /**
     * 拦截的路径
     */
    private String[] authUrls;
}
