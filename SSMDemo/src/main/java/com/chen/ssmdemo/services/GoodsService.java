/**
 * 
 */
package com.chen.ssmdemo.services;

import java.util.List;

import com.chen.ssmdemo.entities.Goods;

/**
 * @author chenti
 * 商品服务接口
 */
public interface GoodsService {

	//分页
	List<Goods> getGoodsPager(int pageNO, int size);
	
	//获得单个商品对象
	Goods getGoodsById(int id);
	
	//获得商品总数
	int getGoodsCount();
	
	//添加
	int insert(Goods goods);
	
	//删除单个
	int delete(int id);
	
	//删除多个
	int deletes(int[] ids);
	
	//更新
	int update(Goods goods);
}
