package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class AddProductAction extends Action {

	public AddProductAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String prodName = request.getParameter("prodName");
		String prodDetail = request.getParameter("prodDetail");
		String manuDateTemp = request.getParameter("manuDate");
		int price = Integer.parseInt(request.getParameter("price"));
		String fileName = request.getParameter("fileName");
		
		String[] temp = manuDateTemp.split("-");
		String manuDate = temp[0]+temp[1]+temp[2];
		
		ProductVO productVO = new ProductVO();
		productVO.setProdName(prodName);
		productVO.setProdDetail(prodDetail);
		productVO.setManuDate(manuDate);
		productVO.setPrice(price);
		productVO.setFileName(fileName);
		
		ProductService service = new ProductServiceImpl();
		service.addProduct(productVO);
		
		request.setAttribute("productVO", productVO);
		
		return "forward:/product/productView.jsp";
	}

}
