package com.prashant.thegao.Dashboard.Panchayat;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.prashant.thegao.R;

public class PanchayatRoadWork extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panchayat_road_work);

        webView =findViewById(R.id.panchayt_roadwork_webview);
        getSupportActionBar().setTitle("Road Work Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        startWebView("http://prd.mp.gov.in/PanchParmeshwar/Public/WorksDetails.aspx?DID=NDM=&GP_ID=MTg5MjQ=&HID=MQ==");

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
                    progressDialog = new ProgressDialog(PanchayatRoadWork.this);
                    progressDialog.setMessage("Panchayat Road Work Details are Loading...");
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