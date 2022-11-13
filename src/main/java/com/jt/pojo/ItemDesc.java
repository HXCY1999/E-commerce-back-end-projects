package com.jt.pojo;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@TableName("item_desc")
public class ItemDesc extends BasePojo{
    @TableId
    private Integer id;      //item.id keep the same
    private String itemDesc; //html code
}
