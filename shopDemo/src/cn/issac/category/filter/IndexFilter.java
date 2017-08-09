package cn.issac.category.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import cn.issac.cart.domain.Cart;
import cn.issac.category.service.CategoryService;
import cn.issac.good.service.GoodService;

/**
 * Servlet Filter implementation class IndexFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD,
		DispatcherType.INCLUDE }, urlPatterns = { "/jsps/index.jsp" })
public class IndexFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		GoodService goodService = new GoodService();
		CategoryService categoryService = new CategoryService();
		request.setAttribute("GoodList", goodService.findAll());
		request.setAttribute("categoryList", categoryService.findAll());
		// 首次打开首页时，分发购物车
		if (httpServletRequest.getSession().getAttribute("cart") == null) {
			httpServletRequest.getSession().setAttribute("cart", new Cart());
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
