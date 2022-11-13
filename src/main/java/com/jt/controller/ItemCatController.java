package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.SysResult;
import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // identifies the controller class Return value is Jason
@RequestMapping("/itemCat") // Extraction of public parts
@CrossOrigin    // Cross-domain operations on front and back ends
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    /**
     * /itemCat/findItemCatList/{level}
     */
    @GetMapping("findItemCatList/{level}")
    public SysResult findItemCatList(@PathVariable Integer level){
        List<ItemCat> itemCatList = itemCatService.findItemCategoryList(level);
        return SysResult.success(itemCatList);
    }
    /**
     * request path:: /itemCat/status/{id}/{status}
     */

    @PutMapping("/status/{id}/{status}")
    public SysResult changeStatus(ItemCat itemCat){
        itemCatService.changeStatus(itemCat);
        return SysResult.success();
    }
    /**
     * add item
     * http://localhost:8091/itemCat/saveItemCat
     */
    @PostMapping("saveItemCat")
    public SysResult saveItemCat(@RequestBody ItemCat itemCat){
        itemCatService.saveItemCate(itemCat);
        return SysResult.success();
    }
    /**
     * modify
     * http://localhost:8091/itemCat/updateItemCat
     */

    @PutMapping("updateItemCat")
    public SysResult updateItemCat(@RequestBody ItemCat itemCat){
        itemCatService.updateItemCat(itemCat);
        return SysResult.success();
    }
    /**
     * http://localhost:8091/itemCat/deleteItemCat?id=1185&level=2
     */
    @DeleteMapping("deleteItemCat")
    public SysResult deleteItemCat(ItemCat itemCat){
        itemCatService.deleteIteemCate(itemCat);
        return SysResult.success();
    }
}
