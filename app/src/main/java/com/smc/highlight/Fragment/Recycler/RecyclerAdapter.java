package com.smc.highlight.Fragment.Recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smc.highlight.Fragment.Post.PostActivity;
import com.smc.highlight.R;
;
import com.smc.highlight.models.PostModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<PostModel> mPost;
    String username;
    Context context;

    public static int postPosition = -1;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView username, postdate, desc, highlighting;
        public ImageView userImage, postImage;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.home_writer);
            userImage = (ImageView)itemView.findViewById(R.id.home_writerimg);
            postImage = (ImageView)itemView.findViewById(R.id.home_postimage);
            desc = (TextView)itemView.findViewById(R.id.home_desc);
            highlighting = (TextView)itemView.findViewById(R.id.home_highlight);
            postdate = (TextView)itemView.findViewById(R.id.home_time);
            cardView = (CardView)itemView.findViewById(R.id.home_cardview);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerAdapter(List<PostModel> mPost, Context context) {
        this.mPost = mPost;
        this.context = context;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_cardview, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final String username = mPost.get(position).getUsername();
        final String userImage = mPost.get(position).getUserIamge();
        final String postImage = mPost.get(position).getPostImage();
        final String desc = mPost.get(position).getDesc();
        final String highlighting = mPost.get(position).getHighlighting();
        final String postDate = simpleDateFormat.format(mPost.get(position).getDate());

        holder.username.setText(username);
        Glide.with(context)
                .load(userImage)
                .into(holder.userImage);
        Glide.with(context)
                .load(postImage)
                .into(holder.postImage);
        holder.desc.setText(desc);
        holder.highlighting.setText(highlighting);
        holder.postdate.setText(postDate);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PostActivity.class);
                context.startActivity(intent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPost.size();
    }
}





