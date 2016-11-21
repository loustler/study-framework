package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDAO;

@Repository
public class PurchaseDAOImpl implements PurchaseDAO {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;
	
	public PurchaseDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addPurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}

	@Override
	public Purchase getPurchaseProd(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.getPurchaseProd", prodNo);
	}

	@Override
	public int updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	@Override
	public int updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}

	@Override
	public List<Purchase> getPurchaseList(Search search, String buyer) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userId", buyer);
		map.put("search", search);
		
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
	}

	@Override
	public List<Purchase> getSaleList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("PurchaseMapper.getSaleList", search);
	}

	@Override
	public int removePurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("PurchaseMapper.removePurchase", tranNo);
	}

	@Override
	public int countPurchaseData(Search search,String buyerId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userId", buyerId);
		map.put("search", search);
		
		return sqlSession.selectOne("PurchaseMapper.countPurchaseData", map);
	}

	@Override
	public int countSaleData(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.countSaleData", search);
	}

	/**
	 * @param sqlSession the sqlSession to set
	 */
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}
