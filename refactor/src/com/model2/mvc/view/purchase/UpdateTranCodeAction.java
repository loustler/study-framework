package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeAction extends Action {

	public UpdateTranCodeAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PurchaseService service = new PurchaseServiceImpl();
		
		String tranCode = request.getParameter("tranCode");
		String forwardURL = "forward:/";
		
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		System.out.println("currentPage : "+request.getParameter("currentPage"));
		
		Purchase purchase = null;
		if (tranCode.equals("2")) {
			int prodNo = Integer.parseInt(request.getParameter("prodNo"));
			purchase = service.getPurchase2(prodNo);
			purchase.setTranCode(tranCode);
			forwardURL += "listSale.do";
		} else if (tranCode.equals("3")) {
			int tranNo = Integer.parseInt(request.getParameter("tranNo"));
			purchase = service.getPurchase(tranNo);
			purchase.setTranCode(tranCode);
			forwardURL += "listPurchase.do";
		}
		
		service.updateTranCode(purchase);
		
		return forwardURL;
	}

}
