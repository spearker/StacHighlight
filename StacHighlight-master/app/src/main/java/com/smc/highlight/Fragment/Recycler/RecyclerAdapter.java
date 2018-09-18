package com.smc.highlight.Fragment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.smc.highlight.Fragment.Highlighting.HighlightingActivity;
import com.smc.highlight.Fragment.Post.PostActivity;
import com.smc.highlight.R;
import com.smc.highlight.models.Item;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<Item> items;
    int item_layout;


    PostActivity pa = new PostActivity();


    public RecyclerAdapter(Context context, List<Item> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.highlighting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, HighlightingActivity.class));
            }
        });
        holder.writer.setText(item.getWriter());
        holder.detail.setText(item.getDetail());
        holder.contents.setText(item.getContents());
        holder.highlight.setText(item.getHighlight());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "this post number is " + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, PostActivity.class));
            }

        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton highlighting;
        TextView writer;
        TextView detail;
        TextView contents;
        TextView highlight;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            writer = (TextView) itemView.findViewById(R.id.home_writer);
            detail = (TextView) itemView.findViewById(R.id.home_detail);
            contents = (TextView) itemView.findViewById(R.id.home_contents);
            highlight = (TextView) itemView.findViewById(R.id.home_highlight);
            cardview = (CardView) itemView.findViewById(R.id.home_cardview);
            highlighting = (ImageButton) itemView.findViewById(R.id.home_highlighter);
            }
    }
}


