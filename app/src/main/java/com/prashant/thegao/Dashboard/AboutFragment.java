package com.prashant.thegao.Dashboard;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.prashant.thegao.R;


public class AboutFragment extends Fragment {

    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        getActivity().setTitle("About App");
        webView = view.findViewById(R.id.aboutwebciew);


        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://thegao.tk");
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                        if (webView != null) {
                            if (webView.canGoBack()) {
                                webView.canGoBack();
                            } else {
                                requireActivity().onBackPressed();
                            }
                        }
                    }
                }

                return false;
            }
        });


        return view;
    }
}