package com.smc.highlight.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smc.highlight.R;

public class Fragment_Challenge extends Fragment{
    private final static String ARG_POSITON = "position";
    private int mPosition;

    public static Fragment_Challenge newInstance(int position) {

        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITON, position);

        Fragment_Challenge fragment = new Fragment_Challenge();
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
        View view = inflater.inflate(R.layout.fragment_challenge, container, false);
        return view;
    }
}

