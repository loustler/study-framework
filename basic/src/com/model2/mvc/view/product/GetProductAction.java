package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;

public class GetProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int prodNum = 0;
		if(request.getParameter("prodNo") != null) {
			prodNum = Integer.parseInt(request.getParameter("prodNo"));
		} else {
			System.out.println("equest.getParameter(\"prodNo\") Error");
		}
		System.out.println("prodNum : " +prodNum);
				
		ProductDAO productDAO = new ProductDAO();
		ProductVO productVO = productDAO.findProduct(prodNum);
		
		request.setAttribute("productVO", productVO);
		
		return "forward:/product/getProduct.jsp";
	}

}
