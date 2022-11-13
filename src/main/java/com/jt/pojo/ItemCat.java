package com.jt.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@Accessors(chain = true)
@TableName("item_cat")
public class ItemCat extends BasePojo{
    @TableId(type = IdType.AUTO)//Primary key self-increment
    private Integer id;         //primary key
    private Integer parentId;   //Define parent menu Open hump rule mapping
    private String name;        //category name
    private Boolean status;     //category status 0 not using 1 normal using
    private Integer level;      //item level   1 2 3
    @TableField(exist = false)
    private List<ItemCat> children; //It's business data, not database fields.
}
