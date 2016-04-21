/**
 * 
 */
package org.sj.oaprj;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author zhen.pan
 *
 */
public class SampleGetStudentInfo {
	class Student {
		String xm;//姓名
		String sfzh;//身份证号码
		String kh;//考号
		String pc;//录取批次
		String zy;//专业
		String yx;//录取院校
		public String getXm() {
			return xm;
		}
		public void setXm(String xm) {
			this.xm = xm;
		}
		public String getSfzh() {
			return sfzh;
		}
		public void setSfzh(String sfzh) {
			this.sfzh = sfzh;
		}
		public String getKh() {
			return kh;
		}
		public void setKh(String kh) {
			this.kh = kh;
		}
		public String getPc() {
			return pc;
		}
		public void setPc(String pc) {
			this.pc = pc;
		}
		public String getZy() {
			return zy;
		}
		public void setZy(String zy) {
			this.zy = zy;
		}
		public String getYx() {
			return yx;
		}
		public void setYx(String yx) {
			this.yx = yx;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("姓名:").append(xm);
			sb.append("\t身份证:").append(sfzh);
			sb.append("\t考号:").append(kh);
			sb.append("\t批次名称:").append(pc);
			sb.append("\t专业名称:").append(zy);
			sb.append("\t院校名称:").append(yx);
			return sb.toString();
		}
	}
	
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

	private static String paserResponse(HttpResponse response) {
		if(HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
			System.out.println("请求没有被正常响应\nRespose is :" + response.getStatusLine());
			return null;
		}
		HttpEntity entity = response.getEntity();
		StringBuilder body = new StringBuilder();
		try {
//			body = EntityUtils.toString(entity);
			//以下这部分仅匹配并返回包含数据的部分内容，其余部分全部忽略：
			  /************************************
			    <tr  class="shstyle679706692_3161" >    
			    <td align='center' width='40%'>考号</td>    
			    <td align='center' width='30%'>0101150135</td>    
			    <td align='center' >姓名：李淑祺</td>    
			  </tr>    
			  <tr  class="qtstyle679706692_3161" >    
			    <td align='center' >院校名称</td>    
			    <td align='center' >专业名称</td>    
			    <td align='center' >批次名称</td>    
			  </tr>    
			  <tr  class="dsstyle679706692_3161" >    
			    <td align='center'>西安欧亚学院</td>    
			    <td align='center'>工程管理</td>    
			    <td align='center'>三批本科</td>    
			  </tr>
			  ******************************************/
			
			java.io.BufferedReader bin = new java.io.BufferedReader(new java.io.InputStreamReader(entity.getContent(), "GBK"));//目前网站是写死的GBK编码
			String line = bin.readLine();
			boolean form = false, table = false;
			while(null != line) {
				if(line.indexOf("<form ") >= 0) {
					form = true;
					line = bin.readLine();
					continue;
				}
				if(form && line.indexOf("<table ") >= 0) {//包含数据内容的页面部分的开始位置
					table = true;
					line = bin.readLine();
					continue;
				}
				if(line.indexOf("</table>") >= 0) {//包含数据的内容部分已经结束，后边的内容不需要在处理
					form = false;
					table = false;
					break;
				}
				if(table) {
					body.append(line).append("\n");
				}
				line = bin.readLine();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return body.toString();
	}

	private static String invoke(CloseableHttpClient httpclient, HttpPost httpost) {
		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paserResponse(response);
		return body;
	}

	private void excutePostForm(String url, Map<String, String> params, String authorization, Student st) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost post = postForm(url, params, authorization);
			String content = invoke(httpclient, post);
			boolean kh = false, xm = false, yx = false, zy = false, pc = false;
			if(null != content) {
				String[] lines = content.split("\n");//按照换行符分隔内容
				for(String line : lines) {
					if(line.indexOf("<tr ") >= 0) {
						continue;
					}
					if(line.indexOf("<td ") >= 0) {
						int p1 = line.indexOf(">"),p2 = line.indexOf("<", p1);
						if(p1 >= 0 && p2 > 0) {
							String tmp = line.substring(p1 + 1, p2).trim();
							if(xm) {
								int p = tmp.indexOf("姓名：");
								if(p >= 0) {
									st.setXm(tmp.substring(p + "姓名：".length()));
								} else {
									st.setXm(tmp);
								}
								xm = false;
								continue;
							}
							if(kh) {
								if(!tmp.equals(st.getKh())) {
									System.out.println("考号：" + tmp + " 查询返回和传入不一致");
									break;
								} else {
									kh = false;
									xm = true;
								}
								continue;
							}
							if(tmp.indexOf("考号") >= 0) {
								kh = true;
								continue;
							}
							if(tmp.indexOf("院校") >= 0) {
								yx = true;
								continue;
							}
							if(tmp.indexOf("专业") >= 0) {
								zy = true;
								continue;
							}
							if(tmp.indexOf("批次") >= 0) {
								pc = true;
								continue;
							}
							if(yx && zy && pc) {
								st.setYx(tmp);
								yx = false;
								continue;
							}
							if(zy && pc) {
								st.setZy(tmp);
								zy = false;
								continue;
							}
							if(pc) {
								st.setPc(tmp);
								pc = false;
								continue;
							}
						}
					}
				}
			}
		} finally {
			httpclient.close();
		}
	}
	
	public void mockFindStudent(Student st) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("wbzkzh", st.getKh());
		params.put("wbsfzh", st.getSfzh());
		excutePostForm("http://www.sneac.com/gklqcx/lqjgcx_jg.jsp?wbtreeid=3077", params, null, st);
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		SampleGetStudentInfo t = new SampleGetStudentInfo();
		Student st = t.new Student();
		st.setKh("0101150135");//考号
		st.setSfzh("610103199710141625");//身份证号
		t.mockFindStudent(st);
		System.out.println(st);
	}

}
