package cn.issac.category.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class MemberFilter
 */
@WebFilter("/jsps/member.jsp")
public class MemberFilter implements Filter {


	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if(httpServletRequest.getSession().getAttribute("session_user")==null){
			httpServletRequest.setAttribute("msg", "请先登录！！");
			httpServletRequest.getRequestDispatcher("/jsps/login.jsp").forward(request,response);
			return;
		}
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
