package cn.issac.category.service;

import java.util.List;

import cn.issac.category.dao.CategoryDao;
import cn.issac.category.domain.Category;
import cn.issac.category.web.servlet.CategoryException;
import cn.issac.good.dao.GoodDao;
import cn.issac.good.domain.Good;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	private GoodDao goodDao = new GoodDao();
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	public Category load(String cid) {
		return categoryDao.findByCid(cid);
	}

	public void edit(Category category) {
		categoryDao.edit(category);
	}

	public void add(Category category) {
		categoryDao.add(category);
		
	}

	public void delete(String cid) throws CategoryException {
		List<Good> goodList = goodDao.findByCategory(cid);
		if(goodList.size()>0){
			throw new CategoryException("此分类下还有商品，不能删除！！");
		}	
		
		categoryDao.delete(cid);

	} 
}
