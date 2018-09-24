package com.smc.highlight.Fragment.Highlighting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.smc.highlight.R;

public class CustomView extends View {
    Paint paint = new Paint();
    int startX=-1, startY=-1, stopX=-1, stopY=-1;
    HighlightingActivity ha = new HighlightingActivity();
    int color;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(0,0,0,0));
        switch (ha.color){
            case 1:
                paint.setColor(Color.argb(80,116,228,255));
                color = Color.argb(80,116,228,255);
                break;
            case 2:
                paint.setColor(Color.argb(80,97,255,109));
                break;
            case 3:
                paint.setColor(Color.argb(80,255,97,115));
                break;
            case 4:
                paint.setColor(Color.argb(80,255,196,97));
                break;
            case 5:
                paint.setColor(Color.argb(80,231,151,255));
                break;
            case 6:
                paint.setColor(Color.argb(80,230,255,68));
                break;
        }
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(50f);

        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }



    @Override

    public boolean onTouchEvent(MotionEvent event) {
        ha.errorText = (TextView)findViewById(R.id.highlighting_comment);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // 손가락이 디스플레이에 닿았을 때
                startX = (int)event.getX();
                startY = (int)event.getY();
                break;
            case MotionEvent.ACTION_MOVE: // 디스플레이를 누른 채 움질일 때
            case MotionEvent.ACTION_UP: // 손가락을 디스플레이에서 떨어트릴 떄
                stopX = (int)event.getX();
                stopY = (int)event.getY();
                this.invalidate();
                break;
        }
        return true;
    }

}


