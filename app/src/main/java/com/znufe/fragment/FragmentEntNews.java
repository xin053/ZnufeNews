package com.znufe.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.znufe.adapter.EntNewsListViewAdapter;
import com.znufe.domain.EntNews;
import com.znufe.netservice.GetEntNewsService;
import com.znufe.news.R;
import com.znufe.news.TechNewsDetailsActivity;

import java.util.List;


public class FragmentEntNews extends Fragment implements OnItemClickListener{

    private EntNewsListViewAdapter adapter;
    private ListView listView;
    private boolean isLoading = true;
    private Activity activity;
    private int page = 0;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_ent, container, false);
        this.activity = getActivity();
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        listView = (ListView)activity.findViewById(R.id.fragment_news_listview);
        adapter = new EntNewsListViewAdapter(activity, null);
        listView.addFooterView(View.inflate(activity, R.layout.foot, null));
        listView.setAdapter(adapter);
    //    listView.setOnScrollListener(this);
        listView.setOnItemClickListener(this);
        new MyAsyncTaskGetNews().execute(page);
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        if(view.getId()!=R.id.foot_view){
            Intent intent = new Intent(activity, TechNewsDetailsActivity.class);
            EntNews entNews = adapter.getEntNewss().get(position);
            intent.putExtra("url", getUrl(entNews.getId(),entNews.getPubDate()));
            startActivity(intent);
        }
    }

    public String getUrl(String id,String date)
    {
        String url = "http://tech.sina.com.cn/i/"+date.split(" ")[0]+"/doc-i"+id.split("-")[0]+".shtml";
        url = "http://sinanews.sina.cn/sharenews.shtml?id="+id;
        return url;
    }

 //   @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//    }
//
//    /**
//     * 滑动到底时自动加载更多
//     */
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem,
//                         int visibleItemCount, int totalItemCount) {
//        if(totalItemCount <= firstVisibleItem+visibleItemCount+1&&isLoading==false){
//            isLoading = true;
//            new MyAsyncTaskGetNews().execute(page);
//        }
//    }

    /**
     * 异步获取新闻列表集合
     * @author linpeng123l
     *
     */
    public class MyAsyncTaskGetNews extends AsyncTask<Integer, String, List<EntNews>>{
        @Override
        protected List<EntNews> doInBackground(Integer... pages) {
            List<EntNews> newss = GetEntNewsService.getEntNews();
            return newss;
        }
        @Override
        protected void onPostExecute(List<EntNews> newss) {
            if(newss.size()>0){
                adapter.addEntNews(newss);
                adapter.notifyDataSetChanged();
                page++;
            }
            isLoading = false;
        }

    }

}
