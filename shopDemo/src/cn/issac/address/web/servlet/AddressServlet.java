package cn.issac.address.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.issac.address.domain.Address;
import cn.issac.address.service.AddressService;
import cn.issac.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AddressServlet extends BaseServlet {
		private AddressService addressService = new AddressService();
		
		
		public String findByUser(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			User user = (User) request.getSession().getAttribute("session_user");
			/**
			 * 判断用户是否登录或session是否已过时，如果是，则跳转到登录页面
			 */
			if(user==null){
				request.setAttribute("msg", "请先登录！！");
				return "/jsps/login.jsp";
			}
			request.setAttribute("addressList", addressService.findByUser(user.getUid()));
			return "/jsps/address.jsp";
		}
		
		
		/**
		 * 添加地址
		 * @param request
		 * @param response
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		public String add(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String aid = request.getParameter("aid");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String province = request.getParameter("s_province");
			String city = request.getParameter("s_city");
			String county = request.getParameter("s_county");
			String detailAddress = request.getParameter("detailAddress");
			Address address = CommonUtils.toBean(request.getParameterMap(), Address.class);
			String addressName = city+county+" "+detailAddress;
			address.setAddressName(addressName);
			/**
			 * 判断信息是否完整
			 */
			if(name.isEmpty()||phone.isEmpty()||province.equals("省份")||
					city.equals("地级市")||county.equals("市、县级市")||detailAddress.isEmpty()){
				request.setAttribute("address", address);
				request.setAttribute("msg", "信息填写不完整！！");
				return "/jsps/addAddress.jsp";
			}
			/**
			 * 根据aid是否已存在判断是添加地址还是编辑地址
			 */
			if(aid.isEmpty()){
				address.setAid(CommonUtils.uuid());
				User user = (User) request.getSession().getAttribute("session_user");
				address.setUser(user);
				addressService.add(address);
			}else{
				update(request, response,address);
			}
			return findByUser(request, response);
		}
		/**
		 * 编辑地址
		 * @param request
		 * @param response
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		public String update(HttpServletRequest request, HttpServletResponse response,Address address)
				throws ServletException, IOException {
			User user = (User) request.getSession().getAttribute("session_user");
			address.setUser(user);
			addressService.update(address);
			return findByUser(request, response);
		}
		
		public String load(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String aid = request.getParameter("aid");
			Address address = addressService.load(aid);
			request.setAttribute("address", address);
			return "/jsps/addAddress.jsp";
		}
		
		public String delete(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String aid = request.getParameter("aid");
			addressService.delete(aid);
			return findByUser(request, response);
		}
}
