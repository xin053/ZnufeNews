package com.znufe.netutil;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 网络操作工具类
 */
public class NetUtil { 
	public final static  String UESRAGENT_PHONE = "User-Agent: R809T__sinanews__4.9.5__android__4.2.1"; 
	
	public static String postAndGetDate(String url){
		String response=null;
		System.out.println(url);
		try{
			HttpPost httpPost=new HttpPost(url);
			httpPost.setHeader("User-Agent", UESRAGENT_PHONE);
			DefaultHttpClient httpClient=new DefaultHttpClient();
			HttpResponse httpResponse=httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode()==200){
				response=EntityUtils.toString(httpResponse.getEntity());
			}
		}catch (Exception e) {
			response="connect_error";
			e.printStackTrace();
		}
		return response;
	}

}
