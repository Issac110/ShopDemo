package cn.issac.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.issac.address.domain.Address;
import cn.issac.category.domain.Category;
import cn.issac.good.domain.Good;
import cn.issac.order.domain.Order;
import cn.issac.order.domain.OrderItem;
import cn.issac.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();
	
	
	/**
	 * 查询所有订单
	 * @return
	 */
	public List<Order> findAll() {
		try {
			String sql = "select * from orders o,tb_user u,address a where o.uid = u.uid and o.aid = a.aid";
			List<Map<String, Object>> mapList = qr.query(sql,
					new MapListHandler());
			List<Order> orderList = new ArrayList<Order>();
			for (Map<String, Object> map : mapList) {
				Address address = CommonUtils.toBean(map, Address.class);
				User user = CommonUtils.toBean(map, User.class);
				Order order = CommonUtils.toBean(map, Order.class);

				address.setUser(user);
				order.setOwner(user);
				order.setAddress(address);
				orderList.add(order);
			}
			return orderList;
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	
	
	
	/**
	 * 添加订单
	 * 
	 * @param order
	 */
	public void addOrder(Order order) {
		try {
			String sql = "insert into orders values(?,?,?,?,?,?,?,?,?)";
			/*
			 * 处理util的Date转换成sql的Timestamp
			 */
			Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
			Object[] params = { order.getOid(), timestamp, order.getTotal(),
					order.getOrderState(), order.getOwner().getUid(), null,
					null, null, null };
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateOrder(Order order){
		
		try {
			String sql = "update orders set aid = ? , comment = ? where oid = ?";
			Object [] params = {order.getAddress().getAid(),order.getComment(),order.getOid()};
			qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 插入订单条目
	 * @param orderItemList
	 */
	public void addOrderItemList(List<OrderItem> orderItemList) {
		/**
		 * QueryRunner类的batch(String sql, Object[][] params)
		 * 其中params是多个一维数组！
		 * 每个一维数组都与sql在一起执行一次，多个一维数组就执行多次
		 */
		try {
			String sql = "insert into orderitem values(?,?,?,?,?)";
			/*
			 * 把orderItemList转换成两维数组
			 *   把一个OrderItem对象转换成一个一维数组
			 */
			Object[][] params = new Object[orderItemList.size()][];
			// 循环遍历orderItemList，使用每个orderItem对象为params中每个一维数组赋值
			for(int i = 0; i < orderItemList.size(); i++) {
				OrderItem item = orderItemList.get(i);	
				params[i] = new Object[]{item.getIid(), item.getCount(), 
						item.getSubtotal(), item.getOrder().getOid(),
						item.getGood().getGid()}; 
			}
			qr.batch(sql, params);//执行批处理
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按uid查询订单
	 * @param uid
	 * @return
	 */
	public List<Order> findByUid(String uid) {
		/*
		 * 1. 通过uid查询出当前用户的所有List<Order>
		 * 2. 循环遍历每个Order，为其加载他的所有OrderItem
		 */
		try {
			/*
			 * 1. 得到当前用户的所有订单
			 */
			String sql = "select * from orders where uid=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
			
			/*
			 * 2. 循环遍历每个Order，为其加载它自己所有的订单条目
			 */
			for(Order order : orderList) {
				loadOrderItems(order);//为order对象添加它的所有订单条目
			}
			
			/*
			 * 3. 返回订单列表
			 */
			return orderList;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 加载指定的订单所有的订单条目
	 * @param order
	 * @throws SQLException 
	 */
	private void loadOrderItems(Order order) throws SQLException {
		/*
		 * 查询两张表：orderitem、good
		 */
		String sql = "select * from orderitem i, good g,category c where i.gid=g.gid and g.cid=c.cid and oid=?";
		/*
		 * 因为一行结果集对应的不再是一个javabean，所以不能再使用BeanListHandler，而是MapListHandler
		 */
		
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
		/*
		 * mapList是多个map，每个map对应一行结果集
		 * 一行：
		 * {iid=C7AD5492F27D492189105FB50E55CBB6, count=2, subtotal=60.0, oid=1AE8A70354C947F8B81B80ADA6783155, gid=7, bname=精通Hibernate,price=30.0, author=张卫琴, image=good_img/8991366-1_l.jpg, cid=2}
		 * ...
		 * 
		 * 我们需要使用一个Map生成两个对象：OrderItem、Good，然后再建立两者的关系（把Good设置给OrderItem）
		 */
		/*
		 * 循环遍历每个Map，使用map生成两个对象，然后建立关系（最终结果一个OrderItem），把OrderItem保存起来
		 */
		List<OrderItem> orderItemList = toOrderItemList(mapList);
		order.setOrderItemList(orderItemList);
	}

	/**
	 * 把mapList中每个Map转换成两个对象，并建立关系
	 * @param mapList
	 * @return
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(Map<String,Object> map : mapList) {
			OrderItem item = toOrderItem(map);
			orderItemList.add(item);
		}
		return orderItemList;
	}

	/**
	 * 把一个Map转换成一个OrderItem对象
	 * 
	 * @param map
	 * @return
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Good good = CommonUtils.toBean(map, Good.class);
		Category category = CommonUtils.toBean(map, Category.class);
		good.setCategory(category);
		orderItem.setGood(good);
		return orderItem;
	}

	/**
	 * 加载订单
	 * 
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		try {

			String sql = "select * from orders o ,address a where o.aid = a.aid and oid=?";
			Map<String, Object> map = qr.query(sql, new MapHandler(), oid);

			Order order = CommonUtils.toBean(map, Order.class);
			Address address = CommonUtils.toBean(map, Address.class);
			order.setAddress(address);

			return order;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Order findByOid(String oid){
		try {
			String sql = "select * from orders o ,tb_user u where o.uid = u.uid and oid = ?";
			Map<String,Object>  map = qr.query(sql, new MapHandler(),oid);
			Order order = CommonUtils.toBean(map, Order.class);
			User user = CommonUtils.toBean(map, User.class);
			order.setOwner(user);
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
	}
	
	
	





	/**
	 * 通过oid查询订单状态
	 * 
	 * @param oid
	 * @return
	 */
	public int getStateByOid(String oid) {
		try {
			String sql = "select state from orders where oid=?";
			return (Integer) qr.query(sql, new ScalarHandler(), oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 修改订单状态
	 * 
	 * @param oid
	 * @param orderState
	 * @return
	 */
	public void updateState(String oid, int orderState) {
		try {
			String sql = "update orders set orderState=? where oid=?";
			qr.update(sql, orderState, oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}




	public void deleteOrder(String oid) {
		try {
			String sql = "delete from orders where oid = ?";
			qr.update(sql,oid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
