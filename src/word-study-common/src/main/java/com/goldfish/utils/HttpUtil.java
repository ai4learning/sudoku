package com.goldfish.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http请求
 * @author cdtangzili
 * @since 2014-12-31 上午10:17:39
 * @version
 */
public class HttpUtil {
	
	/**
	 * 利用http client 调用http服务
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static String handlePostRequest(String url,
			Map<String, String> parameters) {
		String result = "";
		HttpClient client = null;
		HttpPost httpPost = null;
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			if (parameters != null && parameters.size() > 0) {
				for (Map.Entry<String, String> entry : parameters.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					if (key == null || value == null) {
						continue;
					}
					NameValuePair pair = new BasicNameValuePair(key, value);
					nameValuePairs.add(pair);
				}
			}
			httpPost = new HttpPost(url);
			client = new DefaultHttpClient();
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
					nameValuePairs, "UTF-8");
			httpPost.setEntity(formEntity);
			HttpResponse resp = client.execute(httpPost);
			HttpEntity entity = resp.getEntity();
			result = EntityUtils.toString(entity, "UTF-8").trim();
		} catch (Exception e) {
			throw new RuntimeException("HTTP request error," + e.getMessage(),
					e);
		} finally {
			httpPost.abort();
			client.getConnectionManager().shutdown();
		}
		return result;
	}
}
