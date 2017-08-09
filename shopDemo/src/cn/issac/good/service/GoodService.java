package cn.issac.good.service;

import java.util.List;

import cn.issac.good.dao.GoodDao;
import cn.issac.good.domain.Good;

public class GoodService {
	private GoodDao goodDao = new GoodDao();
	
	/**
	 * 查询所有商品
	 * @return
	 */
	public List<Good> findAll() {
		return goodDao.findAll();
	}

	/**
	 * 按分类查询商品
	 * @param cid
	 * @return
	 */
	public List<Good> findByCategory(String cid) {
		return goodDao.findByCategory(cid);
	}

	public Good findByGoodId(String goodId) {
		// TODO Auto-generated method stub
		return goodDao.findByGoodId(goodId);
	}

	public Good load(String gid) {
		return goodDao.findByGoodId(gid);
	}

	public void add(Good good) {
		goodDao.add(good);
		
	}

	public List<Good> findAllByDelete() {
		return goodDao.findAllByDelte();
	}

	public void delete(String gid) {
		goodDao.delete(gid);
		
	}

	public Good editPre(String gid) {
		
		return goodDao.findByGoodId(gid);
	}

	public void edit(Good good) {
		goodDao.edit(good);
		
	}
}
