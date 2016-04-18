package com.weather.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.weather.adapter.HeadLineNewsListAdapter;
import com.weather.bean.HeadlineNewsBean;
import com.weather.net.HttpMethod;
import com.weather.net.NetConnection;
import com.weather.util.Config;
import com.znufe.news.R;
import com.znufe.news.TechNewsDetailsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cui on 16-4-17.
 */
public class HeadlineNewsFragment extends Fragment implements AdapterView.OnItemClickListener{

    private List<HeadlineNewsBean> headlineNewsBeansList;
    private ListView headlineNewslistView;
    private Activity activity;
    private HeadLineNewsListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.headline_news_main, container, false);
        headlineNewsBeansList=new ArrayList<HeadlineNewsBean>();
        getNewsData();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity=getActivity();
    }

    private void getNewsData(){
        new NetConnection(Config.HEADLINE_NEWS_URL, HttpMethod.GET, new NetConnection.SuccessCallBack() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject resultJSON=new JSONObject(result);
                    if (resultJSON.getInt("status")==0) {
                        parserNewsData(resultJSON);
                        setNewsListView();
                    }else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallBack() {
            @Override
            public void onFail() {

            }
        });
    }

    private void parserNewsData(JSONObject json){
        try {
            JSONObject dataJSON=json.getJSONObject("data");
            JSONArray listJSONARR=dataJSON.getJSONArray("list");
            for (int i=0;i<listJSONARR.length();i++)
            {
                JSONObject newsJson=listJSONARR.getJSONObject(i);
                HeadlineNewsBean headlineNewsBeanTemp=new HeadlineNewsBean();
                headlineNewsBeanTemp.setTitle(newsJson.getString("title"));
                headlineNewsBeanTemp.setPicUrl(newsJson.getString("pic"));
                headlineNewsBeanTemp.setIntro(newsJson.getString("intro"));
                headlineNewsBeansList.add(headlineNewsBeanTemp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setNewsListView(){
        headlineNewslistView= (ListView) activity.findViewById(R.id.lv_headline_news);
        HeadLineNewsListAdapter headLineNewsListAdapter=new HeadLineNewsListAdapter(activity,headlineNewsBeansList);
        headlineNewslistView.setAdapter(headLineNewsListAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        if(view.getId()!=R.id.foot_view){
            Intent intent = new Intent(activity, TechNewsDetailsActivity.class);
//            EntNews entNews = adapter.get.get(position);
//            intent.putExtra("url", getUrl(entNews.getId(),entNews.getPubDate()));
            startActivity(intent);
        }
    }

    public String getUrl(String id,String date)
    {
        String url = "http://tech.sina.com.cn/i/"+date.split(" ")[0]+"/doc-i"+id.split("-")[0]+".shtml";
        url = "http://sinanews.sina.cn/sharenews.shtml?id="+id;
        return url;
    }
}
