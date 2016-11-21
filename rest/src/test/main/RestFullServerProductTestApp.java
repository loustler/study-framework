package test.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml", "classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml", "classpath:config/context-transaction.xml" })
public class RestFullServerProductTestApp {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	public RestFullServerProductTestApp() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testGetProductJson() throws Exception {
		HttpClient client = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/user/getProductJson";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

		ObjectMapper objectMapper = new ObjectMapper();
		int prodNo = 10000;
		String jsonValue = objectMapper.writeValueAsString(new Integer(prodNo));

		HttpEntity httpEntity = new StringEntity(jsonValue, "UTF-8");

		httpPost.setEntity(httpEntity);

		System.out.println("httpPost : "+httpPost);

		HttpResponse httpResponse = client.execute(httpPost);

		HttpEntity entity = httpResponse.getEntity();

		InputStream inputStream = entity.getContent();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

		JSONObject jsonObject = (JSONObject) JSONValue.parse(bufferedReader);
		System.out.println(jsonObject);

		Product product = objectMapper.readValue(jsonObject.get("product").toString(), Product.class);
		System.out.println(product);
	}

}
