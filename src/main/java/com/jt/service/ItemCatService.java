package com.jt.service;

import com.jt.pojo.ItemCat;

import java.util.List;

public interface ItemCatService {
    List<ItemCat> findItemCategoryList(Integer level);

    void changeStatus(ItemCat itemCat);


    void saveItemCate(ItemCat itemCat);

    void updateItemCat(ItemCat itemCat);

    void deleteIteemCate(ItemCat itemCat);
}
