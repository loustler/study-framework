package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.vo.UserVO;

import sun.print.resources.serviceui;

public class AddPurchaseViewAction extends Action {

	public AddPurchaseViewAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		UserVO userVO = (UserVO)session.getAttribute("user");
		ProductVO productVO = null;
		
		int productNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductDAO productDAO = new ProductDAO();
		
		productVO = productDAO.findProduct(productNo);
		
		System.out.println("AddPurchase productVO : " +productVO);
		System.out.println("AddPurchase userVO : "+userVO);
		
		request.setAttribute("userVO", userVO);
		request.setAttribute("product", productVO);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}

}
