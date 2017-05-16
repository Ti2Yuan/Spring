/**
 * 
 */
package com.chen.ssmdemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chen.ssmdemo.entities.Goods;

/**
 * @author chenti
 *
 */
public interface GoodsDAO {

	/**
	 * 获得商品信息并分页
	 */
	public List<Goods> getGoodsPager(@Param("skip")int skip, @Param("size")int size);
	
	/**
	 * 通过编号获得单个商品
	 * */
	public Goods getGoodsById(int id);
	
	/**
	 * 获得商品总数
	 * */
	public int getGoodsCount();
	
	/**
	 * 新增加商品
	 * */
	public int insert(Goods good);
	
	/**
	 * 删除商品
	 * */
	public int delete(int id);
	
	/**
	 * 修改商品
	 * */
	public int update(Goods good);
}
