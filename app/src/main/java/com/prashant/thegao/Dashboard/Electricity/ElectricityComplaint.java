package com.prashant.thegao.Dashboard.Electricity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.prashant.thegao.R;

public class ElectricityComplaint extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_complaint);

        getSupportActionBar().setTitle("Electricity Complaint");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        webView = findViewById(R.id.electric_cmplnt_webview);
        webView.setWebViewClient(new WebViewClient());

        startWebView("https://mpezccc.in/mppkvvcl/home/new_complaint");

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setVerticalScrollBarEnabled(false);

        webView.setHorizontalScrollBarEnabled(false);
    }

    private void startWebView(String url) {
        //When opening a url or click on link

        webView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(ElectricityComplaint.this);
                    progressDialog.setMessage("Electricity Complaint Page are Loading...");
                    progressDialog.show();
                }
            }
            public void onPageFinished(WebView view, String url) {
                try{
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();

                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }

        });

        // Javascript inabled on webview
        webView.getSettings().setJavaScriptEnabled(true);

        // Other webview options

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);



        String summary = "<html><body>You scored <b>192</b> points.</body></html>";
        webView.loadData(summary, "text/html", null);


        //Load url in webview
        webView.loadUrl(url);


    }

    // Open previous opened link from history on webview when back button pressed

    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }

}