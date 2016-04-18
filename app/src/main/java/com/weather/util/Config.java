package com.weather.util;

public class Config {
	//format=2&cityname=北京&key=07ad332c4ef2e4eea5475aea92c682a9
	//city=北京&key=625984ddc6d529fbd834065e41a78fc5
	
	public static final String WEATHER_URL="http://v.juhe.cn/weather/index";
	public static final String QUALITY_URL="http://web.juhe.cn:8080/environment/air/cityair";
	public static final String HEADLINE_NEWS_URL="http://api.sina.cn/sinago/list.json?channel=news_toutiao";
	public static final String KEY_WEATHER_FORMATE="format";
	public static final String KEY_WEATHER_CITYNAME="cityname";
	public static final String VALUE_WEATHER_USER_KEY="07ad332c4ef2e4eea5475aea92c682a9";
	public static final String VALUE_QUALITY_USER_KEY="625984ddc6d529fbd834065e41a78fc5";
	public static final String KEY_QUALITY_CITY="city";
	public static final String KEY_USER_KEY="key";
	public static final String CHARSET="utf-8";
	public static final String KEY_STATUS="status";
}
