package cn.issac.good.web.servlet;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.issac.category.domain.Category;
import cn.issac.category.service.CategoryService;
import cn.issac.good.domain.Good;
import cn.issac.good.service.GoodService;
import cn.itcast.commons.CommonUtils;

public class AdminAddGoodServlet extends HttpServlet {
	private GoodService goodService = new GoodService();
	private CategoryService categoryService = new CategoryService();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory(900*1024,new File("F:/f/temp"));
		
		ServletFileUpload sfu = new ServletFileUpload(factory);
		
		sfu.setFileSizeMax(900*1024);
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);
			Map<String,String> map  = new HashMap<String, String>();
			for (FileItem fileItem : fileItemList) {
				if(fileItem.isFormField()){
					map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
				}	
			}
			Good good  = CommonUtils.toBean(map, Good.class);
			good.setGid(CommonUtils.uuid());
			
			Category category = CommonUtils.toBean(map, Category.class);
			good.setCategory(category);
			
			String savepath = this.getServletContext().getRealPath("/good_img");
			
			String filename = CommonUtils.uuid()+"_"+fileItemList.get(4).getName();
			
			
//			if(!filename.toLowerCase().endsWith("jpg")){
//				request.setAttribute("msg", "您上传的图片不是jpg格式！！");
//				request.setAttribute("categoryList", categoryService.findAll());
//				request.getRequestDispatcher("/adminjsps/product_add.jsp")
//						.forward(request, response);
//				return;
//			}
			File destFile = new File (savepath,filename);
			
			fileItemList.get(4).write(destFile);
			
			good.setImage("good_img/"+filename);
			
			goodService.add(good);
			
			Image image = new ImageIcon(destFile.getAbsolutePath()).getImage();
			if(image.getWidth(null)>700||image.getHeight(null)>700){
				destFile.delete();
				request.setAttribute("msg", "您上传的图片尺寸超出了700*700！");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/product_add.jsp")
				.forward(request, response);
			}
			
			
			request.getRequestDispatcher("/AdminGoodServlet?method=findAll").forward(request, response);
		} catch (Exception e) {
			if(e instanceof FileUploadBase.FileSizeLimitExceededException){
				request.setAttribute("msg", "您上传的文件大小超出了900KB");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/product_add.jsp")
				.forward(request, response);
			}
		}
		
	}

}
