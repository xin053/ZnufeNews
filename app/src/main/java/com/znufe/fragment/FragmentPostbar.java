package com.znufe.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.znufe.adapter.PostbarListViewAdapter;
import com.znufe.domain.Postbar;
import com.znufe.netservice.GetPostbarService;
import com.znufe.news.R;

import java.util.List;

public class FragmentPostbar extends Fragment implements OnScrollListener,OnItemClickListener{
	private Activity activity;
	private ListView listView;
	private PostbarListViewAdapter adapter;
	private boolean isLoading = true; 
	private int page = 0;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_postbar, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		activity = getActivity();
		adapter = new PostbarListViewAdapter(activity,null);
		listView = (ListView)activity.findViewById(R.id.fragment_postbar_listview);
		//listView.addFooterView(View.inflate(activity, R.layout.foot, null));
		listView.setAdapter(adapter);
		listView.setOnScrollListener(this);
		listView.setOnItemClickListener(this);
		new MyAsyncTaskGetPoatbar().execute(0);
		super.onActivityCreated(savedInstanceState);
	}

	/**
	 * �첽��ȡ�����б���
	 */
	public class MyAsyncTaskGetPoatbar extends AsyncTask<Integer, String, List<Postbar>>{
		@Override
		protected List<Postbar> doInBackground(Integer... pages) {
			List<Postbar> postbars = GetPostbarService.getPostbarsByPage("�ȸ�",pages[0]);
			return postbars;
		}
		@Override
		protected void onPostExecute(List<Postbar> newPostbars) {
		
			if(newPostbars!=null&&newPostbars.size()>0){
				page ++ ;
				adapter.addPostbars(newPostbars);
				adapter.notifyDataSetChanged();
			}
			isLoading = false;
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if(totalItemCount<=firstVisibleItem+visibleItemCount+1&&!isLoading){
			new MyAsyncTaskGetPoatbar().execute(page);
			isLoading = true;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(activity, "������item", Toast.LENGTH_SHORT).show();
	}
}
