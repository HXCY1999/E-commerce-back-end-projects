package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@TableName("user")//Object-to-table correspondence
public class User extends BasePojo{
    @TableId(type = IdType.AUTO)//primary key
    private Integer id;
//    @TableField("username")
//    Attribute to Field Mapping
//Omitted if the attribute name is the same as the field
    private String username;
    private String password;
    private String phone;
    private String email;
    private Boolean status;     //true:normal false: not using
}
