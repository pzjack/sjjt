/**
 * 
 */
package org.sj.oaprj;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author zhen.pan
 *
 */
public class HttpRestTool {

	private static String  SECRET = "dleduApp" + ':' + "mySecretOAuthSecret";
	private String baseUrl = "http://172.16.40.108:8080";
	private String loginUrl = "http://172.16.23.58:8083/dledu/oauth/token";
	
	private static HttpPost postForm(String url, Map<String, String> params, String authorization) {
		HttpPost httpost = new HttpPost(url);
		if(null != authorization)
			httpost.addHeader("Authorization", authorization);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}

		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return httpost;
	}

	private static HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost) {
		HttpResponse response = null;

		try {
			response = httpclient.execute(httpost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private static String paseResponse(HttpResponse response) {
		HttpEntity entity = response.getEntity();
		String body = null;
		try {
			body = EntityUtils.toString(entity);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return body;
	}

	private static String invoke(CloseableHttpClient httpclient, HttpPost httpost) {
		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);
		return body;
	}

	private void excutePostForm(String url, Map<String, String> params, String authorization) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost post = postForm(url, params, authorization);
			System.out.println(invoke(httpclient, post));
		} finally {
			httpclient.close();
		}
	}
	
	private void excutePostJSON(String url, String json, String authorization) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault(); // 13800510543
      try {
          HttpEntityWrapper en = new HttpEntityWrapper(new StringEntity(json, ContentType.create("application/json", "UTF-8")));
          HttpPost post = new HttpPost(url);
          post.setEntity(en);
          if(null != authorization)
        	  post.addHeader("Authorization", authorization);
          HttpResponse response = httpclient.execute(post);
          HttpEntity resEntity = response.getEntity();
          System.out.println(EntityUtils.toString(resEntity));
      } catch (IOException ex) {
          Logger.getLogger(HttpRestTool.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
    	  httpclient.close();
      }
	}
	
	private void excuteGetUrl(String url, String authorization) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault(); // 13800510543
      try {
          HttpGet get = new HttpGet(url);
          if(null != authorization)
        	  get.addHeader("Authorization", authorization);
          HttpResponse response = httpclient.execute(get);
          HttpEntity resEntity = response.getEntity();
          System.out.println(EntityUtils.toString(resEntity));
      } catch (IOException ex) {
          Logger.getLogger(HttpRestTool.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
    	  httpclient.close();
      }
	}
	
	private void excutePutJSON(String url, String json, String authorization) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault(); // 13800510543
      try {
          HttpEntityWrapper en = new HttpEntityWrapper(new StringEntity(json, ContentType.create("application/json", "UTF-8")));
          HttpPut put = new HttpPut(url);
          put.setEntity(en);
          if(null != authorization)
        	  put.addHeader("Authorization", authorization);
          HttpResponse response = httpclient.execute(put);
          HttpEntity resEntity = response.getEntity();
          System.out.println(EntityUtils.toString(resEntity));
      } catch (IOException ex) {
          Logger.getLogger(HttpRestTool.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
    	  httpclient.close();
      }
	}
	
	
	public void mockLogin() throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", "blzh110202071017");
		params.put("password", "123456");
		params.put("grant_type", "password");
		params.put("scope", "read write");
		params.put("client_secret", "mySecretOAuthSecret");
		params.put("client_id", "dleduApp");
		excutePostForm(loginUrl, params, "Basic " + Base64.getEncoder().encodeToString(SECRET.getBytes()));
	}
	
	
	public void mockLogin2() throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", "admin");
		params.put("password", "admin");
		params.put("grant_type", "password");
		params.put("scope", "read write");
		params.put("client_secret", "AB3456DCEF4590870076544334");
		params.put("client_id", "mytestoauth2resource1");
		excutePostForm("http://localhost:8080/oauth/token", params, "Basic " + Base64.getEncoder().encodeToString("mytestoauth2resource1:AB3456DCEF4590870076544334".getBytes()));
	}
	
	public void mockPutAssess() throws IOException {
	/*	{
			  "content": "tttttttttttttt",
			  "scheduleId": 1,
			  "score": 5
		}*/
		String json = "{" + 
				  "\"content\": \"tttttttttttttt\"," +
				  "\"scheduleId\": 1," +
				  "\"score\": 5" +
				  "}";
		excutePutJSON(baseUrl + "/api/phone/v1/assess/create", json, "bearer 27664294-189c-4215-9ea9-0e9539703071");
	}
	
	public void mockPostAssess() throws IOException {
	/*	{
			  "content": "tttttttttttttt",
			  "scheduleId": 1,
			  "score": 5
		}*/
		String json = "{" + 
//				  "\"content\": \"tttttttttttttt\"," +
				  "\"scheduleId\": 1," +
				  "\"score\": 5" +
				  "}";
		excutePostJSON(baseUrl + "/api/phone/v1/assess/create", json, "bearer 01bdf763-6fe9-4a77-b1e3-2f4ec2fd2c4a");
	}
	
	public void mockPostAssess2() throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("schedule_id", "8341");
		params.put("score", "4");
		params.put("content", "汉字");
		excutePostForm(baseUrl + "/api/phone/v1/assess/create", params, "bearer 5bed741a-9fb7-44fa-bb74-f64410115994");
	}
	
	public void mockPostJsonSaveResource() throws IOException {
		String json = "{" + 
				  "\"parent_id\": \"5704bb3395a146d403721cd7\"," +
				  "\"name\": \"点点学生端\"," +
				  "\"restype\": \"20\"," +
				  "\"ressign\": \"dd\"," +
//				  "\"resource\": \"/api/phone/v1/assess/create\"," +
				  "\"resource\": \"dd_student\"," +
				  "\"whitehost\": [\"172.16.23.58\"]" +
				  "}";
		excutePostJSON("http://localhost:3000/app/back/auth/api/v1/addResource", json, null);
	}
	
	public void mockGetweekList() throws IOException {
		excuteGetUrl("http://172.16.23.58:8087/diandian/api/phone/v1/week/getList", "bearer 18c05d41-cb8f-4036-9134-ebb7b3842c77");
	}
	
	public void mockGetweekCourseList() throws IOException {
		excuteGetUrl("http://localhost:8080/api/phone/v1/teacher/course/getweek?weekId=2775", "bearer 15ed1977-7d58-45c4-bbe3-c7dd9b358f2a");
	}
	
	public void mockGetweekCourseListDay() throws IOException {
		excuteGetUrl("http://localhost:8080/api/phone/v1/teacher/course/getList?offset=1&limit=10", "bearer 9c34b8b7-6007-4652-a873-ddac6e870ed3");
	}
	
	public void mockGetweekCourseStudList() throws IOException {
		excuteGetUrl("http://localhost:8080/api/phone/v1/student/courseweek/get?weekId=2775", "bearer 15ed1977-7d58-45c4-bbe3-c7dd9b358f2a");
	}
	
	public void mockQueryResource() throws IOException {
		String json = "{" + 
				  "\"parent_id\": \"5704bb3395a146d403721cd7\"" +
				  "}";
		excutePostJSON("http://172.16.40.108:3000/app/back/auth/api/v1/queryResource", json, null);
	}
	

	public void mockQueryFindByIdPostResource() throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "570349da0c540054342c0686");
		excutePostForm("http://localhost:3000/app/back/auth/api/v1/findByIdResource", params, null);
	}
	
	public void mockUpdateResource() throws IOException {
		String json = "{" + 
				  "\"_id\": \"57034fd526a226f835119cfd\"," +
				  "\"name\": \"点点学生端:保存评教信息\"" +
				  "}";
		excutePostJSON("http://localhost:3000/app/back/auth/api/v1/updateResource", json, null);
	}
	
	public void mockDeleteResource() throws IOException {
		String json = "{" + 
				  "\"id\": \"5704bb3395a146d403721cd7\"" +
				  "}";
		excutePostJSON("http://localhost:3000/app/back/auth/api/v1/deleteResource", json, null);
	}
	
	public void mockSaveRoleResource() throws IOException {
		String json = "{" + 
				  "\"role\": \"admin\"," +
				  "\"name\": \"点点管理员\"" +
				  "}";
		excutePostJSON("http://localhost:3000/app/back/auth/api/v1/addRoleResource", json, null);
	}
	
	public void mockUpdateRoleResource() throws IOException {
		String json = "{" + 
				  "\"_id\": \"5705fa60de6c59c42400d305\"," +
//				  "\"name\": \"test update\"," +
				  "\"resources\": [" +
				  	"{" +
				  		"\"_id\": \"5704bb3395a146d403721cd7\"," +
				  		"\"name\": \"点点学生端------保存评教信息\"," +
				  		"\"restype\": \"10\"," +
				  		"\"resource\": \"dd\"," +
				  		"\"auth\": \"10\"" +
				  	"}," +
				  	"{" +
			  		"\"_id\": \"5704bb8f95a146d403721cd9\"," +
			  		"\"name\": \"点点学生端\"," +
			  		"\"restype\": \"20\"," +
			  		"\"auth\": \"10\"" +
			  		"}" +
				  "]}";
//		json = "{\"_id\": \"5705c27cd780ba9c193676d0\",\"resources\": []}";
		excutePostJSON("http://localhost:3000/app/back/auth/api/v1/updateRoleResource", json, null);
	}

	
	public void mockQueryRoleResource() throws IOException {
		String json = "{" + 
				  "\"role\": \"admin\"" +
				  "}";
		excutePostJSON("http://localhost:3000/app/back/auth/api/v1/queryRoleResource", json, null);
	}

	public void mockDeleteRoleResource() throws IOException {
		String json = "{" + 
				  "\"id\": \"5705c27cd780ba9c193676d0\"" +
				  "}";
		excutePostJSON("http://localhost:3000/app/back/auth/api/v1/deleteRoleResource", json, null);
	}
	
	public void mockFindAndSetNewPwd() throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("phone", "18681861821");
		params.put("code", "255627");
		params.put("module", "dd_fp");
		params.put("pwd", "admin");
		excutePostForm("http://127.0.0.1:8080/api/web/v1/users/findandsetpwd", params, null);
	}
	

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		HttpRestTool t = new HttpRestTool();
		
//		t.mockLogin();
		t.mockLogin2();
//		t.mockPutAssess();
		
//		t.mockPostAssess();
		
//		t.mockPostAssess2();
		
//		t.mockPostJsonSaveResource();
//		t.mockGetweekList();
//		t.mockGetweekCourseList();
//		t.mockGetweekCourseListDay();
		
//		t.mockGetweekCourseStudList();
		
//		t.mockQueryResource();
//		t.mockQueryFindByIdPostResource();
		
//		t.mockUpdateResource();
//		t.mockDeleteResource();
		
//		t.mockSaveRoleResource();
//		t.mockUpdateRoleResource();
		
//		t.mockQueryRoleResource();
//		t.mockDeleteRoleResource();
		
//		t.mockFindAndSetNewPwd();
		
	}

}