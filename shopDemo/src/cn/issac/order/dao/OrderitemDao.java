package cn.issac.order.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.issac.category.domain.Category;
import cn.issac.good.domain.Good;
import cn.issac.order.domain.OrderItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderitemDao {
	QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 查询所有订单条目
	 * @return
	 */
	public List<OrderItem> findAll(){
		try {
			String sql = "select * from orderitem";
			return qr.query(sql, new BeanListHandler<OrderItem>(OrderItem.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<OrderItem> findByOrder(String oid){
		try {
			String sql = "select * from orderitem o ,good g ,category c where o.gid = g.gid and g.cid = c.cid and oid = ?";
			List<Map<String,Object>> mapList =  qr.query(sql, new MapListHandler(),oid);
			List<OrderItem> orderItemList = new ArrayList<OrderItem>();
			for (Map<String, Object> map : mapList) {
				OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
				Good good = CommonUtils.toBean(map, Good.class);
				Category category = CommonUtils.toBean(map, Category.class);
				good.setCategory(category);
				orderItem.setGood(good);
				orderItemList.add(orderItem);
			}
			return orderItemList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
