package com.smc.highlight.Fragment.Post;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smc.highlight.R;
import com.smc.highlight.models.CommentsDB;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity implements View.OnClickListener{
    EditText text_Comments;
    Button button_comments;

    private ListView comments_list;
    private View header;
    private DatabaseReference commentdb = FirebaseDatabase.getInstance().getReference("comments");
    int position;
    private String Comments;
    private int commentnum = 0;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);



        init();
    }


    void init(){
        comments_list = (ListView) findViewById(R.id.comments_list);
        text_Comments = (EditText) findViewById(R.id.main_comments);
        button_comments = (Button) findViewById(R.id.comment_submit);

        adapter = new ArrayAdapter(this, R.layout.comment_view, R.id.comments_username);

        button_comments.setOnClickListener(this);

        header = getLayoutInflater().inflate(R.layout.header, null, false);
        comments_list.addHeaderView(header);

        comments_list.setAdapter(adapter);

    }

    public void setPosition(int position){
        this.position = position;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.comment_submit:
                String temp = text_Comments.getText().toString();
                Toast.makeText(this, "이 게시물 번호는 " + position + "번 입니다.", Toast.LENGTH_SHORT).show();
                if(temp.equals("")){
                    Toast.makeText(this, "댓글을 입력하세요...", Toast.LENGTH_SHORT).show();
                }else{
                    //EditText의 빈칸이 없을 경우 등록!
                    this.Comments = temp;
                    //postFirebaseDatabase(true);
                    text_Comments.setText("");
                }
                break;
        }
    }

    /*
    이것은 database에 입력한 값을 넣어주는 코드이다.
    public void postFirebaseDatabase(boolean add){ // 연결은 성공, 하지만 position을 못불러와서 전부 0번 포스트에만 댓글이 달린다.
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if(add){
            CommentsDB post = new CommentsDB(Comments);
            postValues = post.toMap();
        }
        childUpdates.put("/" + this.position + "/" +commentnum++, postValues);
        commentdb.updateChildren(childUpdates);
    }
    */


    /*private void setHeader(){

        헤더의 id 값을 받아오기 위해서는 평소에 findViewById를 바로 썻는데 그 앞에header.
         * 을 붙여서 header에 만들어 놓은 TextView의 아이디 값을 쓰겠다 이런 식으로 앞에
         * 꼭 붙여주셔야 합니다. 안그러면 id값을 받아 올 수가 없어요 ㅠㅠ

        TextView jrv_title_text = (TextView)header.findViewById(R.id.jrv_title_text);
        jrv_title_text.setText("조던6 샴페인 팝니다!!");
        TextView jrv_content_text = (TextView)header.findViewById(R.id.jrv_content_text);
        jrv_content_text.setText("이름 : 조던6 샴페인\n가격 : 350,000원\n상태 : 새제품\n기타 : 문의사항 있으면 쪽지 보내주세요!");
        jrv_image_img = (ImageView)header.findViewById(R.id.jrv_image_img);

        //drawable의 이미지를 Bitmap으로 바꾸는 함수입니다.
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.jd_6);

        jrv_image_img.setImageBitmap(bmp);
    }*/

}
