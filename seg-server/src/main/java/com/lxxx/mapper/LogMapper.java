package com.lxxx.mapper;

import com.lxxx.entity.OperateLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @ClassName: LogMapper
 * @Author: ILx
 * @Date: 2024/7/31 17:31
 */
@Mapper
public interface LogMapper {
    /**
     * 记录日志
     *
     * @param: [log]
     * @return: void
     * @Author: ILx
     * @Date: 2024/8/1
     */
    public void insert(OperateLog log);
}
