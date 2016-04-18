package com.znufe.netservice;

import com.znufe.domain.CommentCountInfo;
import com.znufe.domain.TechNews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetTechNewsService extends NetService{
	
	/**
	 * 获取科技新闻
	 * @return
	 */
	public static List<TechNews> getTechNews(){
		List<TechNews> techNewsList = new ArrayList<TechNews>();
		String url = "http://api.sina.cn/sinago/list.json?channel=news_tech";
		JSONObject jsonObject = getJsonObjectByUrl(url);
       
		if(jsonObject!=null){
			try {
				JSONArray jsonArray = jsonObject.getJSONArray("list");
				for(int i=0;i<jsonArray.length();i++){
					TechNews techNews = new TechNews();
					JSONObject object = jsonArray.getJSONObject(i);
					techNews.setId(object.getString("id"));
					techNews.setTitle(object.getString("title"));
                    techNews.setLongTitle(object.getString("long_title"));
                    techNews.setSource(object.getString("source"));
                    techNews.setLink(object.getString("link"));
                    techNews.setPic(object.getString("pic"));
                    techNews.setKpic(object.getString("kpic"));
                    techNews.setBpic(object.getString("bpic"));
                    techNews.setIntro(object.getString("intro"));
                    techNews.setPubDate(object.getString("pubDate"));
                    techNews.setArticlePubDate(new Date(object.getLong("articlePubDate")));
                    techNews.setComments(object.getString("comments"));
                    techNews.setFeedShowStyle(object.getString("feedShowStyle"));
                    techNews.setCategory(object.getString("category"));
                    if(object.has("is_focus"))
                    {
                        techNews.setFocus(object.getBoolean("is_focus"));
                    }
                    techNews.setComment(object.getString("comment"));
                    techNews.setCommentCountInfo(new CommentCountInfo(
                            new JSONObject(object.getString("comment_count_info"))
                    ));
                    
					techNewsList.add(techNews);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
        
       return techNewsList;
	}

}
