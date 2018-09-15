package com.smc.highlight.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smc.highlight.R;

public class Fragment_Search extends Fragment{
    private final static String ARG_POSITON = "position";
    private int mPosition;

    public static Fragment_Search newInstance(int position) {

        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITON, position);

        Fragment_Search fragment = new Fragment_Search();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments().getInt(ARG_POSITON);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }
}

