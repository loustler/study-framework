package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action {

	public AddPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		String buyerId = request.getParameter("buyerId");
		String paymentOption = request.getParameter("paymentOption");
		String receiverName = request.getParameter("receiverName");
		String receiverPhone = request.getParameter("receiverPhone");
		String receiverAddr = request.getParameter("receiverAddr");
		String receiverDate = request.getParameter("receiverDate");
		String receiverRequest = request.getParameter("receiverRequest");
		
		UserVO userVO = null;
		ProductVO productVO = null;
		PurchaseVO purchaseVO = new PurchaseVO();
		
		UserService userService = new UserServiceImpl();
		ProductService productService = new ProductServiceImpl();
		PurchaseService service = new PurchaseServiceImpl();
		
		userVO = userService.getUser(buyerId);
		System.out.println("AddPurchaseAction UserVO : " +userVO);
		productVO = productService.getProduct(prodNo);
		System.out.println("AddPurchaseAction productVO : " +productVO);
		
		purchaseVO.setBuyer(userVO);
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setReceiverName(receiverName);
		purchaseVO.setReceiverPhone(receiverPhone);
		purchaseVO.setDivyAddr(receiverAddr);
		purchaseVO.setDivyDate(receiverDate);
		purchaseVO.setDivyRequest(receiverRequest);
		purchaseVO.setPaymentOption(paymentOption);
		
		service.addPurchase(purchaseVO);
		
		System.out.println("AddPurchaseAction request전 체크 purchaseVO : " +purchaseVO);
		System.out.println("AddPurchaseAction request전 체크 userVO : " +userVO);
		System.out.println("AddPurchaseAction request전 체크 productVO : " +productVO);
		
		request.setAttribute("purchaseVO", purchaseVO);
		request.setAttribute("userVO", userVO);
		request.setAttribute("productVO", productVO);
		
		
		
		return "forward:/purchase/addPurchase.jsp";
	}

}
