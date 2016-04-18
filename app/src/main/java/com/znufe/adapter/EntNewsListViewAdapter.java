package com.znufe.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.znufe.domain.EntNews;
import com.znufe.netutil.GetBitmapUtil;
import com.znufe.news.R;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by JiangQun on 2016/3/17.
 */
public class EntNewsListViewAdapter extends BaseAdapter {

    private List<EntNews> newss;
    private Context context;
    private Map<String, SoftReference<Bitmap>> map = new HashMap<String, SoftReference<Bitmap>>();


    public EntNewsListViewAdapter(Context context,List<EntNews> news) {
        super();
        this.context = context;
        if(newss==null){
            newss = new ArrayList<EntNews>();
        }else{
            this.newss = news;
        }
    }

    public void addEntNews(List<EntNews> newNewss) {
        newss.addAll(newNewss);
    }
    public List<EntNews> getEntNewss() {
        return newss;
    }
    @Override
    public int getCount() {
        return newss.size();
    }

    @Override
    public Object getItem(int position) {
        return newss.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EntNews news = newss.get(position);
        Log.i("Adapter", Integer.toString(position));
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.listview_fragment_ent, null);
        }
        try {
            ((TextView) convertView.findViewById(R.id.fragment_entNews_listview_title)).setText(news.getTitle());
            ((TextView) convertView.findViewById(R.id.fragment_entNews_listview_introduce)).setText(news.getIntroduce());

            Log.i("Adapter", position+news.getIs_focus().toString());
            if (news.getIs_focus()) {
                (convertView.findViewById(R.id.fragment_entNews_listview_hot)).setVisibility(View.VISIBLE);
            }

            ((TextView) convertView.findViewById(R.id.fragment_entNews_listview_date)).setText(news.getPubDate());
            ((TextView) convertView.findViewById(R.id.fragment_entNews_listview_reply_Num)).setText(Integer.toString(news.getComment()));

            if(news.getComment_count_info()!=null)
                ((TextView) convertView.findViewById(R.id.fragment_entNews_listview_agree_num)).setText(String.valueOf(news.getComment_count_info().getPraise()));

            //异步处理图片
            ImageView imageView = (ImageView) convertView.findViewById(R.id.fragment_entNews_listview_photo);
            imageView.setImageResource(R.drawable.img_temp);
            imageView.setTag(news.getPicLink());

            if (TextUtils.isEmpty(news.getPicLink())) {
                imageView.setVisibility(View.GONE);
            } else {
                imageView.setVisibility(View.VISIBLE);
                if (map.get(news.getPicLink()) != null && map.get(news.getPicLink()).get() != null) {
                    imageView.setImageBitmap(map.get(news.getPicLink()).get());
                } else {
                    MyAsyncTaskGetBitmap myAsyncTaskGetBitmap = new MyAsyncTaskGetBitmap();
                    myAsyncTaskGetBitmap.targetUrl = news.getPicLink();
                    myAsyncTaskGetBitmap.imageView = imageView;
                    myAsyncTaskGetBitmap.execute("");
                }
            }
        }catch(Exception e)
        {
            Log.i("Adapter", e.toString());
            e.printStackTrace();
        }

        return convertView;
    }


    /**
     * 异步获取新闻列表里面的图片
     * @author
     *
     */
    public class MyAsyncTaskGetBitmap extends AsyncTask<String, String, Bitmap> {
        ImageView imageView ;
        //照片标志，由于imageview的重用，可能被其他item占用，导致获取的imageview的图片和item不和。这里设一个标志如果tag和imageView.getTag()相同说明该imagevIEW还没有被重用
        String targetUrl;
        @Override
        protected Bitmap doInBackground(String... imageViews) {
            Bitmap bitmap = GetBitmapUtil.getBitmapByUrl(targetUrl);
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            map.put(targetUrl, new SoftReference<Bitmap>(bitmap));
            if(imageView.getTag().equals(targetUrl)){
                imageView.setImageBitmap(bitmap);
            }
        }

    }
}
