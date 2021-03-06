package com.smc.highlight.Fragment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smc.highlight.Fragment.Highlighting.HighlightingActivity;
import com.smc.highlight.Fragment.Post.HashTag;
import com.smc.highlight.Fragment.Post.PostActivity;
import com.smc.highlight.MainActivity;
import com.smc.highlight.R;
;
import com.smc.highlight.models.PostModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<PostModel> mPost;
    String username;
    Context context;
    public static String postID;

    public static int postPosition;

    FirebaseStorage storage = FirebaseStorage.getInstance();


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView username, postdate, desc, highlighting, detail;
        public ImageView userImage, postImage;
        public Button highlighting_button;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.home_writer);
            userImage = (ImageView)itemView.findViewById(R.id.home_writerimg);
            postImage = (ImageView)itemView.findViewById(R.id.home_postimage);
            desc = (TextView)itemView.findViewById(R.id.user_comment);
            detail = (TextView)itemView.findViewById(R.id.home_detail);


            ////////수정하기

            
            highlighting = (TextView)itemView.findViewById(R.id.home_highlight);
            highlighting_button = (Button)itemView.findViewById(R.id.home_highlighter);
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cardview, parent, false);

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final String username = mPost.get(position).getUsername();
        final String userImage = mPost.get(position).getUserIamge();
        final String postImage = mPost.get(position).getPostImage();
        //final String desc = mPost.get(position).getDesc();
        final String highlighting = mPost.get(position).getHighlighting();
        final String postDate = simpleDateFormat.format(mPost.get(position).getDate());
        String uid = mPost.get(position).getUID().toString();

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("User");

        myRef.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userBelong = dataSnapshot.child("userBelong").getValue(String.class);
                String userInterest = dataSnapshot.child("userInterest").getValue(String.class);
               // holder.detail.setText(userBelong + " / " + userInterest);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

        StorageReference storageRef = storage.getReference("postImage/"+postImage);
        StorageReference userRef = storage.getReference("userImage/"+userImage);

        holder.username.setText(username);
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(userRef)
                .into(holder.userImage);
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(storageRef)
                .into(holder.postImage);
        holder.desc.setMovementMethod(LinkMovementMethod.getInstance());
        holder.desc.setText(commentsContent);
        //holder.highlighting.setText(highlighting);
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
               //postID = mPost.get(position).getPostID();
               //Intent intent = new Intent(context, PostActivity.class);
               //context.startActivity(intent);
                Toast.makeText(context, "조금만 기다려주세요!", Toast.LENGTH_SHORT).show();
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





