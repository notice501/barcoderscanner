package com.notice.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity{
	private WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.webbuy);
		setProgressBarVisibility(true);
		final Activity activity = this;
		wv = (WebView) findViewById(R.id.wv);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setWebChromeClient(new WebChromeClient() {
			   public void onProgressChanged(WebView view, int progress) {
			     // Activities and WebViews measure progress with different scales.
			     // The progress meter will automatically disappear when we reach 100%
				   activity.setProgress(progress * 1000);
			   }
			 });
		wv.setWebViewClient(new WebViewClient() {
			   public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			     Toast.makeText(WebViewActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
			   }
			 });
		Intent intent = getIntent();
		String type = intent.getStringExtra("type");
		String value = intent.getStringExtra("value");
		if(type.equals("dang")){
			wv.loadUrl("http://m.dangdang.com/gw_search.php?key=" + value);
		}
		else if(type.equals("360")) {
			wv.loadUrl("http://m.360buy.com/ware/search.action?keyword=" + value);
		}
		else if(type.equals("taobao")){
			wv.loadUrl("http://s.m.taobao.com/search.htm?q=" + value);
		}
		else {
			wv.loadUrl("http://s.m.etao.com/auctions.htm?q=" + value);
		}
	}

}
