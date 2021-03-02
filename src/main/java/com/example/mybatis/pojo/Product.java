package com.example.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mybatis_product")
@ApiModel(value = "商品表")
public class Product implements Serializable {

	@TableId(type = IdType.AUTO)
	@Column(comment = "商品id", isKey = true, isAutoIncrement = true)
	private int id;

	@Column(name = "name", comment = "商品名称")
	@ApiModelProperty(value = "商品名称")
	private String name;

	@Column(name = "category", comment = "商品类型")
	@ApiModelProperty(value = "商品类型")
	private String category;

	@Column(name = "price", comment = "商品价格")
	@ApiModelProperty(value = "商品价格")
	private float price;

	@Column(name = "place", comment = "商品产地")
	@ApiModelProperty(value = "商品产地")
	private String place;

	@Column(name = "code", comment = "商品条形码")
	@ApiModelProperty(value = "商品条形码")
	private String code;
}
