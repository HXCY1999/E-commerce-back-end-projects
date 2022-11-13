package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private ItemCatMapper itemCatMapper;

    /**
     * Querying the database every time would be too slow
     * So query once to increase the speed
     */
    @Override
    public List<ItemCat> findItemCategoryList(Integer level) {
      HashMap<Integer,List<ItemCat>> map = getMap();

        if (level == 1){
            return map.get(0);
        }
        if (level ==2 ){
           return findTwoList(map);
        }
        return findThreeList(map);
        }

    @Override
    public void changeStatus(ItemCat itemCat) {
        itemCatMapper.updateById(itemCat);
    }

    @Override
    public void saveItemCate(ItemCat itemCat) {
        itemCat.setStatus(true);
        itemCatMapper.insert(itemCat);
    }

    @Override
    public void updateItemCat(ItemCat itemCat) {
        itemCatMapper.updateById(itemCat);
    }

    @Override
    public void deleteIteemCate(ItemCat itemCat) {
        Integer level = itemCat.getLevel();
        if (level == 3) {
            itemCatMapper.deleteById(itemCat);
        }
        if (level == 2){
            Integer parentId = itemCat.getId();
            QueryWrapper<ItemCat> itemCatQueryWrapper = new QueryWrapper<>();
            itemCatQueryWrapper.eq("parent_id", parentId);
            //Delete three-level menu
            itemCatMapper.delete(itemCatQueryWrapper);
            itemCatMapper.deleteById(parentId);
        }
        if (level ==1){
            Integer firstId = itemCat.getId();
            //get 2-level menu
            List<ItemCat> twoList = itemCatMapper.selectList(
                    new QueryWrapper<ItemCat>().eq("parent_id", firstId));
            //Traversing the secondary menu
            for (ItemCat twoCate : twoList){
                Integer id = twoCate.getId();
                QueryWrapper<ItemCat> itemCatQueryWrapper = new QueryWrapper<>();
                itemCatQueryWrapper.eq("parent_id", id);
                //Delete three-level menu
                itemCatMapper.delete(itemCatQueryWrapper);
                itemCatMapper.deleteById(id);
            }
            //Delete first level menu
            itemCatMapper.deleteById(firstId);
        }
    }

    private List<ItemCat> findThreeList(HashMap<Integer, List<ItemCat>> map) {
        List<ItemCat> oneList = findTwoList(map);
        // Traversing the secondary menu
                for (ItemCat oneItemCat :oneList){
                    // Secondary data may be null
                    List<ItemCat> twoList = oneItemCat.getChildren();
                    if(twoList == null || twoList.size()==0){
                        // indicates that there is no secondary data
                        // and this loop should be terminated and the next loop should be started.
                        continue;
                    }
                    //secondary list must have values, you can query the third-level list
                    for (ItemCat twoItemCat : twoList){
                        //Get secondary Id.Query tertiary data
                        int parentId = twoItemCat.getId();
                        List<ItemCat> threeList = map.get(parentId);
                        // Save tertiary to secondary data
                        twoItemCat.setChildren(threeList);
                    }
                }
        return oneList;
    }

    private List<ItemCat> findTwoList(HashMap<Integer,List<ItemCat>> map) {
        List<ItemCat> oneList = map.get(0);
        // Traversing the first level menu
        for (ItemCat parent : oneList){
            //get 1-level menu id
            Integer id = parent.getId();
            //find sub-menu
            List<ItemCat> twoList = map.get(id);
            parent.setChildren(twoList);
        }
        return oneList;
    }

    private HashMap getMap() {
        //Iterate through the database once to get all the data
        List<ItemCat> itemCatList = itemCatMapper.selectList(null);
        System.out.println(itemCatList);
        HashMap<Integer, List<ItemCat>> map = new HashMap<>();
        //Iterate through the data into the map
        for (ItemCat itemCat : itemCatList) {
            Integer parentId = itemCat.getParentId();
            if (!map.containsKey(parentId)) {
//            if (map.get(parentId) == null){
                //Create a list and add table data to it
                List<ItemCat> itemCatList1 = new ArrayList<>();
                itemCatList1.add(itemCat);
                map.put(parentId, itemCatList1);
            } else {
                //Not the first join, append to the list in the map
                List<ItemCat> itemCatList1 = map.get(parentId);
                itemCatList1.add(itemCat);
            }
        }
        return  map;
}

}
