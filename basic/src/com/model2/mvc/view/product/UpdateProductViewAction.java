package com.model2.mvc.view.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class UpdateProductViewAction extends Action {

	public UpdateProductViewAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int prodNo = 0;
		if(request.getParameter("prodNo") != null) {
			prodNo = Integer.parseInt(request.getParameter("prodNo"));
		} else {
			System.out.println("request.getParameter(\"prodNo\") Error");
		}
		ProductDAO productDAO = new ProductDAO();	
		ProductVO productVO = productDAO.findProduct(prodNo);
		
		System.out.println("updateViewAction : "+productVO);
		
		
		request.setAttribute("productVO", productVO);
		
		return "forward:/product/updateProductView.jsp";
	}

}
