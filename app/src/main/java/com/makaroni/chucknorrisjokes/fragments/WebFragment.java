package com.makaroni.chucknorrisjokes.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.makaroni.chucknorrisjokes.R;

public class WebFragment extends Fragment {
    WebView webView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.web_fragment,container,false);
        webView = rootView.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        webView.loadUrl("http://www.icndb.com/api/");
    }
}
