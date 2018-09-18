package com.smc.highlight.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smc.highlight.Fragment.Adapter.RecyclerAdapter;
import com.smc.highlight.R;
import com.smc.highlight.models.PostModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment_Home extends Fragment{

    private final static String ARG_POSITON = "position";
    private int mPosition;
    private FirebaseDatabase database;
    private DatabaseReference myRef ;
    private List<PostModel> list = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.home_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<PostModel>();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Post");

        myRef.orderByChild("date").limitToLast(100).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               list.clear();
               int i = (int)dataSnapshot.getChildrenCount();
               String postID[] = new String[i];
               String username[] = new String[i];
               String postImage[] = new String[i];
               String userImage[] = new String[i];
               String desc[] = new String[i];
               Date date[] = new Date[i];

               int j = 0;

               for (DataSnapshot itemSnapshot:dataSnapshot.getChildren()){
                   postID[j] = itemSnapshot.getKey();
                   username[j] = itemSnapshot.child("username").getValue(String.class);
                   postImage[j] = itemSnapshot.child("postImage").getValue(String.class);
                   userImage[j] = itemSnapshot.child("userImage").getValue(String.class);
                   desc[j] = itemSnapshot.child("desc").getValue(String.class);
                   date[j] = itemSnapshot.child("date").getValue(Date.class);

                  j++;
               }

               for(i = (int)dataSnapshot.getChildrenCount() - 1; i >= 0; i--){
                   PostModel pm = new PostModel(postID[i], username[i], userImage[i], postImage[i], desc[i], 3, date[i]);
                   list.add(pm);
               }

               Log.d("postID", postID[0]);

               mAdapter = new RecyclerAdapter(list, getContext());
               mRecyclerView.setAdapter(mAdapter);
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

       return rootView;
    }

}
