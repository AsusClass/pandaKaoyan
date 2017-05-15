package activitytest.example.com.pandakaoyan;
/*
帖子详情界面
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import activitytest.example.com.pandakaoyan.adapters.ReplyAdapter;
import activitytest.example.com.pandakaoyan.panda.shiti.BasicActivity;
import activitytest.example.com.pandakaoyan.panda.shiti.ReplyContent;

public class PostContentActivity extends BasicActivity {
    private List<ReplyContent> replyContentList = new ArrayList<>();//回复集合


    private int current_image;
    private String current_user;
    private int imageId;
    private String username,titl,content1;


    private ListView listView;
    private TextView back,pagename, discuss, til;
    private EditText discuss_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_content);


        listView = (ListView) findViewById(R.id.discuss_list);
        back = (TextView) findViewById(R.id.back);

        pagename = (TextView) findViewById(R.id.page_name);
        discuss = (TextView) findViewById(R.id.click_discuss);
        discuss_content = (EditText) findViewById(R.id.discuss_edit);
        pagename.setText("您的位置:帖子详情");


//接受从homepage传过来的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        imageId = bundle.getInt("image");
        username = bundle.getString("username");
         titl = bundle.getString("title");
        content1 = bundle.getString("content");//以上是帖子楼主的信息

        initReply();//初始化listview数据

        ReplyAdapter adapter = new ReplyAdapter(replyContentList, PostContentActivity.this);
        listView.setAdapter(adapter);//启动适配器


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostContentActivity.this.finish();
            }
        });
        discuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String discuss_text = discuss_content.getText().toString();//获取回复框的内容
                ReplyContent replyContent = new ReplyContent(current_image, current_user, discuss_text);
                replyContentList.add(replyContent);
                ReplyAdapter adapter = new ReplyAdapter(replyContentList, PostContentActivity.this);
                listView.setAdapter(adapter);//启动适配器
                Toast.makeText(PostContentActivity.this, "评论成功！", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void initReply() {

        for (int i = 0; i < 3; i++) {
            ReplyContent replyContent3 = new ReplyContent(imageId, username, titl,content1);
            replyContentList.add(replyContent3);
            ReplyContent replyContent = new ReplyContent(R.drawable.user1, "大表哥", "楼主真乃神人也！");
            replyContentList.add(replyContent);
            ReplyContent replyContent1 = new ReplyContent(R.drawable.use7, "小仙女", "楼主真乃神人也！");
            replyContentList.add(replyContent1);
            ReplyContent replyContent2 = new ReplyContent(R.drawable.user2, "妹子加微信", "旷世奇才");
            replyContentList.add(replyContent2);
        }

    }


}


