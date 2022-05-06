package com.prashant.thegao.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.prashant.thegao.Dashboard.Education.EducationFeatureDash;
import com.prashant.thegao.Dashboard.Electricity.ElectricityFeatureDash;
import com.prashant.thegao.Dashboard.Panchayat.PanchayatSubDashboard.PanchayatFeatureDash;
import com.prashant.thegao.R;


public class HomeFragment extends Fragment {
    WebView webView;
    Button panchayatBTN,electricityBTN,educationBTN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        webView = view.findViewById(R.id.webview1);
        panchayatBTN = view.findViewById(R.id.pnchytBTNid);
        electricityBTN = view.findViewById(R.id.electricBTNid);
        educationBTN = view.findViewById(R.id.educationBTNid);

        getActivity().setTitle("Dashboard");

        panchayatBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PanchayatFeatureDash.class);
                startActivity(intent);
            }
        });

        electricityBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ElectricityFeatureDash.class);
                startActivity(intent);
            }
        });

        educationBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EducationFeatureDash.class);
                startActivity(intent);
            }
        });



        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.bhaskar.com/local/mp/");
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