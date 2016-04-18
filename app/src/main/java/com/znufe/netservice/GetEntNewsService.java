package com.znufe.netservice;

import android.util.Log;

import com.znufe.domain.CommentCountInfo;
import com.znufe.domain.EntNews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiangQun on 2016/3/17.
 */
public class GetEntNewsService extends NetService{
    /**
     * 获取新浪娱乐新闻列表服务类
     * @return 新闻列表
     */
    public static List<EntNews> getEntNews() {
        List<EntNews> entNews = new ArrayList<EntNews>();
        String url = "http://api.sina.cn/sinago/list.json?channel=news_ent"; //娱乐新闻的API接口
        JSONObject jsonObject = getJsonObjectByUrl(url);
        if(jsonObject!=null){
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                Log.i("Fragment", "jsonArray");
                for(int i=0;i<jsonArray.length();i++){
                    //获取新闻列表
                    EntNews everyNews = new EntNews();
                    JSONObject object = jsonArray.getJSONObject(i);

                    everyNews.setId(object.getString("id"));
                    everyNews.setTitle(object.getString("title"));
                    everyNews.setLong_title(object.getString("long_title"));
                    everyNews.setSource(object.getString("source"));
                    everyNews.setLink(object.getString("link"));
                    everyNews.setPicLink(object.getString("pic"));
                    everyNews.setKpic(object.getString("kpic"));

                    everyNews.setIntroduce(object.getString("intro"));
                    everyNews.setPubDate(object.getString("pubDate"));
                    everyNews.setArticlePubDate(object.getInt("articlePubDate"));
                    everyNews.setComments(object.getString("comments"));

                    if(object.has("pics"))
                    {
                        JSONObject picObject = object.getJSONObject("pics");
                        JSONArray picsArray = picObject.getJSONArray("list");
                        everyNews.setPicsArray(picsArray);
                    }
                    everyNews.setFeedShowStyle(object.getString("feedShowStyle"));
                    everyNews.setCategory(object.getString("category"));

                    if(object.has("is_focus"))
                       everyNews.setIs_focus(object.getBoolean("is_focus"));
                    else
                        everyNews.setIs_focus(false);

                    if(object.has("comment"))
                    {
                        everyNews.setComment(object.getInt("comment"));
                    }

                    if(object.has("comment_count_info")) {
                        JSONObject com = object.getJSONObject("comment_count_info");
                        CommentCountInfo comment = new CommentCountInfo();
                        comment.setQreply(com.getInt("qreply"));
                        comment.setTotal(com.getInt("total"));
                        comment.setShow(com.getInt("show"));
                        comment.setCommentStatus(com.getInt("comment_status"));

                        if (com.has("praise")) {
                            comment.setPraise(com.getInt("praise"));
                        }
                        if (com.has("dispraise")) {
                            comment.setDispraise(com.getInt("dispraise"));
                        }
                        everyNews.setComment_count_info(comment);
                    }
                    entNews.add(everyNews);
                }
            } catch (JSONException e) {
              //  Log.e("GetEntNewsService", e.toString());
                e.printStackTrace();
            }
        }
        Log.i("GetEntNewsService", entNews.toString());
        return entNews;
    }
}
