package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.RightsMapper;
import com.jt.pojo.Rights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RightsServiceImpl implements RightsService{


    @Autowired
    private RightsMapper rightsMapper;

    ////Left List Search
    @Override
    public List<Rights> findList() {
//        write sql by mapper file
//       List<Rights> rightsList = rightsMapper.selectRightsList();
//        return rightsList;
        //用MybatisPlus
        //1.find first level menu
        QueryWrapper<Rights> rightsQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Rights> parent = rightsQueryWrapper.eq("parent_id", 0);
        List<Rights> parentList = rightsMapper.selectList(parent);
        //Iterate through the first-level menu and add
        // the second-level menu to the children property of the first-level menu
        for (Rights oneRights : parentList){
            rightsQueryWrapper.clear();
            rightsQueryWrapper.eq("parent_id", oneRights.getId());
           List<Rights> twoList = rightsMapper.selectList(rightsQueryWrapper);
           oneRights.setChildren(twoList);
        }
        return parentList;
    }
}
