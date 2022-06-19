package com.alex.utils.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 返回结果集
 * @Title: RespDTO
 * @Author: zjt
 * @CreateTime: 2022/6/19 11:47
 */
@Getter
@Setter
@Data
public class RespDTO {
    private Integer status;
    private String msg;
    private Object obj;


    private RespDTO() {
    }

    private RespDTO(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }


    public static RespDTO build() {
        return new RespDTO();
    }

    public static RespDTO ok(String msg) {
        return new RespDTO(200, msg, null);
    }

    public static RespDTO ok(String msg, Object obj) {
        return new RespDTO(200, msg, obj);
    }

    public static RespDTO error(String msg) {
        return new RespDTO(500, msg, null);
    }

    public static RespDTO error(String msg, Object obj) {
        return new RespDTO(500, msg, obj);
    }


}
