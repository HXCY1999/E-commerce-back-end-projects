package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;


    @Override
    public PageResult getItemList(PageResult pageResult) {
        String query = pageResult.getQuery();
        Integer pageNum = pageResult.getPageNum();
        Integer pageSize = pageResult.getPageSize();
        // get page object
        Page<Item> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.like("title", query);
        page = itemMapper.selectPage(page, itemQueryWrapper);

        return pageResult.setRows(page.getRecords()).setTotal(page.getTotal());
    }

    @Override
    public void saveItem(ItemVO itemVO) {
        Item item = itemVO.getItem();
        ItemDesc itemDesc = itemVO.getItemDesc();
        item.setStatus(true);
        itemMapper.insert(item);
        //itemDesc has no primary key to increment only, so need to get the id of the item
        itemDesc.setId(item.getId());
        itemDescMapper.insert(itemDesc);
    }
}
