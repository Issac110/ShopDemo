package test;

import java.text.MessageFormat;
import java.util.List;

import org.junit.Test;

import cn.issac.address.dao.AddressDao;
import cn.issac.address.domain.Address;
import cn.issac.good.dao.GoodDao;
import cn.issac.good.domain.Good;
import cn.issac.good.service.GoodService;
import cn.issac.order.dao.OrderDao;
import cn.issac.order.dao.OrderitemDao;
import cn.issac.order.domain.OrderItem;
import cn.issac.order.service.OrderService;

public class Demo1 {
	@Test
	public void fun1() {
		/*
		 * 包含了点位符的字符串就是模板！
		 * 点位符：{0}、{1}、{2}
		 * 可变参数，需要指定模板中的点位符的值！有几个点位符就要提供几个参数
		 */
		String s = MessageFormat.format("{0}或{1}错误！", "用户名", "密码");
		System.out.println(s);
	}
	
	@Test
	public void fun2() {
		GoodService goodService = new GoodService();
		List<Good> list = goodService.findAll();
		for (Good good : list) {
			System.out.println(good);
			
		}
	}
	
	@Test
	public void fun3(){
		GoodDao goodDao  = new GoodDao();
		System.out.println(goodDao.findByGoodId("2"));
	}
	
	@Test
	public void fun4(){
		AddressDao addressDao = new AddressDao();
		List<Address> addressList = addressDao.findByUser("1");
		System.out.println(addressList);
	}
	
	@Test
	public void fun5(){
		AddressDao addressDao = new AddressDao();
		System.out.println(addressDao.findByAid("2"));
		
		}
	
	@Test
	public void fun6(){
		OrderDao orderDao = new OrderDao();
		System.out.println(orderDao.findAll());
	}
	@Test
	public void fun7(){
		OrderitemDao orderItemDao = new OrderitemDao();
		List<OrderItem> OrderItemList = orderItemDao.findByOrder("0CA3C5AD0F474354A8A95B2CBFE9220E");
		for (OrderItem orderItem : OrderItemList) {
			System.out.println(orderItem);
		}
	}
	@Test 
	public void fun8(){
		OrderService orderService = new OrderService();
		System.out.println(orderService.load("0CA3C5AD0F474354A8A95B2CBFE9220E"));
	}
	
	@Test 
	public void fun9(){
		OrderDao orderDao = new OrderDao();
		System.out.println(orderDao.load("0CA3C5AD0F474354A8A95B2CBFE9220E"));
	}
	@Test 
	public void fun10(){
		OrderService orderService = new OrderService();
		System.out.println(orderService.findByOid("69A101AAB3E74A8AAC27294C73572B61"));
	}
	
}
