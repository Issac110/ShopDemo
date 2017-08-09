package cn.issac.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.issac.cart.domain.Cart;
import cn.issac.cart.domain.CartItem;
import cn.issac.good.domain.Good;
import cn.issac.good.service.GoodService;
import cn.itcast.servlet.BaseServlet;

public class CartServlet extends BaseServlet {
	/**
	 * 添加购物条目
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 得到车 2. 得到条目（得到商品和数量） 3. 把条目添加到车中
		 */
		/*
		 * 1. 得到车
		 */
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		/*
		 * 表单传递的只有gid和数量 2. 得到条目 > 得到商品和数量 > 先得到商品的gid，然后我们需要通过gid查询数据库得到Good >
		 * 数量表单中有
		 */
		String gid = request.getParameter("gid");
		Good good = new GoodService().load(gid);
		int count = Integer.parseInt(request.getParameter("count"));
		CartItem cartItem = new CartItem();
		cartItem.setGood(good);
		cartItem.setCount(count);

		/*
		 * 3. 把条目添加到车中
		 */
		cart.add(cartItem);
		
		return "f:/jsps/cart.jsp";
	}

	/**
	 * 清空购物条目
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 1. 得到车 2. 设置车的clear
		 */
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clear();
		return "f:/jsps/cart.jsp";
	}

	/**
	 * 删除购物条目
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. 得到车 2. 得到要删除的gid
		 */
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		String gid = request.getParameter("gid");
		cart.delete(gid);
		return "f:/jsps/cart/list.jsp";
	}
}
