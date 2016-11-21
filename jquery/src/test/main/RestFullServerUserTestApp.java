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

import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml", "classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml", "classpath:config/context-transaction.xml" })
public class RestFullServerUserTestApp {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	public RestFullServerUserTestApp() {
		// TODO Auto-generated constructor stub
	}

	 @Test
	public void testLoginUser() throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/user/loginJson";

		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

		User user = new User();
		user.setUserId("admin");
		user.setPassword("1234");
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonValule = objectMapper.writeValueAsString(user);
		System.out.println("jsonValue : "+jsonValule);
		HttpEntity httpEntity = new StringEntity(jsonValule, "UTF-8");

		httpPost.setEntity(httpEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);

		HttpEntity httpEntity2 = httpResponse.getEntity();

		InputStream inputStream = httpEntity2.getContent();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

		JSONObject jsonObject2 = (JSONObject) JSONValue.parse(bufferedReader);
		System.out.println(jsonObject2);

		ObjectMapper objectMapper2 = new ObjectMapper();
		User user2 = objectMapper2.readValue(jsonObject2.get("user").toString(), User.class);
		System.out.println(user2);
	}

	// @Test
	public void testAddUserJson() throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/user/addUserJson";

		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

		User user = new User();
		user.setUserId("aaa");
		user.setPassword("1111");
		user.setUserName("hong");
		ObjectMapper mapper = new ObjectMapper();
		String jsonValue = mapper.writeValueAsString(user);
		HttpEntity entity = new StringEntity(jsonValue, "UTF-8");

		httpPost.setEntity(entity);
		HttpResponse httpResponse = httpClient.execute(httpPost);

		HttpEntity httpEntity = httpResponse.getEntity();

		InputStream is = httpEntity.getContent();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		JSONObject jsonObject = (JSONObject) JSONValue.parse(bufferedReader);
		System.out.println("JSON Data : " + jsonObject);

		User serverUser = mapper.readValue(jsonObject.get("user").toString(), User.class);
		System.out.println(serverUser);
	}

//	@Test
	public void testGetUserJson() throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://127.0.0.1:8080/user/getUserJson";

		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

		User user = new User();
		user.setUserId("admin");

		ObjectMapper mapper = new ObjectMapper();
		String jsonValue = mapper.writeValueAsString(user);
		HttpEntity entity = new StringEntity(jsonValue, "UTF-8");

		httpPost.setEntity(entity);
		HttpResponse httpResponse = httpClient.execute(httpPost);

		HttpEntity httpEntity = httpResponse.getEntity();

		InputStream is = httpEntity.getContent();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		JSONObject jsonObject = (JSONObject) JSONValue.parse(bufferedReader);
		System.out.println("JSON Data : " + jsonObject);

		User serverUser = mapper.readValue(jsonObject.get("user").toString(), User.class);
		System.out.println(serverUser);
	}

}
