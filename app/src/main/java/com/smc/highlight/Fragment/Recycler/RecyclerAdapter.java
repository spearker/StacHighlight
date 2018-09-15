package com.smc.highlight.Fragment.Recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smc.highlight.Fragment.Highlighting.HighlightingActivity;
import com.smc.highlight.Fragment.Post.HashTag;
import com.smc.highlight.Fragment.Post.PostActivity;
import com.smc.highlight.R;
;
import com.smc.highlight.models.PostModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<PostModel> mPost;
    String username;
    Context context;
    public static String postID;

    public static int postPosition;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView username, postdate, desc, highlighting;
        public ImageView userImage, postImage;
        public ImageButton highlighting_button;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.home_writer);
            userImage = (ImageView)itemView.findViewById(R.id.home_writerimg);
            postImage = (ImageView)itemView.findViewById(R.id.home_postimage);
            desc = (TextView)itemView.findViewById(R.id.home_desc);
            highlighting = (TextView)itemView.findViewById(R.id.home_highlight);
            highlighting_button = (ImageButton)itemView.findViewById(R.id.home_highlighter);
            postdate = (TextView)itemView.findViewById(R.id.home_time);
            cardView = (CardView)itemView.findViewById(R.id.home_cardview);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerAdapter(List<PostModel> mPost, Context context) {
        this.mPost = mPost;
        this.context = context;

    }

    public RecyclerAdapter(){}

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

    public ArrayList<int[]> getSpans(String body, char prefix) {
        ArrayList<int[]> spans = new ArrayList<int[]>();

        Pattern pattern = Pattern.compile(prefix + "\\w+");
        Matcher matcher = pattern.matcher(body);

        // Check all occurrences
        while (matcher.find()) {
            int[] currentSpan = new int[2];
            currentSpan[0] = matcher.start();
            currentSpan[1] = matcher.end();
            spans.add(currentSpan);
        }

        return  spans;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final String username = mPost.get(position).getUsername();
        final String userImage = mPost.get(position).getUserIamge();
        final String postImage = mPost.get(position).getPostImage();
        //final String desc = mPost.get(position).getDesc();
        final String highlighting = mPost.get(position).getHighlighting();
        final String postDate = simpleDateFormat.format(mPost.get(position).getDate());

        String commentsText = mPost.get(position).getDesc();

        ArrayList<int[]> hashtagSpans = getSpans(commentsText, '#');

        SpannableString commentsContent =
                new SpannableString(commentsText);

        for(int i = 0; i < hashtagSpans.size(); i++) {
            int[] span = hashtagSpans.get(i);
            int hashTagStart = span[0];
            int hashTagEnd = span[1];

            commentsContent.setSpan(new HashTag(context), hashTagStart, hashTagEnd, 0);

        }
        holder.username.setText(username);
        Glide.with(context)
                .load(userImage)
                .into(holder.userImage);
        Glide.with(context)
                .load(postImage)
                .into(holder.postImage);
        holder.desc.setMovementMethod(LinkMovementMethod.getInstance());
        holder.desc.setText(commentsContent);
        holder.highlighting.setText(highlighting);
        holder.postdate.setText(postDate);
        holder.highlighting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postID = mPost.get(position).getPostID();
                Intent intent = new Intent(context, HighlightingActivity.class);
                context.startActivity(intent);
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postID = mPost.get(position).getPostID();
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

    public String getPostID() {
        return postID;
    }
}





