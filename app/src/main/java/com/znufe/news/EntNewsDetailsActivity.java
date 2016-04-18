package com.znufe.news;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.znufe.util.FileUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.lang.reflect.Method;

public class EntNewsDetailsActivity extends Activity implements OnTouchListener{

    private String s = "";
    private WebView webView;
    private float startX;
    private float startY;
    private RelativeLayout relativeLayout;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                relativeLayout.setVisibility(View.GONE);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setBlockNetworkImage(false);
                webView.setVisibility(View.VISIBLE);
                FileUtil.addFile(msg.obj.toString());
                s = msg.obj.toString();
                Log.i("Details", s);
                webView.loadDataWithBaseURL(null, msg.obj.toString(), "text/html", "utf-8",null);
            }

        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        String url = getIntent().getStringExtra("url");

        relativeLayout = (RelativeLayout)findViewById(R.id.activity_news_details_relative_is_loading);

        webView = (WebView)findViewById(R.id.news_details_webview);
        webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setBlockNetworkImage(true);
        webView.loadUrl(url);
        webView.setOnTouchListener(this);
        relativeLayout.setOnTouchListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * javascript坚挺函数
     * @author linpeng123l
     *
     */
    final class InJavaScriptLocalObj{
        private boolean isLoaded = false;
        /**
         * 将取得的html中不需要的内容去掉
         * @param html
         */
        @JavascriptInterface
        public void showSource(String html) {
            if(!isLoaded){
                Document document = Jsoup.parse(html);
                Elements elements = document.getElementsByClass("page-view-article");
                elements.remove(elements.select(".img-eye"));
                elements.select(".img-eye").remove();
                Message message = new Message();
                message.obj = document.head()+elements.toString();
                message.what = 1;
                handler.sendMessage(message);
                isLoaded = true;
            }
        }
    }

    /*
     * 监听返回按钮
     */
    public void back(View view){
        finish();
    }


    /**
     * webview监听函数
     * @author linpeng123l
     *
     */
    final class MyWebViewClient extends WebViewClient{
        public int count;

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript:window.local_obj.showSource('<head>'+" +
                    "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            super.onPageFinished(view, url);
        }
    }

    /**
     * 左滑动返回监听
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                float endY = event.getY();
                if(Math.abs(endX-startX)>150&&((endY-startY)==0||Math.abs((endX-startX)/(endY-startY))>2)){
                    finish();
                }
                break;
            default:
                break;
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.entnews_details, menu);
        return true;
    }
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(intent, getTitle()));
                break;

            default:
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

}
