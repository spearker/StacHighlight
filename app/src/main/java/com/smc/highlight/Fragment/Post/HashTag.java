package com.smc.highlight.Fragment.Post;

import android.content.Context;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smc.highlight.Fragment.Adapter.RecyclerAdapter;

public class HashTag extends ClickableSpan{
    //해시태그 개채 선언
    public interface ClickEventListener{ void onClickEvent(String data); }

    private ClickEventListener mClickEventListener = null;
    private Context context;
    private TextPaint textPaint;
    private static String theWord;

    private RecyclerAdapter ra = new RecyclerAdapter();
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Post").child(ra.getPostID());

    public HashTag(Context ctx){
        super();
        context = ctx;
    }

    public void setOnClickEventListener(ClickEventListener listener){
        mClickEventListener = listener;
    }

    @Override public void updateDrawState(TextPaint ds) {
        textPaint = ds;
        ds.setColor(ds.linkColor);
        ds.setARGB(255,30,144,255);
    }

    @Override
    public void onClick(View widget) {
        TextView tv = (TextView)widget;
        Spanned s = (Spanned)tv.getText();
        int start = s.getSpanStart(this);
        int end = s.getSpanEnd(this);
        theWord = s.subSequence(start + 1, end).toString();
        Log.d("theWord", theWord);
    }

    public String getTheWord(){
        return theWord;
    }

}
