package test.main;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml", "classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml", "classpath:config/context-transaction.xml" })
public class RestFullServerPurchaseTestApp {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	public RestFullServerPurchaseTestApp() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void addProductJsonTest() throws Exception{
		HttpClient client = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/purchase/addPurchaseJson";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		User user = new User();
		String buyerId = "user01";
		user.setUserId(buyerId);
		int prodNo = 10010;
		Product product  = new Product();
		product.setProdNo(prodNo);
		Purchase purchase = new Purchase();
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("purchase", purchase);
		
		String jsonData = jsonObject.toJSONString();
		HttpEntity entity = new StringEntity(jsonObject.toJSONString(), "UTF-8");
		System.out.println("쏘는 JsonData : "+jsonData);
		httpPost.setEntity(entity);
		
		HttpResponse httpResponse = client.execute(httpPost);
		
	}

}
