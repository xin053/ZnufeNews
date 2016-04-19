package com.weather.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.weather.bean.FutureWeatherBean;
import com.weather.bean.PMBean;
import com.weather.bean.WeatherBean;
import com.weather.net.HttpMethod;
import com.weather.net.NetConnection;
import com.weather.swiperefresh.PullToRefreshBase;
import com.weather.swiperefresh.PullToRefreshScrollView;
import com.weather.util.Config;
import com.znufe.news.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Cui on 16-4-18.
 */
public class WeatherFragment extends Fragment {

    private PullToRefreshScrollView mPullToRefreshScrollView;
    private ScrollView mScrollView;

    private TextView tv_city,// 城市
            tv_now_weather,// 天气
            tv_today_temp,// 温度
            tv_now_temp,// 当前温度
            tv_aqi,// 空气质量指数
            tv_quality,// 空气质量
            tv_today_temp_a,// 今天温度a
            tv_today_temp_b,// 今天温度b
            tv_tommorrow,// 明天
            tv_tommorrow_temp_a,// 明天温度a
            tv_tommorrow_temp_b,// 明天温度b
            tv_thirdday,// 第三天
            tv_thirdday_temp_a,// 第三天温度a
            tv_thirdday_temp_b,// 第三天温度b
            tv_fourthday,// 第四天
            tv_fourthday_temp_a,// 第四天温度a
            tv_fourthday_temp_b,// 第四天温度b
            tv_humidity,// 湿度
            tv_wind, tv_uv_index,// 紫外线指数
            tv_dressing_index;// 穿衣指数

    private ImageView iv_now_weather,// 现在
            iv_today_weather,// 今天
            iv_tommorrow_weather,// 明天
            iv_thirdday_weather,// 第三天
            iv_fourthday_weather;// 第四天

