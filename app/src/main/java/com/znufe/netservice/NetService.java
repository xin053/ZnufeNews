package com.znufe.netservice;


import com.znufe.netutil.NetUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 获取新闻服务类的父类，提供一些方法获取数据
 */
public class NetService {
	
	public final static String UESRAGENT_PHONE = "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A405 Safari/8536.25"; 
	public final static int COUNT_EVERY_PAGE = 5;
	
	/**
	 * 通过URL请求得到json数组
	 * @param url
	 * @return
	 */
	public static JSONObject[] getJsonObjectsByUrl(String url){
		try {
			String response = NetUtil.postAndGetDate(url).replace("&quot;", "\'");
			JSONArray jsonArray = new JSONArray(response);
			JSONObject[] jsonObjects = new JSONObject[jsonArray.length()];
			for(int i = 0;i<jsonArray.length();i++){
				jsonObjects[i] = jsonArray.getJSONObject(i);
			}  
			return jsonObjects;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过URL请求得到json
	 * @param url
	 * @return
	 */
	public static JSONObject getJsonObjectByUrl(String url){
		try {
			String response = NetUtil.postAndGetDate(url);
			JSONObject jsonObject = new JSONObject(new JSONObject(response).getString("data"));
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 通过url请求得到document
	 * @param url
	 * @return
	 */
	public static Document getDocumentByUrl(String url){
		try {
			Document document = Jsoup.connect(url).get();
			return document;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getHtml(String html)
	{
		Document document = Jsoup.parse(html);
		Elements elements = document.getElementsByClass("page_news");
		elements.select("section.down_news").remove();
		elements.select("dl.clear").remove();
		elements = document.getElementsByClass("main_news");
		elements.select("h6").remove();
		
		elements = document.getElementsByTag("section");
		elements.select("a").remove();
		
		return document.html();
	}
	
	
}
