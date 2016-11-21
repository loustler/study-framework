package com.model2.mvc.view.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeAction extends Action {

	public UpdateTranCodeAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SearchVO searchVO = new SearchVO();
		PurchaseVO purchaseVO = null;
		PurchaseService purchaseServicervice = new PurchaseServiceImpl();
		ProductService productServicervice = new ProductServiceImpl();
		String pageReturn = "forward:/purchase/listSale.jsp";

		System.out.println("parameter trancode : " + request.getParameter("tranCode"));
		String tranCode = request.getParameter("tranCode");
		if (tranCode.equals("003")) {
			System.out.println(this.getClass().getName() + "req get Para tranCode : " + request.getParameter("tranCode"));
			int tranNo = Integer.parseInt(request.getParameter("tranNo"));
			purchaseVO = purchaseServicervice.getPurchase(tranNo);
			System.out.println(this.getClass().getName()+"getPurhcase(trnNo) tranCode : "+purchaseVO.getTranCode());
			purchaseVO.setTranCode(tranCode);
			System.out.println(this.getClass().getName()+"getPurhcase(trnNo) tranCode : "+purchaseVO.getTranCode());
			purchaseServicervice.updateTranCode(purchaseVO);
			purchaseVO = purchaseServicervice.getPurchase(tranNo);
			pageReturn = "forward:/purchase/listPurchase.jsp";
		} else if (tranCode.equals("002")) {
			System.out.println(this.getClass().getName() + "req get Para prodNo : " + request.getParameter("prodNo"));
			int prodNo = Integer.parseInt(request.getParameter("prodNo"));
			purchaseVO = purchaseServicervice.getPurchase2(prodNo);
			purchaseVO.setTranCode(tranCode);
			purchaseServicervice.updateTranCode(purchaseVO);
			purchaseVO = purchaseServicervice.getPurchase2(prodNo);
		}

		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		System.out.println("UpdateTranCodeAction page : " + page);

		String menu = request.getParameter("menu");

		searchVO.setPage(page);

		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		String pageUnit = getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));

		Map<String, Object> map = productServicervice.getProductList(searchVO);

		Integer count = (Integer) map.get("count");
		List<PurchaseVO> purList = (ArrayList<PurchaseVO>) map.get("purList");
		List<ProductVO> proList = (ArrayList<ProductVO>) map.get("proList");

		request.setAttribute("searchVO", searchVO);
		request.setAttribute("proList", proList);
		request.setAttribute("purList", purList);
		request.setAttribute("count", count);
		request.setAttribute("menu", menu);

		System.out.println(this.getClass().getName() + " setAtt search vo : " + searchVO);
		System.out.println(this.getClass().getName() + " setAtt proList : " + proList);
		System.out.println(this.getClass().getName() + " setAtt purList : " + purList);
		System.out.println(this.getClass().getName() + " setAtt count : " + count);
		System.out.println(this.getClass().getName() + " menu : " + menu);

		System.out.println("Last View : "+pageReturn);
		return pageReturn;
	}

}
