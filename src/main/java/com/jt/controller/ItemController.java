package com.jt.controller;

import com.jt.service.ItemService;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * request path: /item/getItemList?query=&pageNum=1&pageSize=10
     * request type: get
     * request variable: use pageResult object to receive
     */
    @GetMapping("getItemList")
    public SysResult getItemList(PageResult pageResult){
         pageResult = itemService.getItemList(pageResult);
        return SysResult.success(pageResult);
    }

    /**
     * /**
     *      *  - request path: http://localhost:8091/item/saveItem
     *      *  - request type: post
     *      *  - Analysis of front-end passing parameters received using ItemVO object  JSON
     *      *  - return value : SysResult object
     *      */
    @PostMapping("saveItem")
   public SysResult saveItem(@RequestBody ItemVO itemVO){
        itemService.saveItem(itemVO);
        return SysResult.success();
   }
}
