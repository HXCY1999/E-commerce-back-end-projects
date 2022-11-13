package com.jt.pojo;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

//pojo base class, completes 2 tasks, 2 dates, implements serialization
@Data
@Accessors(chain=true)
public class BasePojo implements Serializable{
	@TableField(fill = FieldFill.INSERT)
	private Date created;	//assign value when enter database
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updated;	//assign value when enter database or update
}
