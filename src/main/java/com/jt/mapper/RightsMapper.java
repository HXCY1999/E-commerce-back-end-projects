package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Rights;

import java.util.List;

public interface RightsMapper extends BaseMapper<Rights> {
    List<Rights> selectRightsList();
}
