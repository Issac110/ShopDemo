package cn.issac.good.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import cn.issac.category.domain.Category;
import cn.issac.good.domain.Good;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class GoodDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 查询所有商品
	 * @return
	 */
	@Test
	public List<Good> findAll() {
		try {
			String sql = "select * from good g, category c where g.cid=c.cid and del = false";
			
			List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
			return toGoodList(mapList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * 把mapList中每个Map转换成两个对象，并建立关系
	 * @param mapList
	 * @return
	 */
	private List<Good> toGoodList(List<Map<String, Object>> mapList) {
		List<Good> GoodList = new ArrayList<Good>();
		for(Map<String,Object> map : mapList) {
			Good good = toGood(map);
			GoodList.add(good);
		}
		return GoodList;
	}

	
	
	/**
	 * 把一个Map转换成一个Good对象
	 * @param map
	 * @return
	 */
	private Good toGood(Map<String, Object> map) {
		Good good = CommonUtils.toBean(map, Good.class);
		Category category = CommonUtils.toBean(map, Category.class);
		good.setCategory(category);
		return good;
	}

	/**
	 * 按分类查询
	 * @param cid
	 * @return
	 */
	public List<Good> findByCategory(String cid) {
		try {
			String sql = "select * from good where cid=?";
			return qr.query(sql, new BeanListHandler<Good>(Good.class), cid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 加载方法
	 * @param gid
	 * @return
	 */
	public Good findByGoodId(String gid) {
		
		try {
			String sql = "select * from good g, category c where g.cid=c.cid and gid =?";
			
			List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),gid);
			return toGoodList(mapList).get(0);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}


	public void add(Good good) {
		try {
			String sql = "insert into good values(?,?,?,?,?,?,?)";
			Object[] params = {good.getGid(),good.getGname(),good.getPrice(),
					good.getDesc(),good.getImage(),good.getCategory().getCid(),false};
			qr.update(sql, params);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}


	public List<Good> findAllByDelte() {
		try {
			String sql = "select * from good g, category c where g.cid=c.cid and del = true";
			
			List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
			return toGoodList(mapList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void delete(String gid) {
		try {
			String sql = "update good set del = true where gid = ?";
			qr.update(sql,gid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

/**
 * 编辑商品
 * @param good
 */
	public void edit(Good good) {
		try {
			String sql1 = "update book set bname=?, price=?,author=?, image=?, cid=? where bid=?";
			String sql  = "update good set gname=?, price=?,desc=?, cid=?, image=? where gid=?";
			Object [] params = {good.getGname(),good.getPrice(),good.getDesc(),
					good.getCategory().getCid(),good.getImage(),good.getGid()};
			qr.update(sql,params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
