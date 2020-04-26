package com.example.googletrends;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.graphics.Bitmap;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private WebView webV;
    private ProgressBar spinner;
    private TextView txt;
    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webV = (WebView) findViewById(R.id.webView);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        txt = (TextView) findViewById(R.id.textView);
        webV.setWebViewClient(new CustomWebViewClient());

        WebSettings webSettings = webV.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webV.loadUrl("http://trends.google.com/trends/");
        webV.setWebViewClient(new WebViewClient());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                spinner.setVisibility(View.GONE);
                txt.setVisibility(View.GONE);
            }
        }, 8000);
    }


    @Override
    public void onBackPressed() {
        if(webV.canGoBack()) {
            webV.goBack();
        }
        else {
            super.onBackPressed();
        }
    }


    private  class CustomWebViewClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            webV.setVisibility(webV.INVISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            spinner.setVisibility(View.GONE);
            view.setVisibility(webV.VISIBLE);
            super.onPageFinished(view, url);
        }
    }
}
