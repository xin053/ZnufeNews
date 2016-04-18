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
                citys="����&" +
                        "����&" +
                        "����&" +
                        "��ɽ&" +
                        "��˳&" +
                        "����&" +
                        "�׳�&" +
                        "��ɫ&" +
                        "��ɽ&" +
                        "����&" +
                        "����&" +
                        "��ͷ&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "��Ϫ&" +
                        "�Ͻ�&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "��ɳ&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "�е�&" +
                        "�ɶ�&" +
                        "����&" +
                        "���&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "��ͬ&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "��ݸ&" +
                        "��Ӫ&" +
                        "������˹&" +
                        "����&" +
                        "��ɽ&" +
                        "����&" +
                        "��˳&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "��Ԫ&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "������&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "�Ϸ�&" +
                        "��ˮ&" +
                        "����&" +
                        "��Դ&" +
                        "����&" +
                        "����&" +
                        "�����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "�Ƹ�&" +
                        "��ɽ&" +
                        "��ʯ&" +
                        "���ͺ���&" +
                        "����&" +
                        "��«��&" +
                        "���ױ���&" +
                        "����&" +
                        "��ľ˹&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "������&" +
                        "����&" +
                        "����&" +
                        "��&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "�Ž�&" +
                        "����&" +
                        "����&" +
                        "�����&" +
                        "����&" +
                        "��ɽ&" +
                        "��ܽ&" +
                        "�ȷ�&" +
                        "����&" +
                        "��ɽ&" +
                        "���Ƹ�&" +
                        "�ĳ�&" +
                        "����&" +
                        "��Դ&" +
                        "����&" +
                        "�ٲ�&" +
                        "�ٷ�&" +
                        "��ˮ&" +
                        "����ˮ&" +
                        "����&" +
                        "����&" +
                        "¦��&" +
                        "����&" +
                        "�޶�&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "��ɽ&" +
                        "ï��&" +
                        "üɽ&" +
                        "÷��&" +
                        "����&" +
                        "ĵ����&" +
                        "�ϲ�&" +
                        "�ϳ�&" +
                        "�Ͼ�&" +
                        "����&" +
                        "��ƽ&" +
                        "��ͨ&" +
                        "����&" +
                        "�ڽ�&" +
                        "����&" +
                        "����&" +
                        "�̽�&" +
                        "��֦��&" +
                        "ƽ��ɽ&" +
                        "Ƽ��&" +
                        "�ն�&" +
                        "����&" +
                        "�ൺ&" +
                        "��Զ&" +
                        "�ػʵ�&" +
                        "����&" +
                        "�������&" +
                        "Ȫ��&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "�Ϻ�&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "��ͷ&" +
                        "�ع�&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "ʯ����&" +
                        "ʯ��ׯ&" +
                        "ʮ��&" +
                        "ʯ��ɽ&" +
                        "ʯʨ&" +
                        "��ƽ&" +
                        "��ԭ&" +
                        "����&" +
                        "�绯&" +
                        "����&" +
                        "��Ǩ&" +
                        "����&" +
                        "�ذ�&" +
                        "̫ԭ&" +
                        "����&" +
                        "̨��&" +
                        "��ɽ&" +
                        "���&" +
                        "��ˮ&" +
                        "����&" +
                        "ͨ��&" +
                        "ͨ��&" +
                        "ͭ��&" +
                        "�人&" +
                        "Ϋ��&" +
                        "����&" +
                        "μ��&" +
                        "��ɽ��&" +
                        "����&" +
                        "�ں�&" +
                        "�ߺ�&" +
                        "�����첼&" +
                        "��³ľ��&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "��̶&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "Т��&" +
                        "����&" +
                        "��̨&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "���&" +
                        "����&" +
                        "�Ű�&" +
                        "�Ӱ�&" +
                        "�γ�&" +
                        "����&" +
                        "����&" +
                        "�Ӽ�&" +
                        "��̨&" +
                        "�˱�&" +
                        "�˲�&" +
                        "�˴�&" +
                        "����&" +
                        "Ӫ��&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "����&" +
                        "�˳�&" +
                        "��Ϫ&" +
                        "��ׯ&" +
                        "�żҽ�&" +
                        "�żҿ�&" +
                        "����&" +
                        "տ��&" +
                        "����&" +
                        "��ͨ&" +
                        "֣��&" +
                        "��&" +
                        "��ɽ&" +
                        "�ܿ�&" +
                        "��ɽ&" +
                        "�麣&" +
                        "����&" +
                        "����&" +
                        "�Ͳ�&" +
                        "�Թ�&" +
                        "����&" +
                        "����&";

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
