package com.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weather.bean.HeadlineNewsBean;
import com.weather.net.GetNetPic;
import com.znufe.news.R;

import java.util.List;

/**
 * Created by Cui on 16-4-10.
 */
public class HeadLineNewsListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<HeadlineNewsBean> headlineNewsBeanList;
    private GetNetPic loadImage;

    public HeadLineNewsListAdapter(Context context,List<HeadlineNewsBean> headlineNewsBeanList){
        this.headlineNewsBeanList=headlineNewsBeanList;
        mInflater=LayoutInflater.from(context);
        loadImage=new GetNetPic();
    }
    @Override
    public int getCount() {
        return headlineNewsBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return headlineNewsBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView=null;
        if (rowView == null) {
            rowView=mInflater.inflate(R.layout.item_headline_news, null);
        }else {
            rowView=convertView;
        }

        TextView tv_title= (TextView) rowView.findViewById(R.id.tv_title);
        tv_title.setText(headlineNewsBeanList.get(position).getTitle());
        TextView tv_intro= (TextView) rowView.findViewById(R.id.tv_intro);
        tv_intro.setText(headlineNewsBeanList.get(position).getIntro());
        ImageView iv_newsPic= (ImageView) rowView.findViewById(R.id.iv_newsPic);
        String picUrl=headlineNewsBeanList.get(position).getPicUrl();
        iv_newsPic.setTag(picUrl);
        loadImage.asyLoadImage(iv_newsPic,picUrl);

        return rowView;
    }
}
