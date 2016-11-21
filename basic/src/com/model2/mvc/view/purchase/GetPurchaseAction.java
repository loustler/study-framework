package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class GetPurchaseAction extends Action {

	public GetPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PurchaseService service = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = null;
		System.out.println(this.getClass().getName()+"getAtt tranNo : "+request.getParameter("tranNo"));
		System.out.println(this.getClass().getName()+"getAtt vo"+request.getAttribute("purchaseVO"));
		
		if(request.getParameter("tranNo")!=null) {
			System.out.println(this.getClass().getName()+"tranNo exist");
			int tranNo = Integer.parseInt(request.getParameter("tranNo"));
			purchaseVO = service.getPurchase(tranNo);
		}else {
			System.out.println(this.getClass().getName()+"vo exist");
			purchaseVO = (PurchaseVO)request.getAttribute("purchaseVO");
		}
		
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/getPurchase.jsp";
	}

}
