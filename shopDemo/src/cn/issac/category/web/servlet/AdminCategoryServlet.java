package cn.issac.category.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.issac.category.domain.Category;
import cn.issac.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminCategoryServlet extends BaseServlet {
		CategoryService categoryService = new CategoryService();
	
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/tag_list.jsp";
	}

	
	public String editPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = (String) request.getParameter("cid");
		request.setAttribute("category",categoryService.load(cid));
		return "f:/adminjsps/tag_mod.jsp";
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		categoryService.edit(category);
		return findAll(request,response);
	}
	
	
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		category.setCid(CommonUtils.uuid());
		categoryService.add(category);
		return findAll(request,response);
	}
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		String cid = request.getParameter("cid");
		categoryService.delete(cid);
		return findAll(request,response);
		}catch(CategoryException e){
			request.setAttribute("msg", e.getMessage());
			return findAll(request,response);
		}
		
	}
}
