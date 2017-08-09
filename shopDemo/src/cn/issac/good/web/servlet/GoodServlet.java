package cn.issac.good.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.issac.good.service.GoodService;
import cn.itcast.servlet.BaseServlet;

public class GoodServlet extends BaseServlet {
	private GoodService GoodService = new GoodService();
	
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 得到参数gid
		 * 2. 查询得到Good
		 * 3. 保存，转发到desc.jsp
		 */
		request.setAttribute("good", GoodService.load(request.getParameter("gid")));
		return "f:/jsps/addGood.jsp";
	}
	
	/**
	 * 查询所有商品
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("GoodList", GoodService.findAll());
		return "f:/jsps/Good/list.jsp";
	}
	
	/**
	 * 按分类查询
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		request.setAttribute("GoodList", GoodService.findByCategory(cid));
		return "f:/jsps/Good/list.jsp";
	}
	
	public String findByGoodId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String goodId = request.getParameter("goodId");
		request.setAttribute("good", GoodService.findByGoodId(goodId));
		return "f:/jsps/detail.jsp";
	}
}
