package com.alex.utils.resp;

import com.alex.utils.annotation.Encrypt;
import com.alex.utils.entity.RespDTO;
import com.alex.utils.properties.EncryptProperties;
import com.alex.utils.util.AESUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description: 加密返回值
 * @Title: EncryptResponse
 * @Author: zjt
 * @CreateTime: 2022/6/19 12:04
 */
@ControllerAdvice
@EnableConfigurationProperties(EncryptProperties.class)
public class EncryptResponse implements ResponseBodyAdvice<RespDTO> {

    private ObjectMapper om = new ObjectMapper();
    @Autowired
    EncryptProperties encryptProperties;
    
    /**
     * @Description  这个方法用来判断什么样的接口需要加密，参数 returnType 表示返回类型，我们这里的判断逻辑就是方法是否含有 @Encrypt 注解，如果有，表示该接口需要加密处理，如果没有，表示该接口不需要加密处理
     * 
     * @Author zjt
     * @CreateTime 2022/6/19 12:11
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }
    
    
    /**
     * @Description  这个方法会在数据响应之前执行，也就是我们先对响应数据进行二次处理，处理完成后，才会转成 json 返回。我们这里的处理方式很简单，RespBean 中的 status 是状态码就不用加密了，另外两个字段重新加密后重新设置值即可
     * 
     * @Author zjt
     * @CreateTime 2022/6/19 12:11
     */
    @Override
    public RespDTO beforeBodyWrite(RespDTO body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        byte[] keyBytes = encryptProperties.getKey().getBytes();
        try {
            if (body.getMsg()!=null) {
                body.setMsg(AESUtils.encrypt(body.getMsg().getBytes(),keyBytes));
            }
            if (body.getObj() != null) {
                body.setObj(AESUtils.encrypt(om.writeValueAsBytes(body.getObj()), keyBytes));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}
