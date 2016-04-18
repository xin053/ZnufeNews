package com.weather.net;

import android.os.AsyncTask;

import com.weather.util.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class NetConnection {
	/**
	 *
	 * @param url ��ַ
	 * @param method post/get
	 * @param successCallBack
	 * @param failCallBack
	 * @param kvs ��ַ�еĸ��Ӳ���
	 */
	public NetConnection(final String url,final HttpMethod method,final SuccessCallBack successCallBack,final FailCallBack failCallBack,final String ... kvs){

		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {

				//ȷ�����Ӳ����Ĵ����ʽ
				StringBuffer paramsStr=new StringBuffer();
				if(kvs.length!=0){
					for (int i = 0; i < kvs.length; i+=2) {
						paramsStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
					}
				}

				try {
					URLConnection uc;

					switch (method) {
					case POST:
						uc=new URL(url).openConnection();
						uc.setDoOutput(true);//����������
						BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
						bw.write(paramsStr.toString());
						break;
					default:
						String newurl;
						if(kvs.length!=0) {
							newurl = url + "?" + paramsStr.toString().substring(0, paramsStr.toString().lastIndexOf("&"));
						}else{
							newurl=url;
						}
						uc=new URL(newurl).openConnection();
						break;
					}

					System.out.println("Request URL:"+uc.getURL());
					System.out.println("Request data:"+paramsStr);

					BufferedReader br=new BufferedReader(new InputStreamReader(uc.getInputStream(), Config.CHARSET));
					String line=null;
					StringBuffer result=new StringBuffer();
					while ((line=br.readLine())!=null) {
						result.append(line);
					}
					return result.toString();

				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				if (result!=null) {
					if (successCallBack!=null) {
						successCallBack.onSuccess(result);
					}
				} else {

					if (failCallBack!=null) {
						failCallBack.onFail();
					}
				}
				super.onPostExecute(result);
			}
		}.execute();
	}

	public static interface SuccessCallBack{//ͨ���ӿ���֪ͨ����ʱ�򽫴ӷ��������ܵ�json���ݴ��ݳ�ȥ
		void onSuccess(String result);
	}

	public static interface FailCallBack{
		void onFail();
	}

}
