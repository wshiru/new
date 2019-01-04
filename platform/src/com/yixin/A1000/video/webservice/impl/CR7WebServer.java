package com.yixin.A1000.video.webservice.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import com.yixin.framework.util.xml.XMLUtil;

/**
 * 查询安徽创世7.0的RTSP服务器。
 * 
 * @author lianglq
 * 
 */
public class CR7WebServer {
	private static CR7WebServer obj;
	private static Logger logger = Logger.getLogger(CR7WebServer.class);
	
	// 不要老查询,登录。
	private HttpClient httpClient;
	/** 最后刷新时间 */
	private Date lastRefreshTime;

	private Map<String, String> urlList;

	private String urlStr;

	public CR7WebServer() {
		httpClient = new DefaultHttpClient();
		lastRefreshTime = null;
		urlList = new HashMap<String, String>();
	}

	public static synchronized CR7WebServer getCR7WebServer() {
		if (obj == null) {
			obj = new CR7WebServer();
		}
		return obj;
	}

	private String doGet(String url, Map<String, String> params) {
		String uriAPI = urlStr + url;
		String queryString = "";
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				try {// 转码
					String name = URLEncoder.encode(entry.getKey(), "UTF-8");
					String value = URLEncoder.encode(entry.getValue(), "UTF-8");
					if (!"".equals(queryString)) {
						queryString += "&" + name + "=" + value;
					} else {
						queryString =  name + "=" + value;
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		if (!"".equals(queryString)) {
			uriAPI = uriAPI + "?" + queryString;
		}
		String strResult = null;

		HttpGet httpGet = new HttpGet(uriAPI);
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 第3步：使用getEntity方法获得返回结果
				strResult = EntityUtils.toString(httpResponse.getEntity());
				// 去掉返回结果中的"\r"字符，
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return strResult;
	}

	private String doPost(String url, Map<String, String> params)
			throws IOException {
		String strResult = "";

		HttpPost post = new HttpPost(urlStr + url);
		List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			postData.add(new BasicNameValuePair(entry.getKey(), entry
					.getValue()));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postData,
				HTTP.UTF_8);
		post.setEntity(entity);
		HttpResponse response = httpClient.execute(post);

		// 若状态码为200 ok
		if (response.getStatusLine().getStatusCode() == 200) {
			// 取出回应字串
			strResult = EntityUtils.toString(response.getEntity());
		}
		return strResult;
	}

	private Boolean login() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("epid", VCIConnectionPool.cr7WebServerEpid);
		params.put("username", VCIConnectionPool.cr7WebServerUsername);
		params.put("pwd", VCIConnectionPool.cr7WebServerPassword);
		
		String ret = doGet("/MSP/index.php", params);
		if(null == ret){
			logger.info("登录访问cr7Server失败");
			return false;			
		}
		Document doc = XMLUtil.parseToXML(ret);
		if (null == doc) {
			logger.info("登录访问cr7Server返回数据XML解析失败");
			return false;
		}
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Element root = doc.getRootElement(); // 指向根节点
		Element response = (Element) root.element("Response");
		String errorCode = response.attributeValue("NUErrorCode");
		String description = response.attributeValue("Description");
		if("0".equals(errorCode)){
			return true;
		}else{
			logger.error(String.format("登录访问cr7Server返回登录失败：errorCode=%s;description=%s", errorCode,description));
			return false;
		}		
	}

	private void refreshRestList() {

		String ret = doGet(
				"/MSP/index.php?streamtype=TRANSCODE&alg=MPEG4&resolution=CIF&framerate=5&bitrate=100&audioflag=0&audioalg=AMR&start=0&limit=200",
				null);
		System.out.println(ret);
		if(null == ret){
			logger.info("访问cr7Server取数据失败");
			return ;			
		}
		Document doc = XMLUtil.parseToXML(ret);
		if (null == doc) {
			logger.info("访问cr7Server取数据返回数据XML解析失败");
			return;
		}
		Element root = doc.getRootElement(); // 指向根节点
		Element response = (Element) root.element("Response");
		String errorCode = response.attributeValue("NUErrorCode");
		String description = response.attributeValue("Description");
		if(!"0".equals(errorCode)){
			logger.error(String.format("访问cr7Server取数据返回失败：errorCode=%s;description=%s", errorCode,description));
			return ;
		}
		Element Count = (Element) response.element("Count");
		int c = Integer.parseInt(Count.getText());
		List list = response.elements("Camera");
		for(int i = 0 ; i < c ; i++)
		{
			Element e =  (Element)list.get(i);
			Element DataRate = (Element) e.element("DataRate");
			String key = String.format("%s-%s", e.attributeValue("PUID"), e.attributeValue("IVIndex"));
			String url = DataRate.attributeValue("TokenUrl");
			urlList.put(key, url);
			System.out.println(key);
			System.out.println(url);
		}		
	}

	public synchronized String getRtspUrl(String VideoServerCode,
			String CameraCode) {

		// 显示所有设备
		Date now = new Date();
		// 60分钟查询一次
		if (lastRefreshTime == null
				|| (now.getTime() - lastRefreshTime.getTime()) > 60 * 60 * 1000) {
			VCIConnectionPool.readConfig();
			urlStr = VCIConnectionPool.cr7WebServerUrl;
			//登录成功后，再查询列表
			if(login()){
				refreshRestList();	
			}
			lastRefreshTime = new Date();
		}
		String key = String.format("%s-%s", VideoServerCode, CameraCode);
		String url = urlList.get(key);
		if(url == null){
			url = "";
		}
		return url;
	}
}
