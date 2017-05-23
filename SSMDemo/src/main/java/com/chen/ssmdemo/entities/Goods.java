/**
 * 
 */
package com.chen.ssmdemo.entities;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author chenti
 *为了实现校验，在成员变量上设置了一些注解信息
 */
public class Goods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//编号
	private int id;

	//名称
	//pattern和notNull来自目录下的ValidationMessage.properties文件
	@Pattern(regexp="^[^><&#]{1,50}$",message="{pattern}")
	@NotNull(message="{notNull}")
	private String name;
	
	//价格
	@Min(value=1,message="必须大于或等于1")
	private double price;
	
	//图片
	private String picture;

	/**------------Getter and Setter-------------**/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	/* TODO
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id："+getId()+"，name："+getName()+"，price："+getPrice()+"，picture："+getPicture();
	}
	
	
}
