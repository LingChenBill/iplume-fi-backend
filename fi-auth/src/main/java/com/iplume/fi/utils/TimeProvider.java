package com.iplume.fi.utils;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Time provider class.
 *
 * @author: lingchen
 * @date: 2021/8/19
 */
@Component
public class TimeProvider implements Serializable {

    private static final long serialVersionUID = -3301695478208950415L;

    /**
     * 获取当前日期.
     *
     * @return
     */
    public Date now() {
        return new Date();
    }

}
