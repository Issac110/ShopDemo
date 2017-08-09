package cn.issac.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.issac.address.domain.Address;
import cn.issac.address.service.AddressService;
import cn.issac.cart.domain.Cart;
import cn.issac.cart.domain.CartItem;
import cn.issac.order.domain.Order;
import cn.issac.order.domain.OrderItem;
import cn.issac.order.service.OrderException;
import cn.issac.order.service.OrderItemService;
import cn.issac.order.service.OrderService;
import cn.issac.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class OrderServlet extends BaseServlet {
	private OrderService orderService = new OrderService();
	private OrderItemService orderItemService = new OrderItemService();
	private AddressService addressService = new AddressService();
	
	
	
	/**
	 * 这个方法是易宝回调方法
	 * 我们必须要判断调用本方法的是不是易宝！
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String back(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 修改订单的状态
		 */
		return null;
	}
	
	/**
	 * 确认收货
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 获取oid参数
		 * 2. 调用service方法
		 *   > 如果有异常，保存异常信息，转发到msg.jsp
		 * 3. 保存成功信息，转发到msg.jsp
		 */
		String oid = request.getParameter("oid");
		try {
			orderService.confirm(oid);
			request.setAttribute("msg", "恭喜，交易成功！");
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
	}
	
	/**
	 * 加载订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 得到oid参数 2. 使用oid调用service方法得到Order 3.
		 * 保存到request域，转发到/jsps/order/desc.jsp
		 */
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		List<OrderItem> orderItemList = orderItemService.findByOrder(oid);
		order.setOrderItemList(orderItemList);
		request.setAttribute("order", order);

		return "f:/adminjsps/order_detail.jsp";
	}
	
	/**
	 * 查询我的订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myOrders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. 从session得到当前用户，再获取其uid 2.
		 * 使用uid调用orderService#myOrders(uid)得到该用户的所有订单List<Order> 3.
		 * 把订单列表保存到request域中，转发到/jsps/order/list.jsp
		 */
		User user = (User) request.getSession().getAttribute("session_user");
		List<Order> orderList = orderService.myOrders(user.getUid());
		request.setAttribute("orderList", orderList);
		return "f:/jsps/order/list.jsp";
	}
	
	/**
	 * 添加订单 把保存在session中的购物车用来生成Order对象
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadToTureOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. 从session中得到cart 2. 使用cart生成Order对象 3. 调用service方法完成添加订单 4.
		 * 保存order到request域中，转发到/jsps/order/desc.jsp
		 */
		// 从session中获取cart
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		
		/**
		 * 判断购物车是否有商品
		 */
		
		if(cart.getMap().isEmpty()){
			return "/jsps/cart.jsp";
		}
		// 把cart转换成Order对象
		/*
		 * 创建Order对象，并设置属性
		 * 
		 * Cart --> Order
		 */
		Order order = new Order();
		order.setOid(CommonUtils.uuid());// 设置编号
		order.setOrdertime(new Date());// 设置下单时间
		order.setOrderState(1);// 设置订单状态为1，表示未付款
		User user = (User) request.getSession().getAttribute("session_user");
		// 如果用户未登录，则跳转到登录页面
		if (user == null) {
			request.setAttribute("msg", "请先登录！！");
			return "/jsps/login.jsp";
		}
		order.setOwner(user);// 设置订单所有者
		order.setTotal(cart.getTotal());// 设置订单的合计，从cart中获取合计

		/*
		 * 创建订单条目集合
		 * 
		 * cartItemList --> orderItemList
		 */
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		// 循环遍历Cart中的所有CartItem，使用每一个CartItem对象创建OrderItem对象，并添加到集合中
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem oi = new OrderItem();// 创建订单条目

			oi.setIid(CommonUtils.uuid());// 设置条目的id
			oi.setCount(cartItem.getCount());// 设置条目的数量
			oi.setGood(cartItem.getGood());// 设置条目的商品
			oi.setSubtotal(cartItem.getSubtotal());// 设置条目的小计
			oi.setOrder(order);// 设置所属订单

			orderItemList.add(oi);// 把订单条目添加到集合中
		}

		// 把所有的订单条目添加到订单中
		order.setOrderItemList(orderItemList);

		// 清空购物车
		cart.clear();

		// ////////////////////////////////////////////

		/*
		 * 3. 调用orderService添加订单
		 */
		orderService.add(order);
		/*
		 * 4. 保存order到request域，转发到/jsps/order/desc.jsp
		 */
		request.setAttribute("addressList",
				addressService.findByUser(user.getUid()));
		request.setAttribute("order", order);
		return "/jsps/tureorder.jsp";
	}
	
	/**
	 * 添加订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("order");
		Order order = orderService.findByOid(oid);
		String aid = request.getParameter("adderss");
		
		
		/**
		 * 判断地址是否为空
		 */
		if(aid==null){
			request.setAttribute("order", order);
			request.setAttribute("addressList", addressService.findByUser(order.getOwner().getUid()));
			request.setAttribute("msg", "请选择地址!!");
			return "/jsps/tureorder.jsp";
		}
		Address address = addressService.load(aid);
		String comment = request.getParameter("comment");
		order.setAddress(address);
		order.setComment(comment);
		orderService.updateOrder(order);
		orderService.updateState(order.getOid(),2);
		return "/jsps/PaySuccess.jsp";
	}
	
	/**
	 * 查询所有订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("orderList", orderService.findAll());
		return "/adminjsps/order_list.jsp";	
	}
	
	/**
	 * 删除订单
	 * @param request
	 * @param response
	 * @return
	 */
	public String deleteOrder(HttpServletRequest request, HttpServletResponse response){
		String oid = request.getParameter("oid");
		orderService.deleteOrder(oid);
		return "/jsps/index.jsp";	
	}
	
	
}
