package com.weather.adapter;

import android.content.Context;
import android.text.TextUtils;
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

    public List<HeadlineNewsBean> getHeadlineNewsBeanList(){
        return headlineNewsBeanList;
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

        TextView tv_title= (TextView) rowView.findViewById(R.id.tv_headline_news_title);
        TextView tv_intro= (TextView) rowView.findViewById(R.id.tv_headline_news_introduce);
        if (headlineNewsBeanList.get(position).getIs_focus()) {
            rowView.findViewById(R.id.iv_headline_news_hot).setVisibility(View.VISIBLE);
            rowView.findViewById(R.id.headline_news_divide1).setVisibility(View.VISIBLE);
        }
        TextView tv_pubdate= (TextView) rowView.findViewById(R.id.tv_headline_news_date);
        TextView tv_comment= (TextView) rowView.findViewById(R.id.tv_headline_news_reply_Num);
        TextView tv_praise= (TextView) rowView.findViewById(R.id.tv_headline_news_agree_num);
        ImageView iv_newsPic= (ImageView) rowView.findViewById(R.id.iv_headline_news_photo);
        tv_title.setText(headlineNewsBeanList.get(position).getTitle());
        tv_intro.setText(headlineNewsBeanList.get(position).getIntroduce());
        tv_pubdate.setText(headlineNewsBeanList.get(position).getPubDate());
        tv_comment.setText(headlineNewsBeanList.get(position).getComment()+"");

        if (headlineNewsBeanList.get(position).getComment_count_info() != null) {
            tv_praise.setText(headlineNewsBeanList.get(position).getComment_count_info().getPraise() + "");
        }
        if (TextUtils.isEmpty(headlineNewsBeanList.get(position).getPicLink())) {
            iv_newsPic.setVisibility(View.GONE);
        }else {
            String picUrl = headlineNewsBeanList.get(position).getPicLink();
            iv_newsPic.setTag(picUrl);
            loadImage.asyLoadImage(iv_newsPic, picUrl);
        }

        return rowView;
    }
}
