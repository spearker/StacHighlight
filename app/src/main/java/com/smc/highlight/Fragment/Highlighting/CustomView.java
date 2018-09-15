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
    Path path = new Path();

    float x,y, startX=-1, startY=-1, endX=-1, endY=-1;

    HighlightingActivity ha = new HighlightingActivity();

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {

        if (ha.color == 1){ //HighlightingActivity 에서 받은 color값으로 지정된 색을 표시해주는 if문(switch로 작성해도 된다.)
            paint.setColor(Color.parseColor("#66e91e63"));
        }else if (ha.color == 2){
            paint.setColor(Color.parseColor("#66804058"));
        }else if(ha.color == 3){
            paint.setColor(Color.parseColor("#66e91e63"));
        }else if(ha.color ==  4){
            paint.setColor(Color.parseColor("#66e91e63"));
        }else if(ha.color == 5){
            paint.setColor(Color.parseColor("#66e91e63"));
        }else{
            paint.setColor(Color.WHITE);
        }

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(50f);

        canvas.drawPath(path, paint);
    }



    @Override

    public boolean onTouchEvent(MotionEvent event) {
        ha.errorText = (TextView)findViewById(R.id.highlighting_comment);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // 손가락이 디스플레이에 닿았을 때
                x = event.getX();
                y = event.getY();
                path.moveTo(x, y); //좌표 이동
                break;
            case MotionEvent.ACTION_MOVE: // 디스플레이를 누른 채 움질일 때
                x = event.getX();
                y = event.getY();
                path.moveTo(x, y);
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP: // 손가락을 디스플레이에서 떨어트릴 떄
                if (startX != -1 || endX != -1) {
                    x = event.getX();
                    y = event.getY();

                    path.lineTo(x, y); //경로에 선 긋기
                }else{
                    ha.count = 1;
                }
                break;
        }
        invalidate();
        return true;
    }

}


