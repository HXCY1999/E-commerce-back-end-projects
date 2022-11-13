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
@TableName("rights")
public class Rights extends BasePojo{
    @TableId(type = IdType.AUTO)//primary key
    private Integer id;
    private String name;
    private Integer parentId;
    private String path;
    private Integer level;
    @TableField(exist = false)
    private List<Rights> children; //Not an intrinsic property of the table
}
