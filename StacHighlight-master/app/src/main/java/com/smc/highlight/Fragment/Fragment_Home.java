package com.smc.highlight.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smc.highlight.Fragment.Recycler.RecyclerAdapter;
import com.smc.highlight.models.Item;
import com.smc.highlight.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Home extends Fragment{

    private final static String ARG_POSITON = "position";
    private int mPosition;
    private RecyclerView mRecyclerView;

    public Fragment_Home(){}

    public static Fragment_Home newInstance(int position) {

        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITON, position);

        Fragment_Home fragment = new Fragment_Home();
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.home_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        int ITEM_SIZE = 4;

        List<Item> items = new ArrayList<>();
        Item[] item = new Item[ITEM_SIZE];
        item[0] = new Item("이민혁", "과목", "~~~~~이다", 3 );
        item[1] = new Item("이민혁", "과목", "~~~~~이다", 2 );
        item[2] = new Item("이민혁", "과목", "~~~~~이다", 1 );
        item[3] = new Item("이민혁", "과목", "~~~~~이다", 1 );

        for (int i = 0; i < ITEM_SIZE; i++) {
            items.add(item[i]);
        }



        recyclerView.setAdapter(new RecyclerAdapter(getActivity(), items, R.layout.activity_main));
        return rootView;
    }

}
