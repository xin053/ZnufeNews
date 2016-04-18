package com.znufe.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.znufe.domain.TechNews;
import com.znufe.netutil.GetBitmapUtil;
import com.znufe.news.R;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TechNewsListViewAdapter extends BaseAdapter{

	private List<TechNews> techNewses;
	private Context context;
	private Map<String, SoftReference<Bitmap>> map = new HashMap<String, SoftReference<Bitmap>>();

	public TechNewsListViewAdapter(Context context, List<TechNews> techNewses) {
		super();
		this.techNewses = techNewses;
		if(techNewses ==null){
			this.techNewses = new ArrayList<TechNews>();
		}
		this.context = context;
	}

	public void addTechNews(List<TechNews> newTechNewses){
		this.techNewses.addAll(newTechNewses);
	}

	public List<TechNews> getTechNewses() {
		return techNewses;
	}

	@Override
	public int getCount() {
		return techNewses.size();
	}

	@Override
	public Object getItem(int position) {
		return techNewses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TechNews techNews = techNewses.get(position);
		if(convertView == null){
			convertView = View.inflate(context, R.layout.listview_fragment_tech, null);
		}
		getTextViewByViewAndId(convertView, R.id.fragment_techNews_listview_title).setText(techNews.getTitle());
		getTextViewByViewAndId(convertView, R.id.fragment_techNews_listview_details).setText((techNews.getLongTitle()));
		
		if(techNews.isFocus())
		{
			getImageViewByViewAndId(convertView, R.id.fragment_techNews_listview_hot).setVisibility(View.VISIBLE);
		}

		//异步处理图片
		ImageView imageView = (ImageView)convertView.findViewById(R.id.fragment_techNews_listview_photo);
		imageView.setImageResource(R.drawable.img_temp);
		imageView.setTag(techNews.getKpic());

		if(TextUtils.isEmpty(techNews.getKpic())){
			imageView.setVisibility(View.GONE);
		}else{
			imageView.setVisibility(View.VISIBLE);
			if(map.get(techNews.getKpic())!=null&&map.get(techNews.getKpic()).get()!=null){
				imageView.setImageBitmap(map.get(techNews.getKpic()).get());
			}else{
				MyAsyncTaskGetBitmap myAsyncTaskGetBitmap = new MyAsyncTaskGetBitmap();
				myAsyncTaskGetBitmap.targetUrl = techNews.getKpic();
				myAsyncTaskGetBitmap.imageView = imageView;
				myAsyncTaskGetBitmap.execute("");
			}
		}
		
		
		getTextViewByViewAndId(convertView, R.id.fragment_techNews_listview_date).setText(techNews.getPubDate());
		getTextViewByViewAndId(convertView, R.id.fragment_techNews_listview_reply_Num).setText(techNews.getComment());
		getTextViewByViewAndId(convertView, R.id.fragment_techNews_listview_agree_num).setText(String.valueOf(techNews.getCommentCountInfo().getPraise()));
        
		return convertView;
	}


	public class MyAsyncTaskGetBitmap extends AsyncTask<String, String, Bitmap>
	{
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
	
	public TextView getTextViewByViewAndId(View view,int id){
		return (TextView)view.findViewById(id);
	}

	public ImageView getImageViewByViewAndId(View view,int id){
		return (ImageView)view.findViewById(id);
	}
}
