package cn.issac.order.service;

import java.util.List;

import cn.issac.order.dao.OrderitemDao;
import cn.issac.order.domain.OrderItem;

public class OrderItemService {
	OrderitemDao orderItemDao = new OrderitemDao();

	public List<OrderItem> findByOrder(String oid) {
		// TODO Auto-generated method stub
		return orderItemDao.findByOrder(oid);
	}
	
}
