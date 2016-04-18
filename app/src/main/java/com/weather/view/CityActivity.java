package com.weather.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.weather.adapter.CityListAdapter;
import com.znufe.news.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Cui on 16-4-17.
 */
public class CityActivity extends Activity {

        private ListView cityListView;
        private CityListAdapter cityListAdapter;
        private String citys;
        private List<String> cityListCollection;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_city);

                initView();
                getCity();
        }

        private void initView() {
                findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                finish();
                        }
                });

                cityListView = (ListView) findViewById(R.id.lv_city);
        }

        private void getCity(){
                citys="安康&" +
                        "安宁&" +
                        "安庆&" +
                        "鞍山&" +
                        "安顺&" +
                        "安阳&" +
                        "白城&" +
                        "百色&" +
                        "白山&" +
                        "保定&" +
                        "宝鸡&" +
                        "包头&" +
                        "北海&" +
                        "北京&" +
                        "蚌埠&" +
                        "本溪&" +
                        "毕节&" +
                        "滨州&" +
                        "毫州&" +
                        "沧州&" +
                        "长春&" +
                        "常德&" +
                        "昌吉&" +
                        "长沙&" +
                        "长治&" +
                        "常州&" +
                        "巢湖&" +
                        "朝阳&" +
                        "潮州&" +
                        "承德&" +
                        "成都&" +
                        "郴州&" +
                        "赤峰&" +
                        "池州&" +
                        "重庆&" +
                        "崇左&" +
                        "滁州&" +
                        "大理&" +
                        "大连&" +
                        "丹东&" +
                        "大庆&" +
                        "大同&" +
                        "达州&" +
                        "德阳&" +
                        "德州&" +
                        "东莞&" +
                        "东营&" +
                        "鄂尔多斯&" +
                        "鄂州&" +
                        "佛山&" +
                        "抚州&" +
                        "抚顺&" +
                        "阜新&" +
                        "阜阳&" +
                        "福州&" +
                        "赣州&" +
                        "广元&" +
                        "广州&" +
                        "贵阳&" +
                        "桂林&" +
                        "哈尔滨&" +
                        "海口&" +
                        "邯郸&" +
                        "杭州&" +
                        "汉中&" +
                        "合肥&" +
                        "衡水&" +
                        "衡阳&" +
                        "河源&" +
                        "菏泽&" +
                        "贺州&" +
                        "红河州&" +
                        "淮安&" +
                        "淮北&" +
                        "淮南&" +
                        "黄冈&" +
                        "黄山&" +
                        "黄石&" +
                        "呼和浩特&" +
                        "惠州&" +
                        "葫芦岛&" +
                        "呼伦贝尔&" +
                        "湖州&" +
                        "佳木斯&" +
                        "吉安&" +
                        "江门&" +
                        "焦作&" +
                        "嘉兴&" +
                        "揭阳&" +
                        "吉林&" +
                        "济南&" +
                        "景德镇&" +
                        "荆门&" +
                        "荆州&" +
                        "金华&" +
                        "济宁&" +
                        "晋江&" +
                        "晋中&" +
                        "锦州&" +
                        "吉首&" +
                        "九江&" +
                        "开封&" +
                        "凯里&" +
                        "库尔勒&" +
                        "昆明&" +
                        "昆山&" +
                        "莱芙&" +
                        "廊坊&" +
                        "兰州&" +
                        "乐山&" +
                        "连云港&" +
                        "聊城&" +
                        "辽阳&" +
                        "辽源&" +
                        "丽江&" +
                        "临沧&" +
                        "临汾&" +
                        "丽水&" +
                        "六盘水&" +
                        "柳州&" +
                        "龙岩&" +
                        "娄底&" +
                        "六安&" +
                        "罗定&" +
                        "洛阳&" +
                        "泸州&" +
                        "吕梁&" +
                        "马鞍山&" +
                        "茂名&" +
                        "眉山&" +
                        "梅州&" +
                        "绵阳&" +
                        "牡丹江&" +
                        "南昌&" +
                        "南充&" +
                        "南京&" +
                        "南宁&" +
                        "南平&" +
                        "南通&" +
                        "南阳&" +
                        "内江&" +
                        "宁波&" +
                        "宁德&" +
                        "盘锦&" +
                        "攀枝花&" +
                        "平顶山&" +
                        "萍乡&" +
                        "普洱&" +
                        "莆田&" +
                        "青岛&" +
                        "清远&" +
                        "秦皇岛&" +
                        "钦州&" +
                        "齐齐哈尔&" +
                        "泉州&" +
                        "曲靖&" +
                        "衢州&" +
                        "日照&" +
                        "汝州&" +
                        "三明&" +
                        "三亚&" +
                        "上海&" +
                        "商洛&" +
                        "商丘&" +
                        "上饶&" +
                        "汕头&" +
                        "韶关&" +
                        "绍兴&" +
                        "邵阳&" +
                        "沈阳&" +
                        "深圳&" +
                        "石河子&" +
                        "石家庄&" +
                        "十堰&" +
                        "石嘴山&" +
                        "石狮&" +
                        "四平&" +
                        "松原&" +
                        "宿州&" +
                        "绥化&" +
                        "遂宁&" +
                        "宿迁&" +
                        "苏州&" +
                        "秦安&" +
                        "太原&" +
                        "秦州&" +
                        "台州&" +
                        "唐山&" +
                        "天津&" +
                        "天水&" +
                        "铁岭&" +
                        "通化&" +
                        "通辽&" +
                        "铜陵&" +
                        "武汉&" +
                        "潍坊&" +
                        "威海&" +
                        "渭南&" +
                        "文山州&" +
                        "温州&" +
                        "乌海&" +
                        "芜湖&" +
                        "乌兰察布&" +
                        "乌鲁木齐&" +
                        "无锡&" +
                        "吴忠&" +
                        "梧州&" +
                        "厦门&" +
                        "西安&" +
                        "湘潭&" +
                        "襄阳&" +
                        "咸宁&" +
                        "咸阳&" +
                        "孝感&" +
                        "西昌&" +
                        "邢台&" +
                        "西宁&" +
                        "新乡&" +
                        "信阳&" +
                        "新余&" +
                        "忻州&" +
                        "宣城&" +
                        "许昌&" +
                        "徐州&" +
                        "雅安&" +
                        "延安&" +
                        "盐城&" +
                        "阳江&" +
                        "扬州&" +
                        "延吉&" +
                        "烟台&" +
                        "宜宾&" +
                        "宜昌&" +
                        "宜春&" +
                        "银川&" +
                        "营口&" +
                        "义乌&" +
                        "益阳&" +
                        "宜州&" +
                        "永州&" +
                        "玉林&" +
                        "岳阳&" +
                        "榆林&" +
                        "运城&" +
                        "玉溪&" +
                        "枣庄&" +
                        "张家界&" +
                        "张家口&" +
                        "漳州&" +
                        "湛江&" +
                        "肇庆&" +
                        "昭通&" +
                        "郑州&" +
                        "镇江&" +
                        "中山&" +
                        "周口&" +
                        "舟山&" +
                        "珠海&" +
                        "涿州&" +
                        "株洲&" +
                        "淄博&" +
                        "自贡&" +
                        "资阳&" +
                        "遵义&";

                cityListCollection=new ArrayList<String>();
                Set<String> citySetCollection=new HashSet<String>();
                int start=0,end=0;
                String tempStr=null;
                for (int i=0;i<citys.length();i++)
                {
                        if (citys.charAt(i)=='&'){
                                end=i;
                                tempStr=citys.substring(start,end);
                                citySetCollection.add(tempStr);
                                System.out.print(" start:"+start+" end:"+end+" tempStr:"+tempStr);
                                start=i+1;
                        }
                }
                cityListCollection.addAll(citySetCollection);
                cityListAdapter=new CityListAdapter(CityActivity.this,cityListCollection);
                cityListView.setAdapter(cityListAdapter);
                cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent();
                                intent.putExtra("city", cityListCollection.get(position));
                                setResult(1, intent);
                                finish();
                        }
                });

        }
}
