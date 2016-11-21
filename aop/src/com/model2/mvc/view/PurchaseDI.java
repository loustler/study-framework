package com.model2.mvc.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

public class PurchaseDI {
	public static PurchaseService getService() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "com/model2/mvc/resources/config/Context-Common.xml",
						"com/model2/mvc/resources/config/Context-Aspect.xml",
						"com/model2/mvc/resources/config/Context-MyBatis.xml",
						"com/model2/mvc/resources/config/Context-Transaction.xml" });

		return (PurchaseService) applicationContext.getBean("purchaseServiceImpl");
	}

}
