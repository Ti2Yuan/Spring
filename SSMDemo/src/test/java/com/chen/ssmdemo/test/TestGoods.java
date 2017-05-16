/**
 * 
 */
package com.chen.ssmdemo.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import com.chen.ssmdemo.entities.Goods;
import com.chen.ssmdemo.mapper.GoodsDAO;

/**
 * @author chenti
 *
 */
public class TestGoods {

	@Test
	public void getGoodsPagerTest(){
		int skip = 4;
		int size = 2;
		SqlSession sqlSession = MybatisUtil.getSession();
		try {
			GoodsDAO goodsDAO = sqlSession.getMapper(GoodsDAO.class);
			List<Goods> goodsList = goodsDAO.getGoodsPager(skip, size);
			Assert.assertEquals(2, goodsList.size());
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
    public void insertTest() {
        SqlSession session=MybatisUtil.getSession();
        try {
            Goods entity=new Goods();
            entity.setName("正宗无锡阳山水蜜桃新鲜水果水密桃12个6斤装江浙沪皖顺丰包邮");
            entity.setPrice(108);
            entity.setPicture("nopic.jpg");
            GoodsDAO bookdao=session.getMapper(GoodsDAO.class);
            Assert.assertEquals(1, bookdao.insert(entity));
        } finally {
            session.close();
        }
    }

    @Test
    public void deleteTest() {
        SqlSession session=MybatisUtil.getSession();
        try {
            GoodsDAO bookdao=session.getMapper(GoodsDAO.class);
            Assert.assertEquals(1, bookdao.delete(12));
        } finally {
            session.close();
        }
    }

    @Test
    public void update() {
        SqlSession session=MybatisUtil.getSession();
        try {
            GoodsDAO bookdao=session.getMapper(GoodsDAO.class);
            Goods entity=bookdao.getGoodsById(12);
            entity.setName("正宗无锡阳山水蜜桃新鲜水果水密桃12个6斤装");
            entity.setPrice(107);
            entity.setPicture("nopicture.jpg");

            Assert.assertEquals(1, bookdao.update(entity));
        } finally {
            session.close();
        }
    }
}
