package com.jt.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@TableName("item")
public class Item extends BasePojo{
    @TableId(type = IdType.AUTO)
    private Integer id;         //ite id number
    private String title;       //item title information
    private String sellPoint;   //sell point information
    private Integer price;      //item price
    private Integer num;        //Commodities number
    private String images;       //Commodities picture
    private Integer itemCatId;  //Commodities category id number
    private Boolean status;     //status information    0 not selling 1 on sell
}
