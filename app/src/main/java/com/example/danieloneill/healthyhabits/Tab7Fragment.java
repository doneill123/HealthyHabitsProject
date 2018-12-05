package com.example.danieloneill.healthyhabits;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;

public class Tab7Fragment extends Fragment {
    private static final String TAG = "Tab7Fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab7_fragment,container,false);

        return view;
    }
}