package com.weather.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.weather.adapter.HeadLineNewsListAdapter;
import com.weather.bean.HeadlineNewsBean;
import com.weather.net.HttpMethod;
import com.weather.net.NetConnection;
import com.weather.util.Config;
import com.znufe.domain.CommentCountInfo;
import com.znufe.domain.TechNews;
import com.znufe.news.HeadlineNewsDetailsAcitivty;
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
public class HeadlineNewsFragment extends Fragment implements AdapterView.OnItemClickListener {

    private List<HeadlineNewsBean> headlineNewsBeansList;
    private ListView headlineNewslistView;
    private Activity activity;
    private HeadLineNewsListAdapter headLineNewsListAdapter;
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
                headlineNewsBeanTemp.setId(newsJson.getString("id"));
                headlineNewsBeanTemp.setTitle(newsJson.getString("title"));
                headlineNewsBeanTemp.setLong_title(newsJson.getString("long_title"));
                headlineNewsBeanTemp.setSource(newsJson.getString("source"));
                headlineNewsBeanTemp.setLink(newsJson.getString("link"));
                headlineNewsBeanTemp.setPicLink(newsJson.getString("pic"));
                headlineNewsBeanTemp.setKpic(newsJson.getString("kpic"));
                headlineNewsBeanTemp.setIntroduce(newsJson.getString("intro"));
                headlineNewsBeanTemp.setPubDate(newsJson.getString("pubDate"));
                headlineNewsBeanTemp.setArticlePubDate(newsJson.getInt("articlePubDate"));
                headlineNewsBeanTemp.setComments(newsJson.getString("comments"));
                if(newsJson.has("pics")) {
                    headlineNewsBeanTemp.setPicsArray(newsJson.getJSONObject("pics").getJSONArray("list"));
                }
                headlineNewsBeanTemp.setFeedShowStyle(newsJson.getString("feedShowStyle"));
                headlineNewsBeanTemp.setCategory(newsJson.getString("category"));
                if(newsJson.has("is_focus")) {
                    headlineNewsBeanTemp.setIs_focus(newsJson.getBoolean("is_focus"));
                }
                if(newsJson.has("comment")) {
                    headlineNewsBeanTemp.setComment(newsJson.getInt("comment"));
                }
                if(newsJson.has("comment_count_info")) {
                    JSONObject commentCountInfoJson = newsJson.getJSONObject("comment_count_info");
                    CommentCountInfo commentCountInfo = new CommentCountInfo();
                    commentCountInfo.setQreply(commentCountInfoJson.getInt("qreply"));
                    commentCountInfo.setTotal(commentCountInfoJson.getInt("total"));
                    commentCountInfo.setShow(commentCountInfoJson.getInt("show"));
                    commentCountInfo.setCommentStatus(commentCountInfoJson.getInt("comment_status"));
                    commentCountInfo.setPraise(commentCountInfoJson.getInt("praise"));
                    commentCountInfo.setDispraise(commentCountInfoJson.getInt("dispraise"));
                    headlineNewsBeanTemp.setComment_count_info(commentCountInfo);
                }
                headlineNewsBeansList.add(headlineNewsBeanTemp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setNewsListView(){
        headlineNewslistView= (ListView) activity.findViewById(R.id.lv_headline_news);
        headLineNewsListAdapter=new HeadLineNewsListAdapter(activity,headlineNewsBeansList);
        headlineNewslistView.addFooterView(View.inflate(activity,R.layout.foot,null));
        headlineNewslistView.setAdapter(headLineNewsListAdapter);
        headlineNewslistView.setOnItemClickListener(this);
        headlineNewslistView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0, 0, 0, "¹²Ïí");
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        int id = (int) info.id;
        HeadlineNewsBean headlineNewsBean = headLineNewsListAdapter.getHeadlineNewsBeanList().get(id);

        switch (item.getItemId()) {
            case 0:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,getUrl(headlineNewsBean.getId(),headlineNewsBean.getPubDate()));
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,getResources().getText(R.string.send_to)));
                break;

            default:
                break;
        }

        return super.onContextItemSelected(item);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        if(view.getId()!=R.id.foot_view){
            Intent intent = new Intent(activity, HeadlineNewsDetailsAcitivty.class);
            HeadlineNewsBean headlineNewsBean=headLineNewsListAdapter.getHeadlineNewsBeanList().get(position);
            intent.putExtra("url",getUrl(headlineNewsBean.getId(),headlineNewsBean.getPubDate()));
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
