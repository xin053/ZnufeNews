package com.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.znufe.news.R;

import java.util.List;

/**
 * Created by Cui on 16-4-17.
 */
public class CityListAdapter extends BaseAdapter {

    private List<String> cityList;
    private LayoutInflater mInflater;
    public CityListAdapter(Context context,List<String> cityList){
        this.cityList=cityList;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView=null;
        if (rowView == null) {
            rowView=mInflater.inflate(R.layout.item_city, null);
        }else {
            rowView=convertView;
        }

        TextView tv_city= (TextView) rowView.findViewById(R.id.tv_city);
        tv_city.setText(cityList.get(position));
        return rowView;
    }
}
