package cn.issac.good.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.issac.category.domain.Category;
import cn.issac.category.service.CategoryService;
import cn.issac.good.domain.Good;
import cn.issac.good.service.GoodService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminGoodServlet extends BaseServlet {
	GoodService goodService = new GoodService();
	CategoryService categoryService = new CategoryService();
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("goodList", goodService.findAll());
		return "f:/adminjsps/product_list.jsp";
		
	}
	
	public String addPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("categoryList",categoryService.findAll());
		return "f:/adminjsps/product_add.jsp";
		
	}
	
	public String findAllByDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("goodList",goodService.findAllByDelete());
		return "f:/adminjsps/recycle_bin.jsp";
		
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String gid = request.getParameter("gid");
		goodService.delete(gid);
		return findAll(request,response);	
	}
	
	public String editPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String gid = request.getParameter("gid");
		request.setAttribute("good", goodService.editPre(gid));
		request.setAttribute("categoryList", categoryService.findAll());
		return 	"f:/adminjsps/product_mod.jsp";
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Good good = CommonUtils.toBean(request.getParameterMap(), Good.class);
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		good.setCategory(category);
		System.out.println(good);
		goodService.edit(good);
		return 	findAll(request,response);
	}
	
}