    private RelativeLayout rl_city;
    private String city="北京";
    private Activity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.weather_main, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        activity=getActivity();
        init();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 将字符串编码称utf-8字符串
     * @param str 需要编码的字符串
     * @return
     */
    private String urlEncode(String str){
        String encodedStr=null;
        try {
            encodedStr= URLEncoder.encode(str, Config.CHARSET);
            System.out.println("codetest:"+encodedStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedStr;
    }

    /**
     * 获得界面数据
     */
    private void getWeatherData(){

        city=urlEncode(city);
        System.out.println("cityEncoded:"+city);

        new NetConnection(Config.WEATHER_URL, HttpMethod.GET, new NetConnection.SuccessCallBack() {

            @Override
            public void onSuccess(String result) {
                try {
                    System.out.println(result);
                    JSONObject jsonObj=new JSONObject(result);
                    if (jsonObj.getInt("resultcode") == 200) {
                        WeatherBean weatherBean= parserWeatherData(jsonObj);
                        if (weatherBean != null) {
                            setWeatherView(weatherBean);
                        }else {
                            Toast.makeText(activity, "加载天气数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallBack() {

            @Override
            public void onFail() {
            }
        }, Config.KEY_WEATHER_FORMATE,"2",Config.KEY_WEATHER_CITYNAME,city,Config.KEY_USER_KEY,Config.VALUE_WEATHER_USER_KEY);
        new NetConnection(Config.QUALITY_URL, HttpMethod.GET, new NetConnection.SuccessCallBack() {

            @Override
            public void onSuccess(String result) {
                try {
                    System.out.println(result);
                    JSONObject jsonObj=new JSONObject(result);
                    if (jsonObj.getInt("resultcode") == 200) {
                        PMBean pmBean=parserPMData(jsonObj);
                        if (pmBean != null) {
                            setPMView(pmBean);
                        }else {
                            Toast.makeText(activity, "加载PM数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallBack() {

            @Override
            public void onFail() {
            }
        }, Config.KEY_QUALITY_CITY,city,Config.KEY_USER_KEY,Config.VALUE_QUALITY_USER_KEY);
    }

    /**
     * 初始化：绑定界面控件
     */
    private void init() {
        mPullToRefreshScrollView= (PullToRefreshScrollView) activity.findViewById(R.id.pull_to_refresh_scorllview);
        mPullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                getWeatherData();
                mPullToRefreshScrollView.onRefreshComplete();
            }
        });
        mScrollView=mPullToRefreshScrollView.getRefreshableView();

        getWeatherData();

        tv_city = (TextView) activity.findViewById(R.id.tv_city);
        tv_now_weather = (TextView) activity.findViewById(R.id.tv_now_weather);
        tv_today_temp = (TextView) activity.findViewById(R.id.tv_today_temp);
        tv_now_temp = (TextView) activity.findViewById(R.id.tv_now_temp);
        tv_aqi = (TextView) activity.findViewById(R.id.tv_aqi);
        tv_quality = (TextView) activity.findViewById(R.id.tv_quality);
        tv_today_temp_a = (TextView) activity.findViewById(R.id.tv_today_temp_a);
        tv_today_temp_b = (TextView) activity.findViewById(R.id.tv_today_temp_b);
        tv_tommorrow = (TextView) activity.findViewById(R.id.tv_tommorrow);
        tv_tommorrow_temp_a = (TextView) activity.findViewById(R.id.tv_tommorrow_temp_a);
        tv_tommorrow_temp_b = (TextView) activity.findViewById(R.id.tv_tommorrow_temp_b);
        tv_thirdday = (TextView) activity.findViewById(R.id.tv_thirdday);
        tv_thirdday_temp_a = (TextView) activity.findViewById(R.id.tv_thirdday_temp_a);
        tv_thirdday_temp_b = (TextView) activity.findViewById(R.id.tv_thirdday_temp_b);
        tv_fourthday = (TextView) activity.findViewById(R.id.tv_fourthday);
        tv_fourthday_temp_a = (TextView) activity.findViewById(R.id.tv_fourthday_temp_a);
        tv_fourthday_temp_b = (TextView) activity.findViewById(R.id.tv_fourthday_temp_b);
        tv_humidity = (TextView) activity.findViewById(R.id.tv_humidity);
        tv_wind = (TextView) activity.findViewById(R.id.tv_wind);
        tv_uv_index = (TextView) activity.findViewById(R.id.tv_uv_index);
        tv_dressing_index = (TextView) activity.findViewById(R.id.tv_dressing_index);

        iv_now_weather = (ImageView) activity.findViewById(R.id.iv_now_weather);
        iv_today_weather = (ImageView) activity.findViewById(R.id.iv_today_weather);
        iv_tommorrow_weather = (ImageView) activity.findViewById(R.id.iv_tommorrow_weather);
        iv_thirdday_weather = (ImageView) activity.findViewById(R.id.iv_thirdday_weather);
        iv_fourthday_weather = (ImageView) activity.findViewById(R.id.iv_fourthday_weather);

        rl_city= (RelativeLayout) activity.findViewById(R.id.rl_city);
        rl_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(activity,CityActivity.class);
                startActivityForResult(mIntent,1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1&&resultCode==1) {
            city=data.getStringExtra("city");
            System.out.println(city);
            getWeatherData();

        }
    }

    /**
     * 解析天气的json数据
     * @param json 需要解析的json数据
     * @return 返回一个经过解析的实体类
     */
    private WeatherBean parserWeatherData(JSONObject json){
        WeatherBean weatherBean=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        try {
            int code=json.getInt("resultcode");
            int error_code=json.getInt("error_code");
            if (error_code==0&&code==200)
            {
                JSONObject resultJson=json.getJSONObject("result");
                weatherBean=new WeatherBean();

                //today
                JSONObject todayJson=resultJson.getJSONObject("today");
                System.out.println(todayJson.getString("city"));
                weatherBean.setCity(todayJson.getString("city"));
                weatherBean.setUv_index(todayJson.getString("uv_index"));
                weatherBean.setTemp(todayJson.getString("temperature"));
                weatherBean.setWeather_id(todayJson.getJSONObject("weather_id").getString("fa"));
                weatherBean.setWeather_str(todayJson.getString("weather"));
                weatherBean.setDressing_index(todayJson.getString("dressing_index"));

                //sk
                JSONObject skJson=resultJson.getJSONObject("sk");
                weatherBean.setWind(skJson.getString("wind_direction"));
                weatherBean.setNow_temp(skJson.getString("temp"));
                weatherBean.setRelease(skJson.getString("time"));
                weatherBean.setHumidity(skJson.getString("humidity"));

                //future days
                JSONArray futureJsonArray=resultJson.getJSONArray("future");
                Date date=new Date(System.currentTimeMillis());//获得当前系统时间
                List<FutureWeatherBean> futureList=new ArrayList<FutureWeatherBean>();
                for (int i=0;i<futureJsonArray.length();i++)
                {
                    JSONObject futureJson=futureJsonArray.getJSONObject(i);
                    FutureWeatherBean futureWeatherBean=new FutureWeatherBean();
                    Date datef=sdf.parse(futureJson.getString("date"));
                    if (!datef.after(date))
                    {
                        continue;
                    }

                    futureWeatherBean.setTemp(futureJson.getString("temperature"));
                    futureWeatherBean.setWeek(futureJson.getString("week"));
                    futureWeatherBean.setWeather_id(futureJson.getJSONObject("weather_id").getString("fa"));
                    futureList.add(futureWeatherBean);
                    if (futureList.size()==3)
                    {
                        break;
                    }
                }
                weatherBean.setFutureWeatherList(futureList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weatherBean;

    }

    /**
     * 将解析出来的数据添加到相应控件上
     * @param weatherBeanParam
     */
    private void setWeatherView(WeatherBean weatherBeanParam){

        WeatherBean weatherBean=weatherBeanParam;
        tv_city.setText(weatherBean.getCity());
        tv_now_weather.setText(weatherBean.getWeather_str());

        String[] tempArr = weatherBean.getTemp().split("~");
        String temp_str_a = tempArr[1].substring(0, tempArr[1].indexOf("℃"));
        String temp_str_b = tempArr[0].substring(0, tempArr[0].indexOf("℃"));
        // 温度 8℃~16℃" ↑ ↓ °
        tv_today_temp.setText("  ↑ " + temp_str_a + "°   ↓" + temp_str_b + "°");
        tv_now_temp.setText(weatherBean.getNow_temp() + " °");
        iv_today_weather.setImageResource(getResources().getIdentifier("d" + weatherBean.getWeather_id(), "drawable", "com.znufe.news"));

        tv_today_temp_a.setText(temp_str_a + "°");
        tv_today_temp_b.setText(temp_str_b + "°");

        List<FutureWeatherBean> futureList=weatherBean.getFutureWeatherList();
        if (futureList.size()== 3) {
            setFutureData(tv_tommorrow,iv_tommorrow_weather,tv_tommorrow_temp_a,tv_tommorrow_temp_b,futureList.get(0));
            setFutureData(tv_thirdday,iv_thirdday_weather,tv_thirdday_temp_a,tv_thirdday_temp_b,futureList.get(1));
            setFutureData(tv_fourthday,iv_fourthday_weather,tv_fourthday_temp_a,tv_fourthday_temp_b,futureList.get(2));
        }
        Calendar c = Calendar.getInstance();
        int time = c.get(Calendar.HOUR_OF_DAY);
        String prefixStr = null;
        if (time >= 6 && time < 18) {
            prefixStr = "d";
        } else {
            prefixStr = "n";
        }
        iv_now_weather.setImageResource(getResources().getIdentifier(prefixStr + weatherBean.getWeather_id(), "drawable", "com.znufe.news"));

        tv_humidity.setText(weatherBean.getHumidity());
        tv_wind.setText(weatherBean.getWind());
        tv_uv_index.setText(weatherBean.getUv_index());
        tv_dressing_index.setText(weatherBean.getDressing_index());
    }
    private void setFutureData(TextView tv_week, ImageView iv_weather, TextView tv_temp_a, TextView tv_temp_b, FutureWeatherBean bean) {
        tv_week.setText(bean.getWeek());
        iv_weather.setImageResource(getResources().getIdentifier("d" + bean.getWeather_id(), "drawable", "com.znufe.news"));
        String[] tempArr = bean.getTemp().split("~");
        String temp_str_a = tempArr[1].substring(0, tempArr[1].indexOf("℃"));
        String temp_str_b = tempArr[0].substring(0, tempArr[0].indexOf("℃"));
        tv_temp_a.setText(temp_str_a + "°");
        tv_temp_b.setText(temp_str_b + "°");

    }

    /**
     * 解析空气质量的json数据
     * @param json
     * @return
     */
    private PMBean parserPMData(JSONObject json){
        PMBean pmBean=null;

        try {
            int result_code=json.getInt("resultcode");
            int error_code=json.getInt("error_code");
            if (error_code == 0&&result_code==200) {
                pmBean=new PMBean();
                JSONArray resultJSON=json.getJSONArray("result");
                JSONObject cityNowJSON=resultJSON.getJSONObject(0).getJSONObject("citynow");
                pmBean.setAqi(cityNowJSON.getString("AQI"));
                pmBean.setQuality(cityNowJSON.getString("quality"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pmBean;

    }

    /**
     * 将数据添加到空气质量的界面控件上
     * @param pmBean
     */
    private void setPMView(PMBean pmBean){
        tv_aqi.setText(pmBean.getAqi());
        tv_quality.setText(pmBean.getQuality());

    }
}
