/**
 * 
 */
package com.chen.ssmdemo.test;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author chenti
 *
 */
public class MybatisUtil {

	private static SqlSessionFactory sqlSessionFactory;
	public static SqlSessionFactory getSqlSessionFactory(){
		if(sqlSessionFactory == null){
			InputStream inputStream = MybatisUtil.class.getClassLoader().getResourceAsStream("mybatisConf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}
		return sqlSessionFactory;
	}
	
	public static SqlSession getSession(){
		return getSqlSessionFactory().openSession(true);
	}
	
	/**
	 * 获得sql会话
	 * TODO
	 * @param isAutoCommit 是否自动提交，false 则需要sqlSession.commit();rollback()
	 * @return
	 * SqlSession
	 */
	public static SqlSession getSession(boolean isAutoCommit){
		return getSqlSessionFactory().openSession(isAutoCommit);
	}
}
