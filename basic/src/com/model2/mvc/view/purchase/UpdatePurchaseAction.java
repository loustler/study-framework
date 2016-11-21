package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class UpdatePurchaseAction extends Action {

	public UpdatePurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PurchaseVO purchaseVO = new PurchaseVO();
		UserVO userVO = null;
		PurchaseService purchaseService = new PurchaseServiceImpl();
		UserService userService = new UserServiceImpl();
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		String buyerId = request.getParameter("buyerId");
		userVO = userService.getUser(buyerId);
		
		purchaseVO.setTranNo(tranNo);
		purchaseVO.setBuyer(userVO);
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setDivyDate(request.getParameter("divyDate"));
		
		
		System.out.println(this.getClass().getName()+" tranNo : " +tranNo);
		System.out.println(this.getClass().getName()+" purchaseVO : "+purchaseVO);
		
		purchaseService.updatePurcahse(purchaseVO);
		
		purchaseVO = purchaseService.getPurchase(tranNo);
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/getPurchase.jsp";
	}

}
