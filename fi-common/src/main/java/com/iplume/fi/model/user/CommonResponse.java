package com.iplume.fi.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * fi system common response.
 *
 * @author: lingchen
 * @date: 2021/8/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    /**
     * 状态码.
     */
    private Integer code;

    /**
     * 错误信息.
     */
    private String message;

    /**
     * 数据实体.
     */
    private T data;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
