package com.znufe.news;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebView;

import com.znufe.netservice.NetService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Cui on 16-4-19.
 */
public class HeadlineNewsDetailsAcitivty extends Activity {

    private WebView webView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String html = (String)msg.obj;
            html = NetService.getHtml(html);
            webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headline_news_details);

        final String url = getIntent().getStringExtra("url");
        webView = (WebView) findViewById(R.id.headline_news_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setInitialScale(39);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    URL url1 = new URL(url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url1.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);

                    StringBuffer stringBuffer = new StringBuffer();
                    if(httpURLConnection.getResponseCode() == 200)
                    {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");

                        char []buffer = new char[1024];

                        int len = 0;

                        while((len = inputStreamReader.read(buffer))!=-1)
                        {
                            stringBuffer.append(new String(buffer,0,len));
                        }

//                    Log.i("web","1"+stringBuffer.toString());
                        Message message  = new Message();
                        message.obj = stringBuffer.toString();
                        handler.sendMessage(message);
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
