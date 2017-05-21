/**
 * 
 */
package com.chen.ssmdemo.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chen.ssmdemo.entities.Goods;
import com.chen.ssmdemo.mapper.GoodsDAO;

/**
 * @author chenti
 * 商品业务实现
 * 
 * 自动添加到Spring容器中
 */
@Service
public class GoodsServiceImpl implements GoodsService {

	//自动装配
	@Resource
	GoodsDAO goodsdao;
	
	/* TODO 分页
	 * @see com.chen.ssmdemo.services.GoodsService#getGoodsPager(int, int)
	 * @param pageNO
	 * @param size
	 * @return
	 */
	@Override
	public List<Goods> getGoodsPager(int pageNO, int size) {
		int skip = (pageNO-1) * size;
		return goodsdao.getGoodsPager(skip, size);
	}

	/* TODO 获得单个产品对象
	 * @see com.chen.ssmdemo.services.GoodsService#getGoodsById(int)
	 * @param id
	 * @return
	 */
	@Override
	public Goods getGoodsById(int id) {
		return goodsdao.getGoodsById(id);
	}

	/* TODO 添加
	 * @see com.chen.ssmdemo.services.GoodsService#insert(com.chen.ssmdemo.entities.Goods)
	 * @param goods
	 * @return
	 */
	@Override
	public int insert(Goods goods) {
		return goodsdao.insert(goods);
	}

	/* TODO 删除单个
	 * @see com.chen.ssmdemo.services.GoodsService#delete(int)
	 * @param id
	 * @return
	 */
	@Override
	public int delete(int id) {
		return goodsdao.delete(id);
	}

	/* TODO 删除多个
	 * @see com.chen.ssmdemo.services.GoodsService#deletes(int[])
	 * @param ids
	 * @return
	 */
	@Override
	public int deletes(int[] ids) {
		int rows = 0;
		for (int i : ids) {
			rows += delete(i);
		}
		return rows;
	}

	/* TODO 更新
	 * @see com.chen.ssmdemo.services.GoodsService#update(com.chen.ssmdemo.entities.Goods)
	 * @param goods
	 * @return
	 */
	@Override
	public int update(Goods goods) {
		return goodsdao.update(goods);
	}

	/* TODO 获得商品总数
	 * @see com.chen.ssmdemo.services.GoodsService#getGoodsCount()
	 * @return
	 */
	@Override
	public int getGoodsCount() {
		return goodsdao.getGoodsCount();
	}

}
