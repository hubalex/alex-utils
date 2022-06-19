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

}
