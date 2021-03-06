package com.znufe.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.znufe.adapter.TechNewsListViewAdapter;
import com.znufe.domain.TechNews;
import com.znufe.netservice.GetTechNewsService;
import com.znufe.news.R;
import com.znufe.news.TechNewsDetailsActivity;

import java.util.List;

public class FragmentTechNews extends Fragment implements OnItemClickListener{

	private Activity activity;
	private ListView listView;
	private TechNewsListViewAdapter adapter ;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_tech, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		activity = getActivity();
		listView = (ListView)activity.findViewById(R.id.fragment_technews_listview); 
		listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener()
		{
			@Override
			public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo)
			{
				contextMenu.add(0, 0, 0, "共享");
			}
		});
		adapter = new TechNewsListViewAdapter(activity, null);
        //正在加载view
		//listView.addFooterView(View.inflate(activity, R.layout.foot, null));
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		new MyAsnycTaskGetQuestion().execute();
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int id = (int) info.id;
		TechNews techNews = adapter.getTechNewses().get(id);

		switch (item.getItemId()) {
			case 0:
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT,getUrl(techNews.getId(),techNews.getPubDate()));
				sendIntent.setType("text/plain");
				startActivity(Intent.createChooser(sendIntent,getResources().getText(R.string.send_to)));
				break;
			
			default:
				break;
		}

		return super.onContextItemSelected(item);

	}

	/**
	 * 异步获取科技新闻列表类
	 */
	public class MyAsnycTaskGetQuestion extends AsyncTask<Void, String, List<TechNews>>{
		@Override
		protected List<TechNews> doInBackground(Void... voids) {
			List<TechNews> techNewses = GetTechNewsService.getTechNews();
			return techNewses;
		}

        @Override
		protected void onPostExecute(List<TechNews> techNewses) {

            adapter.addTechNews(techNewses);

			super.onPostExecute(techNewses);
		}
		
	}
    
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
			Intent intent = new Intent(activity, TechNewsDetailsActivity.class);
			TechNews techNews = adapter.getTechNewses().get(position);
			intent.putExtra("url", getUrl(techNews.getId(),techNews.getPubDate()));
			startActivity(intent);
	}
	
	public String getUrl(String id,String date)
	{
		String url = "http://tech.sina.com.cn/i/"+date.split(" ")[0]+"/doc-i"+id.split("-")[0]+".shtml";
		url = "http://sinanews.sina.cn/sharenews.shtml?id="+id;
		return url;
	}
}
