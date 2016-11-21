package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDAO;
import com.model2.mvc.service.purchase.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	@Qualifier("purchaseDAOImpl")
	PurchaseDAO dao;

	public PurchaseServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addPurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return dao.addPurchase(purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getPurchase(tranNo);
	}

	@Override
	public Purchase getPurchase2(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getPurchaseProd(prodNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", dao.countPurchaseData(search,buyerId));
		map.put("list", dao.getPurchaseList(search,buyerId));
		return map;
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", dao.countSaleData(search));
		map.put("list", dao.getSaleList(search));
		return map;
	}

	@Override
	public int updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return dao.updatePurchase(purchase);
	}

	@Override
	public int updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateTranCode(purchase);
	}

	@Override
	public int removePurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		return dao.removePurchase(tranNo);
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(PurchaseDAO dao) {
		this.dao = dao;
	}

}
