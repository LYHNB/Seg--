package com.lxxx.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页查询返回结果
 * @ClassName: PageResult
 * @Author: ILx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult implements Serializable {
    /**
     *总记录数
     */
    private long total;
    /**
     * 当前页数据集合
     */
    private List records;
}
