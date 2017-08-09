package cn.issac.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.issac.address.dao.AddressDao;
import cn.issac.order.dao.OrderDao;
import cn.issac.order.dao.OrderitemDao;
import cn.issac.order.domain.Order;
import cn.issac.user.dao.UserDao;
import cn.issac.user.domain.User;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	private OrderitemDao orderitemDao = new OrderitemDao();
	private UserDao userDao = new UserDao();
	
	public List<Order> findAll(){
		return orderDao.findAll();	
	}
	
	
	/**
	 * 添加订单
	 * 需要处理事务
	 * @param order
	 */
	public void add(Order order) {
		try {
			// 开启事务
			JdbcUtils.beginTransaction();
			
			orderDao.addOrder(order);//插入订单
			orderDao.addOrderItemList(order.getOrderItemList());//插入订单中的所有条目
			
			// 提交事务
			JdbcUtils.commitTransaction();
		} catch(Exception e) {
			// 回滚事务
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw new RuntimeException(e);
		}
	}

	/**
	 * 我的订单
	 * @param uid
	 * @return
	 */
	public List<Order> myOrders(String uid) {
		return orderDao.findByUid(uid);
	}

	/**
	 * 加载订单
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		return orderDao.load(oid);
	}

	
	/**
	 * 确认收货
	 * @param oid
	 * @throws OrderException
	 */
	public void confirm(String oid) throws OrderException {
		/*
		 * 1. 校验订单状态，如果不是3，抛出异常
		 */
		int state = orderDao.getStateByOid(oid);//获取订单状态
		if(state != 3) throw new OrderException("订单确认失败，您不是什么好东西！");
		
		/*
		 * 2. 修改订单状态为4，表示交易成功
		 */
		orderDao.updateState(oid, 4);
	}

	public void updateOrder(Order order) {
		orderDao.updateOrder(order);
		
	}


	public Order findByOid(String oid) {
		
		Order order =  orderDao.findByOid(oid);
		order.setOrderItemList(orderitemDao.findByOrder(oid));
		return order;
	}

	/**
	 * 更新订单状态
	 * 
	 * @param oid
	 * @param orderState
	 */
	public void updateState(String oid, int orderState) {
		orderDao.updateState(oid, orderState);

	}

	/**
	 * 删除订单
	 * 
	 * @param oid
	 */
	public void deleteOrder(String oid) {
		orderDao.deleteOrder(oid);

	}
}
