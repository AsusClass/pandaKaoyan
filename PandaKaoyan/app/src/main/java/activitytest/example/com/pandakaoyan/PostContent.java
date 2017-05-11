package activitytest.example.com.pandakaoyan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import activitytest.example.com.pandakaoyan.adapters.ReplyAdapter;
import activitytest.example.com.pandakaoyan.panda.shiti.BasicActivity;
import activitytest.example.com.pandakaoyan.panda.shiti.ReplyContent;

public class PostContent extends BasicActivity {
    private List<ReplyContent> replyContentList = new ArrayList<>();//回复集合
    private  String current_user;
    private  int current_image;
    private  EditText discuss_content;
    private  int imageId;
    private String username;
    private  String content1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_content);
       // ImageView imageView = (ImageView) findViewById(R.id.user_image2);
        //TextView user = (TextView) findViewById(R.id.username);
        TextView til = (TextView) findViewById(R.id.post_item_title);
        //TextView content = (TextView) findViewById(R.id.post_item_content);
        final ListView listView = (ListView) findViewById(R.id.discuss_list);
        TextView back=(TextView) findViewById(R.id.back);
        TextView pagename=(TextView)findViewById(R.id.page_name);
        TextView discuss=(TextView)findViewById(R.id.discuss);
        pagename.setText("您的位置:帖子详情");
          discuss_content=(EditText)findViewById(R.id.discuss_edit);
//接受从homepage传过来的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        imageId = bundle.getInt("image");
         username = bundle.getString("username");
        String titl = bundle.getString("title");
         content1 = bundle.getString("content");

       current_user=bundle.getString("current_username");
        current_image=bundle.getInt("current_image");//获取当前登录的用户信息

        //imageView.setImageResource(imageId);
       // user.setText(username);
       //til.setText("标题");
        //content.setText(content1);


        initReply();
        ReplyAdapter adapter = new ReplyAdapter(replyContentList, PostContent.this);
        listView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PostContent.this.finish();
            }
        });
        discuss.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String discuss_text=discuss_content.getText().toString();
                ReplyContent replyContent = new ReplyContent(current_image, current_user, discuss_text);
                replyContentList.add(replyContent);
              //onCreate(null);
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       while (!Thread.currentThread().isInterrupted()) {
                           try {
                               Thread.sleep(100);
                           } catch (InterruptedException e) {
                               Thread.currentThread().interrupt();
                           }
                    // 使用postInvalidate可以直接在线程中更新界面
                           listView.postInvalidate();
                       }
                   }
               }) ;




                Toast.makeText(PostContent.this, "评论成功！", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void initReply() {

        for (int i = 0; i < 10; i++) {
            ReplyContent replyContent3=new ReplyContent(imageId,username,content1);
            replyContentList.add(replyContent3);
            ReplyContent replyContent = new ReplyContent(R.drawable.user1, "大表哥", "楼主真乃神人也！");
            replyContentList.add(replyContent);
            ReplyContent replyContent1 = new ReplyContent(R.drawable.use7, "小仙女", "楼主真乃神人也！");
            replyContentList.add(replyContent1);
            ReplyContent replyContent2 = new ReplyContent(R.drawable.user2, "妹子加微信", "旷世奇才emoji楼主我们做朋友吧我是妹子哟让我做你的小仙女");
            replyContentList.add(replyContent2);
        }

    }





}


